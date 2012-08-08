package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public abstract class Polynom<T> {

    public abstract int size();
    public abstract Monom<T> getMonom(int numberMonom);

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            Monom<T> m = this.getMonom(i);
            PowersVariables pv = m.getPowersVariables();
            T v = m.getValue();
            if (s.length() != 0) {
                s.append(" + ");
            }
            s.append(v + " ");
            for (int j = 0; j < pv.maxCountVar(); j++) {
                int d = pv.getDegree(j);
                if (d != 0) {
                    if (d == 1) {
                        s.append("* x_" + j + " ");
                    } else {
                        s.append("* x_" + j + "^" + d + " ");
                    }
                }
            }
        }

        return s.toString();
    }

}
