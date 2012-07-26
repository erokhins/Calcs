package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.BadCodeException;
import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.parser.*;

/**
 * @author erokhins
 */
public class CalcEvaluator<T> implements ExpressionVisitor<T, VarTable<T>> {
    private final Ring<T> ring;

    public CalcEvaluator(Ring<T> ring) {
        this.ring = ring;
    }

    @Override
    public T visitBin(ParserNodeBinary n, VarTable<T> l) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        T left = n.getLeft().accept(this, l);
        T right = n.getRight().accept(this, l);
        switch (n.getType()) {
            case PLUS:
                return ring.add(left, right);

            case MINUS:
                return ring.add(left, ring.negative(right));

            case MULT:
                return ring.mult(left, right);

            case DIV:
                try {
                    Field<T> field = (Field<T>) ring;
                    return field.mult(left, field.inverse(right));
                } catch (Exception e) {
                    throw new CalcEvaluatorException("divide not supported");
                }
            default:
                throw new CalcEvaluatorException("NodeBin: not expected type " + n.getType().toString());
        }
    }

    @Override
    public T visitUn(ParserNodeUnary n, VarTable<T> l) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        switch (n.getType()) {
            case PLUS:
                return n.getOperand().accept(this, l);

            case MINUS:
                return ring.negative(n.getOperand().accept(this, l));

            default:
                throw new CalcEvaluatorException("NodeUn: not expected type " + n.getType().toString());
        }
    }

    @Override
    public T visitVar(ParserNodeVar n, VarTable<T> l) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        T c = l.get(n.getVar());
        if (c == null) {
            throw new CalcEvaluatorException("Undefined var: " + n.getVar());
        } else {
            return c;
        }
    }

    @Override
    public T visitNumber(ParserNodeNumber n, VarTable<T> l) {
        return ring.parseNumber(n.getNumberStr());
    }
}
