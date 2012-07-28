package org.hanuna.calcs.polynoms;

import org.hanuna.calcs.fields.Ring;

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
        Monom<T> m = new Monom<T>(ListPowerVariables.EMPTY, ring.getZero());
        return new ListPolynom<T>(m);
    }

    @Override
    public Polynom<T> getUnityElement() {
        Monom<T> m = new Monom<T>(ListPowerVariables.EMPTY, ring.getUnityElement());
        return new ListPolynom<T>(m);
    }

    @Override
    public boolean isZero(Polynom<T> a) {
        boolean t = true;
        for (int i = 0; i < a.size(); i++) {
            T e = a.getMonom(i).getValue();
            t &= ring.isZero(e);
        }

        return t;
    }

    @Override
    public boolean isUnityElement(Polynom<T> a) {
        boolean t = true;
        T e = ring.getZero();
        for (int i = 0; i < a.size(); i++) {
            Monom<T> m = a.getMonom(i);
            if (ListPowerVariables.EMPTY.equals(m.getPowerVariables())) {
                e = ring.add(e, m.getValue());
            } else {
                t &= ring.isZero(m.getValue());
            }
        }
        return t && ring.isUnityElement(e);
    }

    @Override
    public Polynom<T> add(final Polynom<T> a, final Polynom<T> b) {
        return new Polynom<T>() {
            @Override
            public int size() {
                return a.size() + b.size();
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                if (numberMonom < 0 || numberMonom >= size()) {
                    throw new IllegalArgumentException("incorrect numberMonom = " + numberMonom + " size =" + size());
                }
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
                final Monom<T> m = a.getMonom(numberMonom);

                return new Monom<T>(m.getPowerVariables(), m.getValue());
            }
        };
    }

    @Override
    public Polynom<T> mult(final Polynom<T> a, final Polynom<T> b) {
        return mult(a, b, false);
    }


    public Polynom<T> mult(final Polynom<T> a, final Polynom<T> b, boolean linked) {
        Polynom<T> p = new Polynom<T>() {
            @Override
            public int size() {
                return a.size() * b.size();
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                int an = numberMonom % a.size();
                int bn = (numberMonom - an) / b.size();
                Monom<T> am = a.getMonom(an);
                Monom<T> bm = b.getMonom(bn);
                T v = ring.mult(am.getValue(), bm.getValue());

                PowerVariables pv = ListPowerVariables.mult(am.getPowerVariables(), bm.getPowerVariables());
                return new Monom<T>(pv, v);
            }
        };
        return linked ? p : new ListPolynom<T>(p);
    }

    @Override
    public Polynom<T> parseNumber(String s) {
        T v = ring.parseNumber(s);
        Monom<T> m = new Monom<T>(ListPowerVariables.EMPTY, v);
        return new ListPolynom<T>(m);
    }

}
