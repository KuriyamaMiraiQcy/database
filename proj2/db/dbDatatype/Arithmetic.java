package db.dbDatatype;

/**
 * Created by lxjhk on 2/22/17.
 * Email: lxjpub@gmail.com
 */

// Returns DBDatatype

public class Arithmetic {

    public static DBDatatype add(DBDatatype a, DBDatatype b) {
        Object aVal = a.getValue(); //Primitive Types
        Object bVal = b.getValue(); //Primitive Types

        //Handling NaN
        if (aVal instanceof NaN | bVal instanceof NaN) {
            if (a instanceof DBDouble || b instanceof DBDouble) return new DBDouble(new NaN());
            if (a instanceof DBInteger && b instanceof DBInteger) return new DBInteger(new NaN());
            if (a instanceof DBString) return new DBString(new NaN());
            throw new RuntimeException("Unknown types");
        }

        //String + String
        if (aVal instanceof String & bVal instanceof String) {
            return new DBString((String) aVal + (String) bVal);
        }

        // Integer + Integer
        if (aVal instanceof Integer & bVal instanceof Integer) {
            return new DBInteger((int) aVal + (int) bVal);
        }

        // Double + Double
        if (aVal instanceof Double & bVal instanceof Double) {
            return new DBDouble((Double) aVal + (Double) bVal);
        }

        // Integer + Double
        if (aVal instanceof Integer & bVal instanceof Double) {
            return new DBDouble((Double) (((Integer) aVal).doubleValue()) + (Double) bVal);
        }

        // Double + Integer
        if (aVal instanceof Double & bVal instanceof Integer) {
            return new DBDouble((Double) aVal + (Double) (((Integer) bVal).doubleValue()));
        }

        throw new RuntimeException("Unknown Types during addition");
    }

    public static DBDatatype sub(DBDatatype a, DBDatatype b) {
        Object aVal = a.getValue(); //Primitive Types
        Object bVal = b.getValue(); //Primitive Types

        //String
        if (aVal instanceof String || bVal instanceof String) {
            throw new RuntimeException("Sub String Unsupported");
        }

        //Handling NaN
        if (aVal instanceof NaN | bVal instanceof NaN) {
            if (a instanceof DBInteger) return new DBInteger(new NaN());
            if (a instanceof DBDouble) return new DBDouble(new NaN());
            throw new RuntimeException("Unknown types");
        }


        // Integer - Integer
        if (aVal instanceof Integer & bVal instanceof Integer) {
            return new DBInteger((int) aVal - (int) bVal);
        }

        // Double - Double
        if (aVal instanceof Double & bVal instanceof Double) {
            return new DBDouble((Double) aVal - (Double) bVal);
        }

        // Integer - Double
        if (aVal instanceof Integer & bVal instanceof Double) {
            return new DBDouble((Double) (((Integer) aVal).doubleValue()) - (Double) bVal);
        }

        // Double - Integer
        if (aVal instanceof Double & bVal instanceof Integer) {
            return new DBDouble((Double) aVal - (Double) (((Integer) bVal).doubleValue()));
        }

        throw new RuntimeException("Unknown Types during addition");
    }

    public static DBDatatype mul(DBDatatype a, DBDatatype b) {
        Object aVal = a.getValue(); //Primitive Types
        Object bVal = b.getValue(); //Primitive Types

        //String
        if (aVal instanceof String | bVal instanceof String) {
            throw new RuntimeException("Mul String Unsupported");
        }

        //Handling NaN
        if (aVal instanceof NaN | bVal instanceof NaN) {
            if (a instanceof DBInteger) return new DBInteger(new NaN());
            if (a instanceof DBDouble) return new DBDouble(new NaN());
            throw new RuntimeException("Unknown types");
        }


        // Integer * Integer
        if (aVal instanceof Integer & bVal instanceof Integer) {
            return new DBInteger((int) aVal * (int) bVal);
        }

        // Double * Double
        if (aVal instanceof Double & bVal instanceof Double) {
            return new DBDouble((Double) aVal * (Double) bVal);
        }

        // Integer * Double
        if (aVal instanceof Integer & bVal instanceof Double) {
            return new DBDouble((Double) (((Integer) aVal).doubleValue()) * (Double) bVal);
        }

        // Double * Integer
        if (aVal instanceof Double & bVal instanceof Integer) {
            return new DBDouble((Double) aVal * (Double) (((Integer) bVal).doubleValue()));
        }

        throw new RuntimeException("Unknown Types during addition");
    }


    public static DBDatatype div(DBDatatype a, DBDatatype b) {
        Object aVal = a.getValue(); //Primitive Types
        Object bVal = b.getValue(); //Primitive Types

        //String
        if (aVal instanceof String | bVal instanceof String) {
            throw new RuntimeException("Div String Unsupported");
        }

        //Handling NaN
        if (aVal instanceof NaN | bVal instanceof NaN) {
            if (a instanceof DBInteger) return new DBInteger(new NaN());
            if (a instanceof DBDouble) return new DBDouble(new NaN());
            throw new RuntimeException("Unknown types");
        }

        // Integer / Integer
        if (aVal instanceof Integer & bVal instanceof Integer) {
            if ((int) bVal == 0) return new DBInteger(new NaN());
            return new DBInteger((int) aVal / (int) bVal);
        }

        // Double / Double
        if (aVal instanceof Double & bVal instanceof Double) {
            if ((Double) bVal == 0) return new DBDouble(new NaN());
            return new DBDouble((Double) aVal / (Double) bVal);
        }

        // Integer / Double
        if (aVal instanceof Integer & bVal instanceof Double) {
            if ((Double) bVal == 0) return new DBInteger(new NaN());
            return new DBDouble((Double) (((Integer) aVal).doubleValue()) / (Double) bVal);
        }

        // Double / Integer
        if (aVal instanceof Double & bVal instanceof Integer) {
            if ((int) bVal == 0) return new DBDouble(new NaN());
            return new DBDouble((Double) aVal / (Double) (((Integer) bVal).doubleValue()));
        }

        throw new RuntimeException("Unknown Types during addition");
    }


    //TODO: Implement mod
}
