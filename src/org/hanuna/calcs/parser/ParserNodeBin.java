package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeBin implements ParserNode {
    private ParserNode left;
    private ParserNode right;
    private int type;


    public ParserNodeBin(ParserNode left, ParserNode right, int type){
        this.left = left;
        this.right = right;
        this.type = type;
    }


    public int getType() {
        return type;
    }

    public ParserNode getLeft() {
        return left;
    }

    public ParserNode getRight() {
        return right;
    }


    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws ExpressionVisitorError {
        return visitor.visitBin(this);
    }
}
