package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public abstract class AbstractPowersVariables implements PowersVariables {

    public boolean equalPV(PowersVariables pv) {
        boolean t = true;
        int max = Math.max(pv.maxCountVar(), this.maxCountVar());
        for (int i = 0; i < max && t; i++) {
            t &= pv.getDegree(i) == this.getDegree(i);
        }
        return t;
    }

}
