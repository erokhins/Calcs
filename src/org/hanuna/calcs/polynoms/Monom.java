package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public class Monom<T> {
    private final PowerVariables pVar;
    private final T value;

    public Monom(PowerVariables pVar, T value) {
        this.pVar = pVar;
        this.value = value;
    }

    public Monom(int varNumber, T value) {
        this(new SimplePowerVariables(varNumber), value);
    }

    public PowerVariables getPowerVariables() {
        return pVar;
    }

    public T getValue() {
        return value;
    }

}
