package org.hanuna.calcs.syntaxtree;

import org.hanuna.calcs.parser.ExpressionVisitor;
import org.hanuna.calcs.lexer.LexerTokenType;

/**
 * @author erokhins
 */
public class SyntaxTreeNodeUnary implements SyntaxTreeNode {
    private LexerTokenType type;
    private SyntaxTreeNode operand;

    public SyntaxTreeNodeUnary(LexerTokenType type, SyntaxTreeNode operand) {
        this.type = type;
        this.operand = operand;
    }

    public LexerTokenType getType() {
        return type;
    }

    public SyntaxTreeNode getOperand() {
        return operand;
    }

    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitUn(this, l);
    }
}
