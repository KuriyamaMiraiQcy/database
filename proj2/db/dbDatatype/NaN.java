package db.dbDatatype;

/**
 * Created by 62339 on 2017/2/22.
 */

public class NaN {

    @Override
    public String toString() {
        return "NaN";
    }

    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }

        if (x instanceof NaN) {
            return true;
        }
        return false;
    }

}
