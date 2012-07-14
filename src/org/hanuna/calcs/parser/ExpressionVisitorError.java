package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ExpressionVisitorError extends Exception {
    private String s;

    public ExpressionVisitorError(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }
}
