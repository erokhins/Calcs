package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public interface ExpressionVisitor<T> {
    public T visitBin(ParserNodeBin n) throws ExpressionVisitorError;
    public T visitUn(ParserNodeUn n) throws ExpressionVisitorError;
    public T visitVar(ParserNodeVar n) throws ExpressionVisitorError;
}
