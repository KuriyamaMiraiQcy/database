package db.dbDatatype;


/**
 * Created by lxjhk on 2/22/17.
 * Email: lxjpub@gmail.com
 */
public interface DBDatatype extends Comparable<DBDatatype> {
    Object getValue();

    boolean equals(Object x);

    boolean isNaN();

    boolean isNOVALUE();

    DBDatatype clone();


    default String print() {
        if (isNaN()) {
            return "NaN";
        } else if (isNOVALUE()) {
            return "NOVALUE";
        } else {
            return getValue().toString();
        }
    }

}
