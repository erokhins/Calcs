package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserException extends Exception {
    private String s;

    public ParserException(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }
}
