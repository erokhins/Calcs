package org.hanuna.calcs.syntaxtree;

import org.hanuna.calcs.parser.ExpressionVisitor;

/**
 * @author erokhins
 */
public class SyntaxTreeNodeNumber implements SyntaxTreeNode {
    private String numberStr;

    public SyntaxTreeNodeNumber(String numberStr) {
        this.numberStr = numberStr;
    }

    public String getNumberStr() {
        return numberStr;
    }


    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitNumber(this, l);
    }
}
