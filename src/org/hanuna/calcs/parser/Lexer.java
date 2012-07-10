package org.hanuna.calcs.parser;

import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * @author erokhins
 */
class Lexer{

    public static final int TT_PLUS = 1;  // +
    public static final int TT_MINUS = 2; // -
    public static final int TT_MULT = 3;  // *
    public static final int TT_BKT_O = 5; // (
    public static final int TT_BKT_C = 6; // )
    public static final int TT_EQ = 7;    // =
    public static final int TT_VAR = 12;   // asd
    public static final int TT_RUN = 10;   // >
    public static final int TT_INT = 11;   // 239
    public static final int TT_STOP = -1;  // error or end file
    public static final int TT_ER = -2;  // error input syntax

    private StreamTokenizer in;
    public int     ttype;
    public String  sval;
    public int     nval;

    Lexer(StreamTokenizer in){
        this.in = in;
    }

    int next(){
        try{
            in.nextToken();
        } catch (IOException e) {
            this.ttype = Lexer.TT_STOP;
            return this.ttype;
        }
        switch (in.ttype){
            case StreamTokenizer.TT_EOF: this.ttype = Lexer.TT_STOP; break;
            case StreamTokenizer.TT_NUMBER:
                this.ttype = Lexer.TT_INT;
                this.nval = (int) in.nval;
                break;
            case StreamTokenizer.TT_WORD:
                this.ttype = Lexer.TT_VAR;
                this.sval = in.sval;
                break;
            case 61: ttype = TT_EQ;
                break;
            case 62: ttype = TT_RUN;
                in.ordinaryChar('-');
                break;
            case 40: ttype = TT_BKT_O;
                break;
            case 41: ttype = TT_BKT_C;
                break;
            case 43: ttype = TT_PLUS;
                break;
            case 45: ttype = TT_MINUS;
                break;
            case 42: ttype = TT_MULT;
                break;

            default: ttype = TT_ER;
        }
        return this.ttype;
    }
}
