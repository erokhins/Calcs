package org.hanuna.calcs;

/**
 * @author erokhins
 */
public class BadCodeException extends RuntimeException {
    private String s;

    public BadCodeException(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }
}
