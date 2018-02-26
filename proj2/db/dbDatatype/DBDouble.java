package db.dbDatatype;

import java.text.DecimalFormat;

/**
 * Created by 62339 on 2017/2/22.
 */

public class DBDouble implements DBDatatype {
    protected boolean isNaN = false;
    protected boolean isNOVALUE = false;
    protected Double num;

    /********************************************/

    public DBDouble(Double n) {
        num = new Double(n);
    }

    public DBDouble(int n) {
        num = new Double(n);
    }

    public DBDouble(DBDouble n) {
        isNaN = n.isNaN;
        isNOVALUE = n.isNOVALUE;
        num = n.num;
    }

    public DBDouble(NaN n) {
        this.isNaN = true;
    }

    public DBDouble(NOVALUE n) {
        this.isNOVALUE = true;
    }

    static public DBDatatype getNaN() {
        return new DBDouble(new NaN());
    }

    static public DBDatatype getNOVALUE() {
        return new DBDouble(new NaN());
    }

    /********************************************/

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    @Override
    public Object getValue() {
        //Return the underlying primitive type
        if (this.isNaN) {
            return new NaN();
        }
        if (this.isNOVALUE) {
            return new Double(0.0);
        }
        return this.num;
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

    @Override
    public int compareTo(DBDatatype o) {
        Object aVal = this.getValue(); //Primitive Types
        Object bVal = o.getValue(); //Primitive Types

        if (o instanceof DBString) throw new RuntimeException("Cannot compare Double with String");

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

        if ((double) aVal - (double) bVal == 0) return 0;
        if ((double) aVal - (double) bVal > 0) return 1;
        if ((double) aVal - (double) bVal < 0) return -1;

        throw new RuntimeException("No matching return statement in compareTo");
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

    @Override
    public String print() {
        //TODO: Use Override instead of formatting the output here
        if (isNaN()) {
            return "NaN";
        } else if (isNOVALUE()) {
            return "NOVALUE";
        } else {
            DecimalFormat df = new DecimalFormat("###.000");
            return df.format(num);
        }
    }
}

