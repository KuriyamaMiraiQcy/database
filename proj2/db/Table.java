package db;


import db.dbDatatype.DBDatatype;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by 62339 on 2017/2/25.
 */
public class Table {
    HashMap<String, Column> columns = new HashMap<>();
    String[] columnsType;
    String[] columnsName;

    //Take in two String arrays. First one contains all the names of the column.
    //Another one contains all the tyoe.
    public Table(String[] columnsName, String[] columnsType) {
        if (columnsName.length != columnsType.length) {
            throw new RuntimeException("You haven't give types for some columns");
        }
        for (int i = 0; i < columnsName.length; i += 1) {
            columns.put(columnsName[i], new Column(8, columnsType[i]));
        }
        this.columnsType = columnsType;
        this.columnsName = columnsName;
    }

    //Help method to split a string by space.
    //The parameter should only contains just two substrings that contains no space.
    private static String[] splitBySpace(String a) {
        int start = 0;
        String[] attr = new String[2];
        String[] temp = a.split(" ");
        int i = 0;
        for (String x: temp) {
            if (!x.equals("")) {
                attr[i] = x;
                i += 1;
            }
        }
        return attr;
    }

    //Load a table from file.
    public static String loadTable(String tableName) {
        try {
            //Cite:http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java
            String dir = System.getProperty("user.dir");
            dir +=  "/" + tableName + ".tbl";
            //Cite:http://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
            Scanner in = new Scanner(new FileReader(dir));
            String tableAttr = in.nextLine();

            String[] splitByComma = tableAttr.split(",");
            String[] name = new String[splitByComma.length];
            String[] type = new String[splitByComma.length];

            for (int i = 0; i < splitByComma.length; i += 1) {
                String[] splitSpace = splitBySpace(splitByComma[i]);
                name[i] = splitSpace[0];
                type[i] = splitSpace[1];
            }
            Table aTable = new Table(name, type);

            while (in.hasNextLine()) {
                String tempLine = in.nextLine();

                String[] row = tempLine.split(",");
                aTable.addRow(row);
            }
            Database.addTable(tableName, aTable);
            return "";
        } catch (Exception e) {
            return ("ERROR:" + e.getMessage());
        }
    }

