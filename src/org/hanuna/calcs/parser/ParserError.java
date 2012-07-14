package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class ParserError extends Exception {
    private String s;

    public ParserError(String s){
        this.s = s;
    }

    public String getMessage(){
        return s;
    }
}
