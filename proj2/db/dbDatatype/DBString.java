package db.dbDatatype;

/**
 * Created by 62339 on 2017/2/22.
 */

public class DBString implements DBDatatype {

    protected boolean isNaN = false;
    protected boolean isNOVALUE = false;
    protected String str;

    /********************************************/
    public DBString(String n) {
        str = n;
    }

    public DBString(DBString n) {
        isNaN = n.isNaN;
        isNOVALUE = n.isNOVALUE;
        str = n.str;
    }

    public DBString(NaN n) {
        this.isNaN = true;
    }

    public DBString(NOVALUE n) {
        this.isNOVALUE = true;
    }


    static public DBDatatype getNaN() {
        return new DBString(new NaN());
    }

    static public DBDatatype getNOVALUE() {
        return new DBString(new NaN());
    }



    /********************************************/

    @Override
    public Object getValue() {
        if (this.isNaN) {
            return new NaN();
        }
        if (this.isNOVALUE) {
            return "";
        }
        return str;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public boolean isNaN() {
        return this.isNaN;
    }

    public boolean isNOVALUE() {
        return this.isNOVALUE;
    }

    public DBDatatype clone() {
        return this;
    }

    //Method equals to judge whether two Objects are equal.
    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }

        if (x instanceof DBDatatype) {
            return compareTo((DBDatatype) x) == 0 ? true : false;
        } else {
            return false;
        }
    }

    //TODO: Implement equals and Comparator methods

    @Override
    public int compareTo(DBDatatype o) {
        Object aVal = this.getValue(); //Primitive Types
        Object bVal = o.getValue(); //Primitive Types

        if (!(o instanceof DBString)) throw new RuntimeException("Cannot compare non-String with String");

        //NaN should treat it as being larger than all other values except itself, to which it should be equal.
        if (o.isNaN() && this.isNaN()) return 0;
        if (this.isNaN()) return 1;
        if (o.isNaN()) return -1;

        //Any comparison operation that has a NOVALUE as one of its operands should evaluate to false
        //Here, NOVALUE is smaller than all other values except itself
        //TODO:Must return False, Override this behaviour later.
        if (o.isNOVALUE() && this.isNOVALUE()) return 0;
        if (this.isNOVALUE()) return -1;
        if (o.isNOVALUE()) return 1;

        return ((String) aVal).compareTo((String) bVal);

    }

    @Override
    public String print() {
        if (isNaN()) {
            return "NaN";
        } else if (isNOVALUE()) {
            return "NOVALUE";
        } else {
            try {
                return "'" + getValue().toString().split("'")[1] + "'";
            } catch (Exception e) {
                return "'" + getValue().toString() + "'";
            }
        }
    }
}