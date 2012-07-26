package org.hanuna.calcs.polinoms;

import org.hanuna.calcs.fields.Ring;

/**
 * @author erokhins
 */
public class PolinomRing<T> implements Ring<Polinom<T>> {

    private Ring<T> ring;
    public PolinomRing(Ring<T> ring) {
        this.ring = ring;
    }

    @Override
    public Polinom<T> getZero() {
        ListMonom<T> m = new ListMonom<T>(ListPowerVariables.getEmpty(), ring.getZero());
        return new ListPolinom<T>(m);
    }

    @Override
    public Polinom<T> getUnityElement() {
        ListMonom<T> m = new ListMonom<T>(ListPowerVariables.getEmpty(), ring.getUnityElement());
        return new ListPolinom<T>(m);
    }

    @Override
    public boolean isZero(Polinom<T> a) {
        boolean t = true;
        for (int i = 0; i < a.size(); i++) {
            T e = a.getMonom(i).getValue();
            t &= ring.isZero(e);
        }

        return t;
    }

    @Override
    public boolean isUnityElement(Polinom<T> a) {
        boolean t = true;
        PowerVariables empty = ListPowerVariables.getEmpty();
        T e = ring.getZero();
        for (int i = 0; i < a.size(); i++) {
            Monom<T> m = a.getMonom(i);
            if (empty.equals(m.getPowerVariables())) {
                e = ring.add(e, m.getValue());
            } else {
                t &= ring.isZero(m.getValue());
            }
        }
        return t && ring.isUnityElement(e);
    }

    @Override
    public Polinom<T> add(final Polinom<T> a, final Polinom<T> b) {
        return new Polinom<T>() {
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
    public Polinom<T> negative(final Polinom<T> a) {
        return new Polinom<T>() {
            @Override
            public int size() {
                return a.size();
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                final Monom<T> m = a.getMonom(numberMonom);

                return new ListMonom<T>(m.getPowerVariables(), m.getValue());
            }
        };
    }

    @Override
    public Polinom<T> mult(final Polinom<T> a, final Polinom<T> b) {
        return mult(a, b, false);
    }


    public Polinom<T> mult(final Polinom<T> a, final Polinom<T> b, boolean linked) {
        Polinom<T> p = new Polinom<T>() {
            @Override
            public int size() {
                return a.size() * b.size();
            }

            @Override
            public Monom<T> getMonom(int numberMonom) {
                int an = numberMonom % a.size();
                int bn = (numberMonom - an) / b.size();
                Monom<T> am = a.getMonom(an);
                Monom<T> bm = a.getMonom(bn);
                T v = ring.mult(am.getValue(), bm.getValue());

                PowerVariables pv = ListPowerVariables.mult(am.getPowerVariables(), bm.getPowerVariables());
                return new ListMonom<T>(pv, v);
            }
        };
        return linked ? p : new ListPolinom<T>(p);
    }

    @Override
    public Polinom<T> parseNumber(String s) {
        T v = ring.parseNumber(s);
        Monom<T> m = new ListMonom<T>(ListPowerVariables.getEmpty(), v);
        return new ListPolinom<T>(m);
    }

}
