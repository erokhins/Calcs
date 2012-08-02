package org.hanuna.calcs.polynoms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */
public class ListPolynom<T> implements Polynom<T> {

    public static <T> ListPolynom<T> singleVarPolynom(int indexVar, T value) {
        return new ListPolynom<T>(Monom.SingleVarMonom(indexVar, value));
    }

    public static <T> ListPolynom<T> constantPolynom(T value) {
        return new ListPolynom<T>(Monom.constMonom(value));
    }

    private final List<Monom<T>> sumMonom;

    public ListPolynom(Polynom<T> p) {
        sumMonom = new ArrayList<Monom<T>>(p.size());
        for (int i = 0; i < p.size(); i++) {
            sumMonom.add(p.getMonom(i));
        }
    }

    public ListPolynom(Monom<T> m) {
        sumMonom = new ArrayList<Monom<T>>(1);
        sumMonom.add(m);
    }

    @Override
    public int size() {
        return sumMonom.size();
    }

    @Override
    public Monom<T> getMonom(int numberMonom) {
        checkNumberMonom(numberMonom, size());
        return sumMonom.get(numberMonom);
    }

    public static void checkNumberMonom(int numberMonom, int size) {
        if (numberMonom < 0 || numberMonom >= size) {
            throw new IllegalArgumentException("incorrect number monom = " + numberMonom + ", count monom in this Polynom =" + size);
        }
    }
}
