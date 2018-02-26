package db.dbDatatype;

/**
 * Created by 62339 on 2017/2/22.
 */
public class NOVALUE {

    @Override
    public String toString() {
        return "NOVALUE";
    }


    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }

        if (x instanceof NOVALUE) {
            return true;
        }
        return false;
    }

}
