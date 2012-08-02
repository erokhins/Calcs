package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.lexer.LexerTokenType;
import org.hanuna.calcs.parser.ParserDouble;

/**
 * @author erokhins
 */
public class FieldEvaluator<T> extends RingEvaluator<T> {
    public FieldEvaluator(Field<T> field, ParserDouble<T> parserDouble) {
        super(field, parserDouble);
    }

    @Override
    protected T applyArithmeticOperation(LexerTokenType type, T left, T right) {
        if (type == LexerTokenType.DIV) {
            Field<T> field = (Field<T>) ring;
            return field.mult(left, field.inverse(right));
        }
        return super.applyArithmeticOperation(type, left, right);
    }
}
