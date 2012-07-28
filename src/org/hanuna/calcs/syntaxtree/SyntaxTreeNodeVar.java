package org.hanuna.calcs.syntaxtree;

import org.hanuna.calcs.parser.ExpressionVisitor;

/**
 * @author erokhins
 */
public class SyntaxTreeNodeVar implements SyntaxTreeNode {
    private String var;

    public SyntaxTreeNodeVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitVar(this, l);
    }
}
