package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeVar extends ParserNode {
    private int type;
    private String var;

    public ParserNodeVar(int type, String var){
        this.type = type;
        this.var = var;
    }
}
