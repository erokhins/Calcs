package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.BadCodeException;
import org.hanuna.calcs.parser.*;

/**
 * @author erokhins
 */
public class StringEvaluator implements ExpressionVisitor<String> {

    @Override
    public String visitBin(ParserNodeBinary n) {
        if (n == null) {
           throw new BadCodeException("null node");
        }
        return  "{" + n.getLeft().accept(this) + " "
                + n.getType().toString() + " "
                + n.getRight().accept(this) + "}";
    }

    @Override
    public String visitUn(ParserNodeUnary n) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        return  "{" + n.getType().toString() + " "
                + n.getOperand().accept(this) + "}";
    }

    @Override
    public String visitVar(ParserNodeVar n) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        return LexerTokenType.VAR.toString() + ":" + n.getVar();
    }

    @Override
    public String visitNumber(ParserNodeNumber n) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        return LexerTokenType.NUMBER.toString() + ":" + n.getNumberStr();
    }
}
