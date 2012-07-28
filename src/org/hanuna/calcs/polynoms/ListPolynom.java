package org.hanuna.calcs.polynoms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */
public class ListPolynom<T> implements Polynom<T> {

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

    public ListPolynom(int varNumber, T value) {
        this(new Monom<T>(varNumber, value));
    }

    @Override
    public int size() {
        return sumMonom.size();
    }

    @Override
    public Monom<T> getMonom(int numberMonom) {
        if (numberMonom < 0 || numberMonom >= size()) {
            throw new IllegalArgumentException("incorrect numberMonom = " + numberMonom + " size =" + size());
        }
        return sumMonom.get(numberMonom);
    }
}
