package org.hanuna.calcs;

/**
 * @author erokhins
 */
public class BaseRuntimeException extends RuntimeException {
    String s;
    public BaseRuntimeException(String s) {
        this.s = s;
    }

    public String getMessage() {
        return s;
    }

}
