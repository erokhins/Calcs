package org.hanuna.calcs.parser;

import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeBinary;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeNumber;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeUnary;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeVar;

/**
 * @author erokhins
 */
public interface ExpressionVisitor<T, L> {
    public T visitBin(SyntaxTreeNodeBinary n, L l);
    public T visitUn(SyntaxTreeNodeUnary n, L l);
    public T visitVar(SyntaxTreeNodeVar n, L l);
    public T visitNumber(SyntaxTreeNodeNumber n, L l);
}
