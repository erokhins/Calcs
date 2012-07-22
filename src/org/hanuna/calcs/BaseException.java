package org.hanuna.calcs;

/**
 * @author erokhins
 */
public class BaseException extends Exception {
    String s;
    public BaseException(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }

}
