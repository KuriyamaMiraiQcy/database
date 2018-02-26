package db;

import java.util.HashMap;

public class Database {
    static HashMap<String, Table> tables;

    public Database() {
        tables = new HashMap<>();
    }

    //First drop the contents of table, then delete the whole object.
    static void dropTable(String name) {
        Table temp = getTable(name);
        temp.dropTable();
        tables.remove(name, temp);
    }


    static String addTable(String name, Table tbl) {
        try {
            tables.put(name, tbl);
            return null;
        } catch (RuntimeException e) {
            return ("ERROR: " + e.getMessage());
        }
    }

    static Table getTable(String name) {
        if (!tables.containsKey(name)) {
            throw new RuntimeException("There is not any table named "
                    + name + " in the database.");
        }
        return tables.get(name);
    }

    public String transact(String query) {
        String[] a = new String[1];
        a[0] = query;
        return Parse.main(a);
    }
}
