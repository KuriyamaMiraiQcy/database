package db;

import db.dbDatatype.DBDouble;
import db.dbDatatype.DBInteger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by 62339 on 2017/2/25.
 */
public class TestColumn {

    @Test
    public void testColumn() {
        Column a = new Column(12, "int");
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
    }

    @Test
    public void testAddElement() {
        Column a = new Column(12, "int");
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElement("2");
        a.addElement("3");
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (Integer i = 0; i < 100; i += 1) {
            a.addElement(i.toString());
        }
        assertEquals(102, a.elements.size());
        a.addElement("2.5");
        assertEquals(102, a.elements.size());
        a.addElement("322.8fds");
        assertEquals(102, a.elements.size());
        a.elements.clear();
        assertEquals(0, a.elements.size());
    }

    @Test
    public void testColumnSearch() {
        Column a = new Column(12, "int");
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElement("2");
        a.addElement("3");
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (Integer i = 0; i < 100; i += 1) {
            a.addElement(i.toString());
        }
        for (Integer i = 0; i < 5; i += 1) {
            a.addElement("2");
        }
        System.out.println(a.columnSearch("2"));
    }

    @Test
    public void testAddElementWithDataType() {
        Column a = new Column(12, "int");
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElementWithDataType(new DBInteger(2));
        a.addElementWithDataType(new DBInteger(3));
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (int i = 0; i < 100; i += 1) {
            a.addElementWithDataType(new DBInteger(i));
        }
        assertEquals(102, a.elements.size());
        try {
            a.addElementWithDataType(new DBDouble(2.5));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(102, a.elements.size());
        for (Integer i = 0; i < 5; i += 1) {
            a.addElementWithDataType(new DBInteger(2));
        }
        a.elements.clear();
        assertEquals(0, a.elements.size());
    }

    @Test
    public void testColumnSearchWithDataType() {
        Column a = new Column(12, "int");
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElementWithDataType(new DBInteger(2));
        a.addElementWithDataType(new DBInteger(3));
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (int i = 0; i < 100; i += 1) {
            a.addElementWithDataType(new DBInteger(i));
        }
        assertEquals(102, a.elements.size());
        try {
            a.addElementWithDataType(new DBDouble(2.5));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        for (Integer i = 0; i < 5; i += 1) {
            a.addElementWithDataType(new DBInteger(2));
        }
        assertEquals(107, a.elements.size());
        System.out.println(a.columnSearch("2"));
    }

    @Test
    public void testColumn(Column a) {
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
    }

    @Test
    public void testAddElement(Column a) {
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElement("2");
        a.addElement("3");
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (Integer i = 0; i < 100; i += 1) {
            a.addElement(i.toString());
        }
        assertEquals(102, a.elements.size());
        a.addElement("2.5");
        assertEquals(102, a.elements.size());
        a.addElement("322.8fds");
        assertEquals(102, a.elements.size());
        a.elements.clear();
        assertEquals(0, a.elements.size());
    }

    @Test
    public void testColumnSearch(Column a) {
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElement("2");
        a.addElement("3");
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (Integer i = 0; i < 100; i += 1) {
            a.addElement(i.toString());
        }
        for (Integer i = 0; i < 5; i += 1) {
            a.addElement("2");
        }
        System.out.println(a.columnSearch("2"));
    }

    @Test
    public void testAddElementWithDataType(Column a) {
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElementWithDataType(new DBInteger(2));
        a.addElementWithDataType(new DBInteger(3));
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (int i = 0; i < 5; i += 1) {
            a.addElementWithDataType(new DBInteger(i));
        }
        assertEquals(7, a.elements.size());
        try {
            a.addElementWithDataType(new DBDouble(2.5));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(7, a.elements.size());
        for (Integer i = 0; i < 5; i += 1) {
            a.addElementWithDataType(new DBInteger(2));
        }

    }

    @Test
    public void testColumnSearchWithDataType(Column a) {
        assertEquals("int", a.columnType);
        assertTrue(a.elements.isEmpty());
        a.addElementWithDataType(new DBInteger(2));
        a.addElementWithDataType(new DBInteger(3));
        assertEquals(2, a.elements.size());
        assertEquals(1, a.elements.indexOf(new DBInteger(3)));
        for (int i = 0; i < 100; i += 1) {
            a.addElementWithDataType(new DBInteger(i));
        }
        assertEquals(102, a.elements.size());
        try {
            a.addElementWithDataType(new DBDouble(2.5));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        for (Integer i = 0; i < 5; i += 1) {
            a.addElementWithDataType(new DBInteger(2));
        }
        assertEquals(107, a.elements.size());
        System.out.println(a.columnSearch("2"));
    }
}
