package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public abstract class AbstractPowerVariables implements PowerVariables {


    public boolean equals(PowerVariables pv) {
        boolean t = true;
        int max = Math.max(pv.maxVarNumber(), this.maxVarNumber());
        for (int i = 0; i < max; i++) {
            t &= pv.getDegree(i) == this.getDegree(i);
        }
        return t;
    }

}
