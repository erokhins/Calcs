package org.hanuna.calcs.polinoms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */
public class ListPowerVariables implements PowerVariables {
    private final List<Integer> vars;

    public ListPowerVariables(List<Integer> vars) {
        this.vars = vars;
    }

    public static ListPowerVariables getEmpty() {
        return new ListPowerVariables(new ArrayList<Integer>(0));
    }

    @Override
    public int getDegree(int numberVar) {
        if (numberVar < 0) {
            throw new IllegalArgumentException("numberVar is < 0!");
        }
        if (numberVar > vars.size()) {
            return 0;
        } else {
            return vars.get(numberVar);
        }
    }

    @Override
    public int countVars() {
        return vars.size();
    }

    @Override
    public boolean equals(PowerVariables pv) {
        boolean t = true;
        int max = Math.max(pv.countVars(), this.countVars());
        for (int i = 0; i< max; i++) {
            t &= pv.getDegree(i) == this.getDegree(i);
        }
        return t;
    }

    public static PowerVariables mult(PowerVariables a, PowerVariables b) {
        int max = Math.max(a.countVars(), b.countVars());
        List<Integer> l = new ArrayList<Integer>(max);
        for (int i = 0; i < max; i++) {
            l.add(a.getDegree(i) + b.getDegree(i));
        }
        return new ListPowerVariables(l);
    }
}
