package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.BadCodeException;
import org.hanuna.calcs.parser.*;

/**
 * @author erokhins
 */
public class CalcEvaluator implements ExpressionVisitor<Integer> {
    private final TableVars list;

    public CalcEvaluator(TableVars list) {
        this.list = list;
    }

    @Override
    public Integer visitBin(ParserNodeBinary n) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        switch (n.getType()) {
            case PLUS:
                return n.getLeft().accept(this) + n.getRight().accept(this);

            case MINUS:
                return n.getLeft().accept(this) - n.getRight().accept(this);

            case MULT:
                return n.getLeft().accept(this) * n.getRight().accept(this);

            case DIV:
                throw new CalcEvaluatorException("divide not supported");

            default:
                throw new CalcEvaluatorException("NodeBin: not expected type " + n.getType().toString());
        }
    }

    @Override
    public Integer visitUn(ParserNodeUnary n) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        switch (n.getType()) {
            case PLUS:
                return n.getOperand().accept(this);

            case MINUS:
                return - n.getOperand().accept(this);

            default:
                throw new CalcEvaluatorException("NodeUn: not expected type " + n.getType().toString());
        }
    }

    @Override
    public Integer visitVar(ParserNodeVar n) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        Integer c = list.get(n.getVar());
        if (c == null) {
            throw new CalcEvaluatorException("Undefined var: " + n.getVar());
        } else {
            return c;
        }
    }

    @Override
    public Integer visitNumber(ParserNodeNumber n) {
        return Integer.parseInt(n.getNumberStr());
    }
}
