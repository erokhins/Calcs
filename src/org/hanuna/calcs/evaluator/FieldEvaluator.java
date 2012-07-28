package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.lexer.LexerTokenType;

/**
 * @author erokhins
 */
public class FieldEvaluator<T> extends RingEvaluator<T, Field<T>> {
    public FieldEvaluator(Field<T> field) {
        super(field);
    }

    @Override
    protected T applyArithmeticOperation(LexerTokenType type, T left, T right) {
        if (type == LexerTokenType.DIV) {
            return ring.mult(left, ring.inverse(right));
        }
        return super.applyArithmeticOperation(type, left, right);
    }
}
