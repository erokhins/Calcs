package org.hanuna.calcs.polynoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author erokhins
 */
public class ListPowerVariables extends AbstractPowerVariables {

    public static final ListPowerVariables EMPTY = new ListPowerVariables(Collections.<Integer>emptyList());

    public static PowerVariables mult(PowerVariables a, PowerVariables b) {
        int max = Math.max(a.maxVarNumber(), b.maxVarNumber());
        List<Integer> l = new ArrayList<Integer>(max);
        for (int i = 0; i < max; i++) {
            l.add(a.getDegree(i) + b.getDegree(i));
        }
        return new ListPowerVariables(l);
    }

    private final List<Integer> vars;

    public ListPowerVariables(List<Integer> vars) {
        this.vars = vars;
    }

    @Override
    public int getDegree(int numberVar) {
        if (numberVar < 0) {
            throw new IllegalArgumentException("numberVar is < 0!");
        }
        if (numberVar >= vars.size()) {
            return 0;
        } else {
            return vars.get(numberVar);
        }
    }

    @Override
    public int maxVarNumber() {
        return vars.size();
    }
}
