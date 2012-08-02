package org.hanuna.calcs.lexer;

/**
 * @author erokhins
 */
public enum LexerTokenType {

    PLUS("+"),
    MINUS("-"),
    MULT("*"),
    DIV("/"),
    RUN(">"),
    BRACKET_OPEN("("),
    BRACKET_CLOSE(")"),
    EQUAL("="),
    STOP("end"),
    ERROR("error"),
    VAR("var"),
    NUMBER("number");

    private final String s;

    private LexerTokenType(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }

    public boolean isSimpleType() {
        return this != VAR && this != NUMBER && this != ERROR;
    }

    public boolean isSingleLetterType() {
        return this != VAR && this != NUMBER;
    }

    public boolean isStopOrError() {
        return this == STOP || this == ERROR;
    }

}
