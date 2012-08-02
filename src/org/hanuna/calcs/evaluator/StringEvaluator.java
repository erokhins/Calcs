package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.lexer.LexerTokenType;
import org.hanuna.calcs.parser.ExpressionVisitor;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeBinary;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeNumber;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeUnary;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNodeVar;
import static org.hanuna.calcs.syntaxtree.SyntaxTreeUtils.checkNode;


/**
 * @author erokhins
 */
public class StringEvaluator implements ExpressionVisitor<String, Object> {

    @Override
    public String visitBin(SyntaxTreeNodeBinary n, Object o) {
        checkNode(n);
        return  "{" + n.getLeft().accept(this, o) + " "
                + n.getType().toString() + " "
                + n.getRight().accept(this, o) + "}";
    }

    @Override
    public String visitUn(SyntaxTreeNodeUnary n, Object o) {
        checkNode(n);
        return  "{" + n.getType().toString() + " "
                + n.getOperand().accept(this, 0) + "}";
    }

    @Override
    public String visitVar(SyntaxTreeNodeVar n, Object o) {
        checkNode(n);
        return LexerTokenType.VAR.toString() + ":" + n.getVar();
    }

    @Override
    public String visitNumber(SyntaxTreeNodeNumber n, Object o) {
        checkNode(n);
        return LexerTokenType.NUMBER.toString() + ":" + n.getNumberStr();
    }

}
