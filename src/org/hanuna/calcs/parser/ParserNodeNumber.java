package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserNodeNumber implements ParserNode {
    private String numberStr;

    public ParserNodeNumber(String numberStr) {
        this.numberStr = numberStr;
    }

    public String getNumberStr() {
        return numberStr;
    }


    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitNumber(this);
    }
}
