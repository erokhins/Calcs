package org.hanuna.calcs.polynoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author erokhins
 */
public class ListPowersVariables extends AbstractPowersVariables {

    public static final ListPowersVariables EMPTY = new ListPowersVariables(Collections.<Integer>emptyList());

    public static PowersVariables mult(PowersVariables a, PowersVariables b) {
        int max = Math.max(a.maxCountVar(), b.maxCountVar());
        List<Integer> l = new ArrayList<Integer>(max);
        for (int i = 0; i < max; i++) {
            l.add(a.getDegree(i) + b.getDegree(i));
        }
        return new ListPowersVariables(l);
    }

    public static PowersVariables copyFromList(List<Integer> pv) {
        List<Integer> list = new ArrayList<Integer>(pv);
        return new ListPowersVariables(list);
    }


    private final List<Integer> vars;

    private ListPowersVariables(List<Integer> vars) {
        this.vars = new ArrayList<Integer>(vars);
    }

    @Override
    public int getDegree(int indexVar) {
        if (indexVar < 0) {
            throw new IllegalArgumentException("index of var < 0");
        }
        if (indexVar >= vars.size()) {
            return 0;
        } else {
            return vars.get(indexVar);
        }
    }

    @Override
    public int maxCountVar() {
        return vars.size();
    }
}
