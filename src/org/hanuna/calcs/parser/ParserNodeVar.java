package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeVar implements ParserNode {
    private String var;

    public ParserNodeVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    @Override
    public <T, L> T accept(ExpressionVisitor<T, L> visitor, L l) {
        return visitor.visitVar(this, l);
    }
}
