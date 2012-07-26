package org.hanuna.calcs.polinoms;

/**
 * @author erokhins
 */
public interface Monom<T> {

    public PowerVariables getPowerVariables();
    public T getValue();
}
