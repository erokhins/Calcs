package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeUn implements ParserNode {
    private int type;
    private ParserNode left;

    public ParserNodeUn(int type, ParserNode left){
        this.type = type;
        this.left = left;
    }

    public int getType() {
        return type;
    }

    public ParserNode getLeft() {
        return left;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor)  throws ExpressionVisitorError {
        return visitor.visitUn(this);
    }
}
