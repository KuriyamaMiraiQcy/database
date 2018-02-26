package db;

import db.dbDatatype.DBDatatype;
import db.dbDatatype.DBDouble;
import db.dbDatatype.DBInteger;
import db.dbDatatype.DBString;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 62339 on 2017/2/25.
 */

public class Column {
    ArrayList<DBDatatype> elements;
    String columnType;


    public Column(int capacity, String columnType) {
        elements = new ArrayList<>(capacity);
        this.columnType = columnType;
    }

    //Copy constructor
    public Column(Column b) {
        this.columnType = new String(b.columnType);
        this.elements = new ArrayList<>(b.elements);
        //TODO: This only returns a shallow copy of the elements inside.
        // Potential Errors.
        // Constructs a list containing the elements of the specified
        // collection, in the order they are returned by the collection's iterator.

    }

    //Add a element with a input string.
    public void addElement(String element) {

        if (columnType.equals("string")) {
            if (element.equals("NOVALUE")) {
                elements.add(DBString.getNOVALUE());
            } else {
                elements.add(new DBString(element));
            }
        } else if (columnType.equals("int")) {
            if (element.equals("NOVALUE")) {
                elements.add(DBInteger.getNOVALUE());
            } else {
                try {
                    int elementIntVal = Integer.parseInt(element);
                    elements.add(new DBInteger(elementIntVal));
                } catch (NumberFormatException e) {
                    System.out.println("This is not a valid value for int!");
                }
            }
        } else {
            if (element.equals("NOVALUE")) {
                elements.add(DBDouble.getNOVALUE());
            } else {
                try {
                    Double elementFloatVal = Double.parseDouble(element);
                    elements.add(new DBDouble((double) elementFloatVal));
                } catch (NumberFormatException e) {
                    System.out.println("This is not a valid value for float!");
                }
            }
        }
    }

    //Add a element with existed element
    public void addElementWithDataType(DBDatatype element) {
        if (element instanceof DBInteger && columnType.equals("int")) {
            elements.add(new DBInteger((DBInteger) element));
        } else if (element instanceof DBString && columnType.equals("string")) {
            elements.add(new DBString((DBString) element));
        } else if (element instanceof DBDouble && columnType.equals("float")) {
            elements.add(new DBDouble((DBDouble) element));
        } else {
            throw new RuntimeException("Type error");
        }
    }


    //Search for elements that equal to the parameter. Put the result in a LinkedList.
    public LinkedList columnSearch(String element) {
        LinkedList<Integer> indexList = new LinkedList<>();
        for (int i = 0; i < elements.size(); i += 1) {
            if (elements.get(i).toString().equals(element)) {
                indexList.addLast(i);
            }
        }
        return indexList;
    }

    //Return the element's String representation in the databse.
    public String printElement(int i) {
        return elements.get(i).print();
    }

    //Return Column size.
    public int getSize() {
        return elements.size();
    }

    //Return the element in a certain row.
    public DBDatatype getElementAtIndex(int i) {
        return elements.get(i);
    }

}
