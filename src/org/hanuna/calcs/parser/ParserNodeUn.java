package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeUn extends ParserNode {
    private int type;
    private ParserNode left;

    public ParserNodeUn(int type, ParserNode left){
        this.type = type;
        this.left = left;
    }
}
