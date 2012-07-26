package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeUnary implements ParserNode {
    private LexerTokenType type;
    private ParserNode operand;

    public ParserNodeUnary(LexerTokenType type, ParserNode operand) {
        this.type = type;
        this.operand = operand;
    }

    public LexerTokenType getType() {
        return type;
    }

    public ParserNode getOperand() {
        return operand;
    }

    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitUn(this, l);
    }
}
