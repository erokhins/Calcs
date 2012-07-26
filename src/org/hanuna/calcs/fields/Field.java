package org.hanuna.calcs.fields;

/**
 * @author erokhins
 */
public interface Field<T> extends Ring<T> {

    T inverse(T a) throws FieldCalculateException;

}
