package db;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse {
    // Various common constructs, simplifies parsing.
    private static final String REST = "\\s*(.*)\\s*",
            COMMA = "\\s*,\\s*",
            AND = "\\s+and\\s+";

    // Stage 1 syntax, contains the command name.
    private static final Pattern CREATE_CMD = Pattern.compile("create table " + REST),
            LOAD_CMD = Pattern.compile("load " + REST),
            STORE_CMD = Pattern.compile("store " + REST),
            DROP_CMD = Pattern.compile("drop table " + REST),
            INSERT_CMD = Pattern.compile("insert into " + REST),
            PRINT_CMD = Pattern.compile("print " + REST),
            SELECT_CMD = Pattern.compile("select " + REST);

    // Stage 2 syntax, contains the clauses of commands.
    private static final Pattern CREATE_NEW = Pattern.compile("(\\S+)\\s+\\((\\S+\\s+\\S+\\s*" +
            "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
            SELECT_CLS = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
                    "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
                    "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+" +
                    "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
            CREATE_SEL = Pattern.compile("(\\S+)\\s+as select\\s+" +
                    SELECT_CLS.pattern()),
            INSERT_CLS = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
                    "\\s*(?:,\\s*.+?\\s*)*)");

    public static String main(String[] args) {
        if (args.length != 1) {
            System.err.println("Expected a single query argument");
            return "ERROR";
        }

        return eval(args[0]);
    }

    private static String eval(String query) {
        Matcher m;
        if ((m = CREATE_CMD.matcher(query)).matches()) {
            return createTable(m.group(1));
        } else if ((m = LOAD_CMD.matcher(query)).matches()) {
            return loadTable(m.group(1));
        } else if ((m = STORE_CMD.matcher(query)).matches()) {
            return storeTable(m.group(1));
        } else if ((m = DROP_CMD.matcher(query)).matches()) {
            return dropTable(m.group(1));
        } else if ((m = INSERT_CMD.matcher(query)).matches()) {
            return insertRow(m.group(1));
        } else if ((m = PRINT_CMD.matcher(query)).matches()) {
            return printTable(m.group(1));
        } else if ((m = SELECT_CMD.matcher(query)).matches()) {
            return select(m.group(1));
        }
        return null;
    }

    private static String createTable(String expr) {
        Matcher m;
        if ((m = CREATE_NEW.matcher(expr)).matches()) {
            return createNewTable(m.group(1), m.group(2).split(COMMA));
        } else if ((m = CREATE_SEL.matcher(expr)).matches()) {
            return createSelectedTable(m.group(1), m.group(2), m.group(3), m.group(4));
        }
        return "ERROR: Unexpected query";
    }

    private static String createNewTable(String name, String[] cols) {
        int colLength = cols.length;
        String[] columnNames = new String[colLength];
        String[] columnTypes = new String[colLength];

        try {
            for (int i = 0; i < colLength; i += 1) {
                String[] temp = cols[i].split(" ");
                if (temp.length != 2) {
                    throw new RuntimeException("String split failed!");
                }
                columnNames[i] = temp[0];
                columnTypes[i] = temp[1];
            }
            Table newTable = new Table(columnNames, columnTypes);
            Database.addTable(name, newTable);
            return "";
        } catch (Exception e) {
            return ("ERROR: " + e.getMessage());
        }

        /*StringJoiner joiner = new StringJoiner(", ");
        for (int i = 0; i < cols.length - 1; i++) {
            joiner.add(cols[i]);
        }

        String colSentence = joiner.toString() + " and " + cols[cols.length - 1];
        System.out.printf("You are trying to create a table named %s with the columns %s\n", name, colSentence);
        */

    }

    private static String createSelectedTable(String name, String exprs, String tables, String conds) {
        System.out.printf("You are trying to create a table named %s by selecting these expressions:" +
                " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", name, exprs, tables, conds);
        return null;
    }

    private static String loadTable(String name) {
        //System.out.printf("You are trying to load the table named %s\n", name);
        return Table.loadTable(name);
    }

    private static String storeTable(String name) {
        String result = Table.storeTable(name);
        if (result == "") {
            return result;
        }
        return "ERROR: " + result;

    }

    private static String dropTable(String name) {
        //System.out.printf("You are trying to drop the table named %s\n", name);
        try {
            Database.dropTable(name);
            return "";
        } catch (RuntimeException e) {
            return ("ERROR: " + e.getMessage());
        }
    }

    private static String insertRow(String expr) {
        Matcher m = INSERT_CLS.matcher(expr);
        if (!m.matches()) {
            System.err.printf("Malformed insert: %s\n", expr);
            return "insert query invaliid";
        }

        //System.out.printf("You are trying to insert the row \"%s\" into the table %s\n", m.group(2), m.group(1));
        try {
            Table insertTable = Database.getTable(m.group(1));
            String[] splitBySpace = m.group(2).split(",");
            for (int i = 0; i < splitBySpace.length - 1; i += 1) {
                splitBySpace[i] = splitBySpace[i].split(",")[0];
            }
            insertTable.addRow(splitBySpace);
            return "";
        } catch (RuntimeException e ) {
            return ("ERROR: " + e.getMessage());
        }
    }

    private static String printTable(String name) {
        //System.out.printf("You are trying to print the table named %s\n", name);
        try {
            String a = Database.getTable(name).printTable();
            return a;
        } catch (RuntimeException e) {
            return ("ERROR: " + e.getMessage());
        }
    }

    private static String select(String expr) {
        Matcher m = SELECT_CLS.matcher(expr);
        if (!m.matches()) {
            System.err.printf("Malformed select: %s\n", expr);
            return null;
        }

        return select(m.group(1), m.group(2), m.group(3));
    }

    private static String select(String exprs, String tables, String conds) {
        //System.out.printf("You are trying to select these expressions:" +
        //        " '%s' from the join of these tables: '%s', filtered by these conditions: '%s'\n", exprs, tables, conds);
        if (exprs.equals("*")) {
            String[] tablesArray = tables.split(",");
            Table temp = Database.getTable(tablesArray[0]);
            for (int i = 0; i < tablesArray.length - 1; i += 1) {
                temp = temp.joinTable(temp, Database.getTable(tablesArray[i + 1]));
            }
            return temp.printTable();
        }
        return null;
    }
}
