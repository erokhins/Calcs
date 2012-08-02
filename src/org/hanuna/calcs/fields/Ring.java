package org.hanuna.calcs.fields;

/**
 * @author erokhins
 */
public interface Ring<T> {

    T getZero();
    T getUnityElement();
    boolean isZero(T a);
    boolean isUnityElement(T a);

    T add(T a, T b);
    T negative(T a);

    T mult(T a, T b);
}
