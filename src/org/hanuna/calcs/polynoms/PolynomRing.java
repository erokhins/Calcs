package org.hanuna.calcs.polynoms;

import org.hanuna.calcs.fields.Ring;

import java.util.Iterator;
import java.util.LinkedList;
import static org.hanuna.calcs.polynoms.ListPolynom.*;

/**
 * @author erokhins
 */
public class PolynomRing<T> implements Ring<Polynom<T>> {

    private Ring<T> ring;

    public PolynomRing(Ring<T> ring) {
        this.ring = ring;
    }

    @Override
    public Polynom<T> getZero() {
        Monom<T> m = new Monom<T>(ListPowersVariables.EMPTY, ring.getZero());
        return new ListPolynom<T>(m);
    }

    @Override
    public Polynom<T> getUnityElement() {
        Monom<T> m = new Monom<T>(ListPowersVariables.EMPTY, ring.getUnityElement());
        return new ListPolynom<T>(m);
    }

    @Override
    public boolean isZero(Polynom<T> a) {
        a = simplify(a);
        if (a.size() == 1) {
            T value = a.getMonom(0).getValue();
            if (ring.isZero(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isUnityElement(Polynom<T> a) {
        a = simplify(a);
        if (a.size() == 1) {
            Monom<T> m = a.getMonom(0);
            if (ring.isUnityElement(m.getValue()) && ListPowersVariables.EMPTY.equalPV(m.getPowersVariables())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Polynom<T> add(final Polynom<T> a, final Polynom<T> b) {
        return new Polynom<T>() {

            private final int size = a.size() + b.size();

            @Override
            public int size() {
                return size;
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                checkNumberMonom(numberMonom, size());
                if (numberMonom < a.size()) {
                    return a.getMonom(numberMonom);
                } else {
                    return b.getMonom(numberMonom - a.size());
                }
            }

        };
    }



    @Override
    public Polynom<T> negative(final Polynom<T> a) {
        return new Polynom<T>() {

            @Override
            public int size() {
                return a.size();
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                checkNumberMonom(numberMonom, size());
                final Monom<T> m = a.getMonom(numberMonom);
                return new Monom<T>(m.getPowersVariables(), ring.negative(m.getValue()));
            }

        };
    }


    @Override
    public Polynom<T> mult(final Polynom<T> a, final Polynom<T> b) {
        Polynom<T> p = new Polynom<T>() {

            private final int size = a.size() * b.size();
            @Override
            public int size() {
                return size;
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                checkNumberMonom(numberMonom, size());
                int an = numberMonom % a.size();
                int bn = (numberMonom - an) / b.size();
                Monom<T> am = a.getMonom(an);
                Monom<T> bm = b.getMonom(bn);
                T v = ring.mult(am.getValue(), bm.getValue());

                PowersVariables pv = ListPowersVariables.mult(am.getPowersVariables(), bm.getPowersVariables());
                return new Monom<T>(pv, v);
            }

        };
        return simplify(p);
    }


    public Polynom<T> simplify(Polynom<T> p) {
        Polynom<T> new_p = null;
        LinkedList<Monom<T>> listMonoms = new LinkedList<Monom<T>>();
        for (int i = 0; i < p.size(); i++) {
            listMonoms.add(p.getMonom(i));
        }

        while (! listMonoms.isEmpty()) {
            PowersVariables currentPV = listMonoms.getFirst().getPowersVariables();
            T value = ring.getZero();

            for (Iterator<Monom<T>> i = listMonoms.iterator(); i.hasNext(); ) {
                Monom<T> currentMonom = i.next();
                if (currentPV.equalPV(currentMonom.getPowersVariables())) {
                    value = ring.add(value, currentMonom.getValue());
                    i.remove();
                }
            }
            if (! ring.isZero(value)) {
                Monom<T> new_m = new Monom<T>(currentPV, value);
                if (new_p == null) {
                    new_p = new ListPolynom<T>(new_m);
                } else {
                    new_p = add(new_p, new ListPolynom<T>(new_m));
                }
            }
        }
        if (new_p == null) {
            return this.getZero();
        }

        return new ListPolynom<T>(new_p);
    }

}
