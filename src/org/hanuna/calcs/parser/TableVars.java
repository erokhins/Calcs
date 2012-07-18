package org.hanuna.calcs.parser;

import java.util.HashMap;

/**
 * @author erokhins
 */
public class TableVars {
    private final HashMap<String, Integer> vars;

    public TableVars() {
        this.vars = new HashMap<String, Integer>();
    }

    public Integer get(String s) {
        return this.vars.get(s);
    }

    public void put(String s, Integer k) {
        this.vars.put(s, k);
    }


}
