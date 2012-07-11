package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public class LexerToken {
    /** ttype =  */
    public static final int TT_PLUS     = 1;    // +
    public static final int TT_MINUS    = 2;    // -
    public static final int TT_MULT     = 3;    // *
    public static final int TT_DIV      = 4;    // /
    public static final int TT_BKT_O    = 5;    // (
    public static final int TT_BKT_C    = 6;    // )
    public static final int TT_EQ       = 7;    // =
    public static final int TT_VAR      = 12;   // asd
    public static final int TT_RUN      = 10;   // >
    public static final int TT_INT      = 11;   // 239
    public static final int TT_STOP     = -1;   // end file
    public static final int TT_ER       = -3;   // error input syntax
    public static final int TT_IOER     = -2;   // IO ERROR

    private final int ttype;  // Token type,
    private final String s;   // Token String

    LexerToken (int ttype, String s) {
        this.ttype = ttype;
        this.s = s;
    }

    public int getT(){
        return ttype;
    }

    public String getS(){
        return s;
    }

}
