package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.parser.*;

/**
 * @author erokhins
 */
public class StringEvaluator implements ExpressionVisitor<String> {

    @Override
    public String visitBin(ParserNodeBin n) throws ExpressionVisitorError {
        if (n == null) {
           throw new ExpressionVisitorError("null node");
        }
        return  "{" + n.getLeft().accept(this) + " "
                + LexerToken.typeToStr(n.getType()) + " "
                + n.getRight().accept(this) + "}";
    }

    @Override
    public String visitUn(ParserNodeUn n) throws ExpressionVisitorError {
        if (n == null) {
            throw new ExpressionVisitorError("null node");
        }
        return  "{" + LexerToken.typeToStr(n.getType()) + " "
                + n.getLeft().accept(this) + "}";
    }

    @Override
    public String visitVar(ParserNodeVar n) throws ExpressionVisitorError {
        if (n == null) {
            throw new ExpressionVisitorError("null node");
        }
        return LexerToken.typeToStr(n.getType()) + ":" + n.getVar();
    }
}
