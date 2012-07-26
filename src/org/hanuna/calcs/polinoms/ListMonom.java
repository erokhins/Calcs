package org.hanuna.calcs.polinoms;

/**
 * @author erokhins
 */
public class ListMonom<T> implements Monom<T> {
    protected final PowerVariables pVar;
    private final T value;

    public ListMonom(PowerVariables pVar, T value) {
        this.pVar = pVar;
        this.value = value;
    }



    @Override
    public PowerVariables getPowerVariables() {
        return pVar;
    }

    @Override
    public T getValue() {
        return value;
    }
}
