package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public interface ExpressionVisitor<T> {
    public T visitBin(ParserNodeBinary n);
    public T visitUn(ParserNodeUnary n);
    public T visitVar(ParserNodeVar n);
    public T visitNumber(ParserNodeNumber n);
}
