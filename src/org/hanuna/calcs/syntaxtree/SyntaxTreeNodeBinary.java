package org.hanuna.calcs.syntaxtree;

import org.hanuna.calcs.parser.ExpressionVisitor;
import org.hanuna.calcs.lexer.LexerTokenType;

/**
 * @author erokhins
 */
public class SyntaxTreeNodeBinary implements SyntaxTreeNode {
    private SyntaxTreeNode left;
    private SyntaxTreeNode right;
    private LexerTokenType type;


    public SyntaxTreeNodeBinary(SyntaxTreeNode left, SyntaxTreeNode right, LexerTokenType type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }


    public LexerTokenType getType() {
        return type;
    }

    public SyntaxTreeNode getLeft() {
        return left;
    }

    public SyntaxTreeNode getRight() {
        return right;
    }


    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitBin(this, l);
    }
}
