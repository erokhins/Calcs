package org.hanuna.calcs.vartable;

import org.hanuna.calcs.vartable.VarTable;

import java.util.HashMap;

/**
 * @author erokhins
 */
public class IntegerVarTable implements VarTable<Integer> {
    private final HashMap<String, Integer> vars;

    public IntegerVarTable() {
        this.vars = new HashMap<String, Integer>();
    }

    @Override
    public Integer get(String s) {
        return this.vars.get(s);
    }

    @Override
    public void put(String s, Integer k) {
        this.vars.put(s, k);
    }


}
