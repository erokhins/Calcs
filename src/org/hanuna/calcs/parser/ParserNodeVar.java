package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeVar implements ParserNode {
    private int type;
    private String var;

    public ParserNodeVar(int type, String var){
        this.type = type;
        this.var = var;
    }

    public String getVar() {
        return var;
    }
    public int getType() {
        return type;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) throws ExpressionVisitorError {
        return visitor.visitVar(this);
    }
}
