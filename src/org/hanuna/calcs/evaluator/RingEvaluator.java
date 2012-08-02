package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.lexer.LexerTokenType;
import org.hanuna.calcs.parser.ExpressionVisitor;
import org.hanuna.calcs.parser.ParserDouble;
import org.hanuna.calcs.syntaxtree.*;
import org.hanuna.calcs.vartable.VarTable;
import static org.hanuna.calcs.syntaxtree.SyntaxTreeUtils.*;

/**
 * @author erokhins
 */

public class RingEvaluator<T> implements ExpressionVisitor<T, VarTable<T>> {
    protected final Ring<T> ring;
    private final ParserDouble<T> parserDouble;

    public RingEvaluator(Ring<T> ring, ParserDouble<T> parserDouble) {
        this.ring = ring;
        this.parserDouble = parserDouble;
    }

    @Override
    public T visitBin(SyntaxTreeNodeBinary n, VarTable<T> l) {
        checkNode(n);
        T left = n.getLeft().accept(this, l);
        T right = n.getRight().accept(this, l);
        return applyArithmeticOperation(n.getType(), left, right);
    }

    protected T applyArithmeticOperation(LexerTokenType type, T left, T right) {
        switch (type) {
            case PLUS:
                return ring.add(left, right);

            case MINUS:
                return ring.add(left, ring.negative(right));

            case MULT:
                return ring.mult(left, right);

            default:
                throw new CalcEvaluatorException("NodeBin: not expected type " + type);
        }
    }

    @Override
    public T visitUn(SyntaxTreeNodeUnary n, VarTable<T> l) {
        checkNode(n);
        switch (n.getType()) {
            case PLUS:
                return n.getOperand().accept(this, l);

            case MINUS:
                return ring.negative(n.getOperand().accept(this, l));

            default:
                throw new CalcEvaluatorException("NodeUn: not expected type " + n.getType());
        }
    }

    @Override
    public T visitVar(SyntaxTreeNodeVar n, VarTable<T> l) {
        checkNode(n);
        T c = l.get(n.getVar());
        if (c == null) {
            throw new CalcEvaluatorException("Undefined var: " + n.getVar());
        } else {
            return c;
        }
    }

    @Override
    public T visitNumber(SyntaxTreeNodeNumber n, VarTable<T> l) {
        checkNode(n);
        return parserDouble.parseDouble(n.getNumberStr());
    }

}
