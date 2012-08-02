package org.hanuna.calcs.lexer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.hanuna.calcs.lexer.LexerToken.*;

/**
 * @author erokhins
 */
public class OldLexer implements Lexer {

    private final UndoReader reader;
    private LexerToken currentToken = null;
    private int currentChar = -239;

    public OldLexer(Reader reader) {
        this.reader = new UndoReader(reader);
    }
    public OldLexer(String s) {
        this(new StringReader(s));
    }

    @Override
    public LexerToken next() throws IOException {
        if (currentChar == -239) {
            readNextChar();
        }

        readSpaces();
        switch (currentChar) {
            case -1:
                currentToken = TOKEN_STOP;
                break;

            case '-':
                currentToken = TOKEN_MINUS;
                break;

            case '+':
                currentToken = TOKEN_PLUS;
                break;

            case '*':
                currentToken = TOKEN_MULT;
                break;

            case '/':
                currentToken = TOKEN_DIV;
                break;

            case '(':
                currentToken = TOKEN_BRACKET_OPEN;
                break;

            case ')':
                currentToken = TOKEN_BRACKET_CLOSE;
                break;

            case '=':
                currentToken = TOKEN_EQUAL;
                break;

            case '>':
                currentToken = TOKEN_RUN;
                break;

            default:
                if (itIsLetter(currentChar)) {
                    currentToken = newVar(readVar());
                } else if (Character.isDigit(currentChar)) {
                    currentToken = newNumber(readFloatNumber());
                } else {
                    currentToken = newError("" + (char) this.currentChar);
                }
                break;
        }

        if (currentToken.isSingleLetterType()) {   // if is 1 symbol - read next
            readNextChar();
        }
        return currentToken;
    }

    @Override
    public LexerToken getToken() throws IOException {
        if (currentToken == null) {
            this.next();
        }
        return currentToken;
    }

    @Override
    public LexerTokenType getTokenType() throws IOException {
        return this.getToken().getType();
    }


    private int readNextChar() throws IOException {
        currentChar = reader.read();
        return currentChar;
    }

    private int readSpaces() throws IOException {
        while ((currentChar == '\n') || (currentChar == '\r') || (currentChar == ' ')) {
            readNextChar();
            if (currentChar < 0) {
                return currentChar;
            }
        }
        return 0;
    }

    private String readNumber() throws IOException {
        StringBuilder s = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            s.append((char) currentChar);
            readNextChar();
        }
        return s.toString();
    }

    private String readFloatNumber() throws IOException {
        String s = readNumber();
        if (s.length() != 0 && currentChar == '.') {
            readNextChar();
            if (Character.isDigit(currentChar)) {
                s = s + '.' + readNumber();
            } else {
                reader.undo();
            }
        }
        return s;
    }

    private String readVar() throws IOException {
        StringBuilder s = new StringBuilder();
        if (itIsLetter(currentChar)) {
            s.append((char) currentChar);
        } else {
            return "";
        }
        readNextChar();

        while (itIsLetter(currentChar) || Character.isDigit(currentChar)) {
            s.append((char) currentChar);
            readNextChar();
        }

        return s.toString();
    }

    private boolean itIsLetter(int c) {
        return Character.isLetter(c) || c == '_';
    }


}
