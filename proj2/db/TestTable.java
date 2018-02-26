package db;

import db.dbDatatype.*;
import org.junit.Test;

/**
 * Created by 62339 on 2017/2/25.
 */
public class TestTable {
    @Test
    public void testTable() {
        String[] a = new String[2];
        a[0] = "a";
        a[1] = "b";
        String[] b = new String[2];
        b[0] = "int";
        b[1] = "string";
        Table aTable = new Table(a, b);
        Column aColumn = aTable.columns.get("a");
        TestColumn aTest = new TestColumn();
        aTest.testAddElement(aColumn);
        aColumn.elements.clear();
        aTest.testAddElementWithDataType(aColumn);
        aColumn.elements.clear();
        aTest.testColumn(aColumn);
        aColumn.elements.clear();
        aTest.testColumnSearch(aColumn);
        aColumn.elements.clear();
        aTest.testColumnSearchWithDataType(aColumn);
    }

    @Test
    public void testDropTable() {
        String[] a = new String[2];
        a[0] = "a";
        a[1] = "b";
        String[] b = new String[2];
        b[0] = "int";
        b[1] = "string";
        Table aTable = new Table(a, b);
        Column aColumn = aTable.columns.get("a");
        TestColumn aTest = new TestColumn();
        aTest.testAddElement(aColumn);
        aColumn.elements.clear();
        aTest.testAddElementWithDataType(aColumn);
        aColumn.elements.clear();
        aTest.testColumn(aColumn);
        aColumn.elements.clear();
        aTest.testColumnSearch(aColumn);
        aColumn.elements.clear();
        aTest.testColumnSearchWithDataType(aColumn);
    }

    @Test
    public void testTablePrint() {
        String[] a = new String[3];
        a[0] = "hfjh";
        a[1] = "mbvcb";
        a[2] = "qfds";
        String[] b = new String[3];
        b[0] = "int";
        b[1] = "float";
        b[2] = "string";
        Table aTable = new Table(a, b);
        Column aColumn = aTable.columns.get("hfjh");
        Column bColumn = aTable.columns.get("mbvcb");
        Column cColumn = aTable.columns.get("qfds");
        aColumn.addElement("1");
        bColumn.addElement("1.23");
        cColumn.addElement("ghj");
        aColumn.addElement("NOVALUE");
        bColumn.addElement("NOVALUE");
        cColumn.addElement("NOVALUE");
        aColumn.addElementWithDataType(new DBInteger(3));
        bColumn.addElementWithDataType(new DBDouble(2.3));
        cColumn.addElementWithDataType(new DBString("4564DS"));
        aColumn.addElementWithDataType(new DBInteger(new NaN()));
        bColumn.addElementWithDataType(new DBDouble(new NaN()));
        cColumn.addElementWithDataType(new DBString(new NaN()));
        aColumn.addElementWithDataType(new DBInteger(new NOVALUE()));
        bColumn.addElementWithDataType(new DBDouble(new NOVALUE()));
        cColumn.addElementWithDataType(new DBString(new NOVALUE()));
        aTable.printTable();
    }

    @Test
    public void testTableAddRow() {
        String[] a = new String[3];
        a[0] = "gdfgd";
        a[1] = "hgg";
        a[2] = "priohdg";
        String[] b = new String[3];
        b[0] = "int";
        b[1] = "float";
        b[2] = "string";
        Table aTable = new Table(a, b);
        String[] aString = new String[3];
        aString[0] = "NOVALUE";
        aString[1] = "NOVALUE";
        aString[2] = "NOVALUE";
        aTable.addRow(aString);
        aString[0] = "1";
        aString[1] = "2.3344353";
        aString[2] = "dsds";
        aTable.addRow(aString);
        aString[0] = "1";
        aString[1] = "2.3344353";
        aString[2] = "NOVALUE";
        aTable.addRow(aString);
        aTable.printTable();
    }

    @Test
    public void testCartesianProductTable() {
        String[] a = new String[3];
        a[0] = "gdfgd";
        a[1] = "hgg";
        a[2] = "priohdg";
        String[] b = new String[3];
        b[0] = "int";
        b[1] = "float";
        b[2] = "string";
        Table aTable = new Table(a, b);
        String[] aString = new String[3];

        aString[0] = "3";
        aString[1] = "2.365353";
        aString[2] = "dsdsds";
        aTable.addRow(aString);
        aString[0] = "1";
        aString[1] = "3.344353";
        aString[2] = "NOVALUE";
        aTable.addRow(aString);

        String[] c = new String[3];
        c[0] = "gdfg6556d";
        c[1] = "hg4345g";
        c[2] = "jhphgfghfdg";
        b[0] = "int";
        b[1] = "float";
        b[2] = "string";
        Table bTable = new Table(c, b);

        aString[0] = "3";
        aString[1] = "2.365353";
        aString[2] = "dsfgdgfds";
        bTable.addRow(aString);
        aString[0] = "4";
        aString[1] = "5.3";
        aString[2] = "NOVALUE";
        bTable.addRow(aString);
        System.out.println(aTable.joinTable(aTable, bTable).printTable());
        System.out.println("Join Table print!");
        aTable.printTable();
        bTable.printTable();
    }

    @Test
    public void testInput() {
        System.out.print(Table.loadTable("teams"));
    }

    @Test
    public void testStore() {
        String[] a = new String[3];
        a[0] = "gdfgd";
        a[1] = "hgg";
        a[2] = "priohdg";
        String[] b = new String[3];
        b[0] = "int";
        b[1] = "float";
        b[2] = "string";
        Table aTable = new Table(a, b);
        String[] aString = new String[3];

        aString[0] = "3";
        aString[1] = "2.365353";
        aString[2] = "dsdsds";
        aTable.addRow(aString);
        aString[0] = "1";
        aString[1] = "3.344353";
        aString[2] = "NOVALUE";
        aTable.addRow(aString);

        String[] c = new String[3];
        c[0] = "gdfgd";
        c[1] = "hgg";
        c[2] = "jhphgfghfdg";
        b[0] = "int";
        b[1] = "float";
        b[2] = "string";
        Table bTable = new Table(c, b);

        aString[0] = "3";
        aString[1] = "2.365353";
        aString[2] = "dsfgdgfds";
        bTable.addRow(aString);
        aString[0] = "4";
        aString[1] = "5.3";
        aString[2] = "NOVALUE";
        bTable.addRow(aString);
        Table join = aTable.joinTable(aTable, bTable);
        Database db = new Database();
        Database.addTable("t11", join);

        join.storeTable("t11");
    }
}
