package org.hanuna.calcs.parser;

import static org.hanuna.calcs.parser.LexerTokenType.*;


/**
 * @author erokhins
 */
public class LexerToken {

    public static final LexerToken TOKEN_PLUS           = new LexerToken(PLUS);
    public static final LexerToken TOKEN_MINUS          = new LexerToken(MINUS);
    public static final LexerToken TOKEN_MULT           = new LexerToken(MULT);
    public static final LexerToken TOKEN_DIV            = new LexerToken(DIV);
    public static final LexerToken TOKEN_RUN            = new LexerToken(RUN);
    public static final LexerToken TOKEN_STOP           = new LexerToken(STOP);
    public static final LexerToken TOKEN_EQUAL          = new LexerToken(EQUAL);
    public static final LexerToken TOKEN_BRACKET_OPEN   = new LexerToken(BRACKET_OPEN);
    public static final LexerToken TOKEN_BRACKET_CLOSE  = new LexerToken(BRACKET_CLOSE);

    private final LexerTokenType tokenType;
    private final String tokenString;

    private LexerToken(LexerTokenType tokenType) {
        this.tokenType = tokenType;
        this.tokenString = "";
    }

    private LexerToken(LexerTokenType tokenType, String tokenString) {
        this.tokenType = tokenType;
        this.tokenString = tokenString;
    }

    public LexerTokenType getType() {
        return tokenType;
    }

    public String getString() {
        return tokenString;
    }

    public boolean isSimpleToken() {
        return tokenType.isSimpleType();
    }

    public static LexerToken newVar(String nameVar) {
        return new LexerToken(LexerTokenType.VAR, nameVar);
    }

    public static LexerToken newNumber(String numberStr) {
        return new LexerToken(LexerTokenType.NUMBER, numberStr);
    }

    public static LexerToken newError(String s) {
        return new LexerToken(ERROR, s);
    }

    public String toString() {
        if (tokenType.isSimpleType()) {
            return tokenType.toString();
        } else {
            return tokenString;
        }
    }

}
