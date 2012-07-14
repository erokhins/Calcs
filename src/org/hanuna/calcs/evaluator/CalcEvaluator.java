package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.parser.*;
import static org.hanuna.calcs.parser.LexerToken.*;

/**
 * @author erokhins
 */
public class CalcEvaluator implements ExpressionVisitor<Integer> {
    private final ListOfVars list;

    public CalcEvaluator(ListOfVars list) {
        this.list = list;
    }

    @Override
    public Integer visitBin(ParserNodeBin n) throws ExpressionVisitorError {
        if (n == null) {
            throw new ExpressionVisitorError("null node");
        }
        switch (n.getType()) {
            case TT_PLUS:
                return n.getLeft().accept(this) + n.getRight().accept(this);

            case TT_MINUS:
                return n.getLeft().accept(this) - n.getRight().accept(this);

            case TT_MULT:
                return n.getLeft().accept(this) * n.getRight().accept(this);

            case TT_DIV:
                throw new ExpressionVisitorError("divide not supported");

            default:
                throw new ExpressionVisitorError("NodeBin: not expected type "+LexerToken.typeToStr(n.getType()));
        }
    }

    @Override
    public Integer visitUn(ParserNodeUn n) throws ExpressionVisitorError {
        if (n == null) {
            throw new ExpressionVisitorError("null node");
        }
        switch (n.getType()) {
            case TT_PLUS:
                return n.getLeft().accept(this);

            case TT_MINUS:
                return - n.getLeft().accept(this);

            default:
                throw new ExpressionVisitorError("NodeUn: not expected type "+LexerToken.typeToStr(n.getType()));
        }
    }

    @Override
    public Integer visitVar(ParserNodeVar n) throws ExpressionVisitorError {
        if (n == null) {
            throw new ExpressionVisitorError("null node");
        }
        switch (n.getType()) {
            case TT_VAR:
                Integer c = list.get(n.getVar());
                if (c == null) {
                    throw new ExpressionVisitorError("Undefined var: "+n.getVar());
                } else {
                    return c;
                }
            case TT_INT:
                return new Integer(n.getVar());

            default:
                throw new ExpressionVisitorError("NodeVar: not expected type "+LexerToken.typeToStr(n.getType()));
        }
    }
}
