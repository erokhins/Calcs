package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.BadCodeException;
import org.hanuna.calcs.parser.ExpressionVisitor;
import org.hanuna.calcs.lexer.LexerTokenType;
import org.hanuna.calcs.syntaxtree.*;

/**
 * @author erokhins
 */
public class StringEvaluator implements ExpressionVisitor<String, Object> {

    @Override
    public String visitBin(SyntaxTreeNodeBinary n, Object o) {
        if (n == null) {
           throw new BadCodeException("null node");
        }
        return  "{" + n.getLeft().accept(this, o) + " "
                + n.getType().toString() + " "
                + n.getRight().accept(this, o) + "}";
    }

    @Override
    public String visitUn(SyntaxTreeNodeUnary n, Object o) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        return  "{" + n.getType().toString() + " "
                + n.getOperand().accept(this, 0) + "}";
    }

    @Override
    public String visitVar(SyntaxTreeNodeVar n, Object o) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        return LexerTokenType.VAR.toString() + ":" + n.getVar();
    }

    @Override
    public String visitNumber(SyntaxTreeNodeNumber n, Object o) {
        if (n == null) {
            throw new BadCodeException("null node");
        }
        return LexerTokenType.NUMBER.toString() + ":" + n.getNumberStr();
    }
}
