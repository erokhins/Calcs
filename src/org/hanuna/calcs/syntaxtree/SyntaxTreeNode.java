package org.hanuna.calcs.syntaxtree;

import org.hanuna.calcs.parser.ExpressionVisitor;

/**
 * @author erokhins
 */
public interface SyntaxTreeNode {
    <T, L> T accept(ExpressionVisitor<T, L> visitor, L l);
}


