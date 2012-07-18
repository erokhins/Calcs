package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeBinary implements ParserNode {
    private ParserNode left;
    private ParserNode right;
    private LexerTokenType type;


    public ParserNodeBinary(ParserNode left, ParserNode right, LexerTokenType type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }


    public LexerTokenType getType() {
        return type;
    }

    public ParserNode getLeft() {
        return left;
    }

    public ParserNode getRight() {
        return right;
    }


    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitBin(this);
    }
}
