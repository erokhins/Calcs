package org.hanuna.calcs.parser;

import java.util.HashMap;

/**
 * @author erokhins
 */
public class ListOfVars {
    private final HashMap<String,Integer> vars;

    public ListOfVars() {
        this.vars = new HashMap();
    }

    public Integer get(String s) {
        return this.vars.get(s);
    }

    public void put(String s, Integer k) {
        this.vars.put(s, k);
    }


}
