package org.hanuna.calcs.fields;

/**
 * @author erokhins
 */
public interface Field<T> extends Ring<T> {

    T divide(T a, T b) throws FieldCalculateException;
    T inverse(T a) throws FieldCalculateException;

}
