package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public interface VarTable<L> {
    public L get(String name);
    public void put(String name, L value);
}
