package org.hanuna.calcs.parser;

import java.io.*;
import static org.hanuna.calcs.parser.LexerToken.*;

/**
 * @author erokhins
 */
public class Lexer {

    private final Reader f;
    private LexerToken t;
    private int c; // this char in file


    public Lexer(Reader f) {
        this.f = f;
        readNextChar();
    }

    public Lexer(String s) {
        this(new StringReader(s));
    }

    public Lexer(Reader f, boolean readFirstToken) {
        this(f);
        if (readFirstToken) {
            this.next();
        }
    }

    public Lexer(String s, boolean readFirstToken) {
        this(new StringReader(s), readFirstToken);
    }




    public LexerToken next() {
        readSpaces();
        switch (c) {
            case -2:
                t = new LexerToken(TT_IOER, "");
                break;

            case -1:
                t = new LexerToken(TT_STOP, "");
                break;

            case '-':
                t = new LexerToken(TT_MINUS, "");
                break;

            case '+':
                t = new LexerToken(TT_PLUS, "");
                break;

            case '*':
                t = new LexerToken(TT_MULT, "");
                break;

            case '/':
                t = new LexerToken(TT_DIV, "");
                break;

            case '(':
                t = new LexerToken(TT_BKT_O, "");
                break;

            case ')':
                t = new LexerToken(TT_BKT_C, "");
                break;

            case '=':
                t = new LexerToken(TT_EQ, "");
                break;

            case '>':
                t = new LexerToken(TT_RUN, "");
                break;

            default:
                if (itIsLetter(c)) {
                    t = new LexerToken(TT_VAR, readVar());
                } else if (itIsNumber(c)) {
                    t = new LexerToken(TT_INT, readInt());
                } else {
                    t = new LexerToken(TT_ER, "" + (char) this.c);
                }
                break;
        }
        int tt = t.getT();
        if (tt != TT_VAR && tt != TT_INT) {   // if is 1 symbol - read next
            readNextChar();
        }

        return t;
    }

    public LexerToken getToken() {
        return t;
    }


    private int readNextChar() {
        try{
            c = f.read();
        } catch (IOException e) {
            c = -2;
        }
        return c;
    }

    /**
     * if (endFile) {
     *     return -1;
     * } elseif (IOError) {
     *     return -2;
     * } else {
     *     return 0;
     * }
     */

    private int readSpaces() {
        while ((c == '\n') || (c == '\r') || (c == ' ')) {
            readNextChar();
            if(c < 0){
                return c;
            }
        }
        return 0;
    }

    private String readInt() {
        String s = "";
        while (itIsNumber(c)){
            s += (char) c;
            readNextChar();
        }
        return s;
    }

    private String readVar() {
        String s = "";
        if (itIsLetter(c)) {
            s += (char) c;
        } else {
            return s;
        }
        readNextChar();

        while (itIsLetter(c) || itIsNumber(c)) {
            s += (char) c;
            readNextChar();
        }

        return s;
    }

    private boolean itIsNumber(int c){
        return (c >= '0') && (c <= '9');
    }

    private boolean itIsLetter(int c){
        return ((c >= 'a') && (c <= 'z'))
                || ((c >= 'A') && (c <= 'Z'))
                || (c == '_');
    }

}
