package org.hanuna.calcs.polinoms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erokhins
 */
public class Polinom<T> {
    private List<Monom<T>> sum;

    public Polinom() {
        sum = new LinkedList<Monom<T>>();
    }

    public Iterator<Monom<T>> iterator() {
        return sum.iterator();
    }

    public int size() {
        return sum.size();
    }

    public void add(Monom<T> m) {
        sum.add(m);
    }

}
