package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public class Monom<T> {

    public static <T> Monom<T> constMonom(T value) {
        return new Monom<T>(new SingleVariable(-1), value);
    }

    public static <T> Monom<T> SingleVarMonom(int indexVar, T value) {
        return new Monom<T>(new SingleVariable(indexVar), value);
    }


    private final PowersVariables pVar;
    private final T value;

    public Monom(PowersVariables pVar, T value) {
        this.pVar = pVar;
        this.value = value;
    }

    public PowersVariables getPowersVariables() {
        return pVar;
    }

    public T getValue() {
        return value;
    }

}
