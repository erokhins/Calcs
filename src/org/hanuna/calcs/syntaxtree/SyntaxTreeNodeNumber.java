package org.hanuna.calcs.syntaxtree;

import org.hanuna.calcs.parser.ExpressionVisitor;

/**
 * @author erokhins
 */
public class SyntaxTreeNodeNumber implements SyntaxTreeNode {
    private double number;

    public SyntaxTreeNodeNumber(double number) {
        this.number = number;
    }

    public double getNumberStr() {
        return number;
    }


    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitNumber(this, l);
    }
}
