package org.hanuna.calcs.fields;

/**
 * @author erokhins
 */
public interface Field<T> {

    T getZero();
    T getUnityElement();
    boolean isZero(T a);
    boolean isUnityElement(T a);

    T add(T a, T b);
    T subtract(T a, T b);
    T negative(T a);

    T mult(T a, T b);
    T divide(T a, T b) throws FieldCalculateException;
    T inverse(T a) throws FieldCalculateException;

    String toStr(T a);
}
