package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public interface ExpressionVisitor<T, L> {
    public T visitBin(ParserNodeBinary n, L l);
    public T visitUn(ParserNodeUnary n, L l);
    public T visitVar(ParserNodeVar n, L l);
    public T visitNumber(ParserNodeNumber n, L l);
}
