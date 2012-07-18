package org.hanuna.calcs.evaluator;

/**
 * @author erokhins
 */
public class CalcEvaluatorException extends RuntimeException {
    private String s;

    public CalcEvaluatorException(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }
}
