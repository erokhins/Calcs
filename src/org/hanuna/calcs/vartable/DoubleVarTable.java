package org.hanuna.calcs.vartable;

import java.util.HashMap;

/**
 * @author erokhins
 */
public class DoubleVarTable implements VarTable<Double> {
    private final HashMap<String, Double> vars;

    public DoubleVarTable() {
        this.vars = new HashMap<String, Double>();
    }

    @Override
    public Double get(String s) {
        return this.vars.get(s);
    }

    @Override
    public void put(String s, Double k) {
        this.vars.put(s, k);
    }


}