    //Write the table to file.
    public static String storeTable(String tableName) {
        BufferedWriter output = null;
        try {
            String dir = System.getProperty("user.dir");
            dir += "/" + tableName + ".tbl";
            File file = new File(dir);
            output = new BufferedWriter(new FileWriter(file));
            output.write(Database.getTable(tableName).printTable());
            output.close();
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public void dropTable() {
        columns = null;
    }

    //Iterate through whole table to get the value..
    public String printTable() {

        int lengthOfColumn = get(0).getSize();
        String printString = "";

        //As the name and type row is not included in the column elements.
        //Use -1 to identify that I want to print out this line.
        for (int i = -1; i < lengthOfColumn; i += 1) {
            for (int j = 0; j < columnsName.length; j += 1) {
                if (i == -1) {
                    printString += columnsName[j] + " ";
                    printString += get(j).columnType;
                } else {
                    printString += get(j).printElement(i);
                }
                if (j != columnsName.length - 1) {
                    printString += ",";
                }
            }
            if (i != lengthOfColumn - 1) {
                printString += "\n";
            }
        }
        return printString;
    }

    //Help method to concat two strings
    private String[] StringArrayConcat(String[] a, String[] b) {
        String[] concatString = new String[a.length + b.length];
        System.arraycopy(a, 0, concatString, 0, a.length);
        System.arraycopy(b, 0, concatString, a.length, b.length);
        return concatString;
    }

    //Use to add row which users input.
    public void addRow(String[] row) {
        for (int i = 0; i < columnsName.length; i += 1) {
            get(i).addElement(row[i]);
        }
    }

    //Use to add row which copies from another table.
    public void addRow(DBDatatype[] row) {
        for (int i = 0; i < columnsName.length; i += 1) {
            get(i).addElementWithDataType(row[i]);
        }
    }

    private int getNumColumn() {
        return columnsName.length;
    }

    //Get column whose index is i
    private Column get(int i) {
        return columns.get(columnsName[i]);
    }

    //Join table without same column
    private Table CartesianProductTable(Table a, Table b) {
        //Concat all the names in a and b
        String[] concatColumnNameString = StringArrayConcat(a.columnsName, b.columnsName);
        //Concat all the column types in a and b
        String[] concatColumnTypeString = StringArrayConcat(a.columnsType, b.columnsType);
        //Create a new empty table that is product of two tables
        Table productTable = new Table(concatColumnNameString, concatColumnTypeString);
        //Get the number of rows for two tables.
        int columnLengthA = a.get(0).getSize();
        int columnLengthB = b.get(0).getSize();
        //Get number of columns for two tables.
        int numColumnA = a.getNumColumn();
        int numColumnB = b.getNumColumn();

        //Every row in Table a
        for (int i = 0; i < columnLengthA; i += 1) {
            DBDatatype[] newRow = new DBDatatype[numColumnA + numColumnB];
            //Every column in Table a row i, copy their elemnt.
            for (int j = 0; j < numColumnA; j += 1) {
                newRow[j] = a.get(j).getElementAtIndex(i);
            }
            //Every column in Table b row i, copy their elemnt.
            for (int length = 0; length < columnLengthB; length += 1) {
                for (int index = 0; index < numColumnB; index += 1) {
                    newRow[numColumnA + index] = b.get(index).getElementAtIndex(length);
                }
                productTable.addRow(newRow);
            }
        }
        return productTable;
    }

    //Join table with same column.
    public Table joinTableWithSameColumn(ArrayList<String> sameColumn, Table a, Table b) {

        String[] newColumnsName = new String[a.columnsName.length + b.columnsName.length - sameColumn.size()];
        String[] newColumnsType = new String[newColumnsName.length];
        String[] sameColumnName = sameColumn.toArray(new String[0]);
        //List for columns that will lastly be added into new table.
        LinkedList<Column> columns = new LinkedList<>();
        //List for columnsName that will be used to construct a new table.
        LinkedList<String> columnsName = new LinkedList<>();
        //List for numbers that records which row contains same element.
        LinkedList<Integer> sameElementindex = new LinkedList<>();
        //Initialize to a list that contains every row's index.
        for (int size = 0; size < a.get(0).getSize(); size += 1) {
            sameElementindex.addLast(size);
        }
        //Find the same row
        for (int i = 0; i < sameColumn.size(); i += 1) {
            newColumnsName[i] = new String(sameColumnName[i]);
            columnsName.addLast(newColumnsName[i]);
            Column tempA = a.columns.get(sameColumnName[i]);
            Column tempB = b.columns.get(sameColumnName[i]);
            for (Integer index = 0; index < tempA.getSize(); index += 1) {
                DBDatatype elementA = tempA.getElementAtIndex(index);
                //Remove the index if the element at that index is not equal.
                if (!tempB.elements.contains(elementA)) {
                    sameElementindex.remove((Integer) index);
                }
            }
            newColumnsType[i] = new String(tempA.columnType);
        }
        //How many rows that contain same element.
        int sameElementNumber = sameElementindex.size();
        //Add the same columns to the list first.
        //Add their name and type into the new String[].
        for (int sameColumnIndex = 0; sameColumnIndex < sameColumn.size(); sameColumnIndex += 1) {
            Column tempA = a.columns.get(sameColumnName[sameColumnIndex]);
            Column ColumnWithSameElement = new Column(sameElementNumber, tempA.columnType);
            for (int sameElement = 0; sameElement < sameElementNumber; sameElement += 1) {
                ColumnWithSameElement.addElementWithDataType(tempA.getElementAtIndex(sameElementindex.get(sameElement)));
            }
            columns.addLast(ColumnWithSameElement);
        }
        //Next index to add a column.
        int lastToAdd = sameColumn.size();

        //Add the rest columns of A into the list
        //Add their name and type into the new String[].
        for (int aIndex = 0; aIndex < a.columnsName.length; aIndex += 1) {
            if (!sameColumn.contains(a.columnsName[aIndex])) {
                newColumnsName[lastToAdd] = new String(a.columnsName[aIndex]);
                columnsName.addLast(newColumnsName[lastToAdd]);
                Column temp = a.get(aIndex);
                Column ColumnWithSameElement = new Column(sameElementNumber, temp.columnType);
                for (int sameElement = 0; sameElement < sameElementNumber; sameElement += 1) {
                    ColumnWithSameElement.addElementWithDataType(temp.getElementAtIndex(sameElementindex.get(sameElement)));
                }
                columns.addLast(ColumnWithSameElement);
                newColumnsType[lastToAdd] = new String(temp.columnType);
                lastToAdd += 1;
            }
        }

        //Add the rest columns of B into the list
        //Add their name and type into the new String[].
        for (int bIndex = 0; bIndex < b.columnsName.length; bIndex += 1) {
            if (!sameColumn.contains(b.columnsName[bIndex])) {
                newColumnsName[lastToAdd] = new String(b.columnsName[bIndex]);
                columnsName.addLast(newColumnsName[lastToAdd]);
                Column temp = b.get(bIndex);
                Column ColumnWithSameElement = new Column(sameElementNumber, temp.columnType);
                for (int sameElement = 0; sameElement < sameElementNumber; sameElement += 1) {
                    ColumnWithSameElement.addElementWithDataType(temp.getElementAtIndex(sameElementindex.get(sameElement)));
                }
                columns.addLast(ColumnWithSameElement);
                newColumnsType[lastToAdd] = new String(temp.columnType);
                lastToAdd += 1;
            }
        }

        //Unexpected join error.
        if (lastToAdd > newColumnsName.length) {
            throw new RuntimeException("Error");
        }

        //Create a new table.
        Table joinTable = new Table(newColumnsName, newColumnsType);
        int size = columns.size();
        for (int i = 0; i < size; i += 1) {
            joinTable.columns.put(columnsName.removeFirst(), columns.removeFirst());
        }
        return joinTable;
    }

    public Table joinTable(Table a, Table b) {
        boolean shareSameColumn = false;
        ArrayList<String> sameColumnArrayList = new ArrayList<>();
        //Judge whether they have same columns.
        for (String x : a.columnsName) {
            if (b.columns.containsKey(x)) {
                shareSameColumn = true;
                sameColumnArrayList.add(x);
            }
        }
        if (shareSameColumn) {
            return joinTableWithSameColumn(sameColumnArrayList, a, b);
        } else {
            return CartesianProductTable(a, b);
        }
    }
}
