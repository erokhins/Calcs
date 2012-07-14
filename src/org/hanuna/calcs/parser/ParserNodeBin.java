package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeBin extends ParserNode {
    private ParserNode left;
    private ParserNode right;
    private int type;


    public ParserNodeBin(ParserNode left, ParserNode right, int type){
        this.left = left;
        this.right = right;
        this.type = type;
    }
}
