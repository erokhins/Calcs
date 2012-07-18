package org.hanuna.calcs.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.hanuna.calcs.parser.LexerToken.*;

/**
 * @author erokhins
 */
public class Lexer {

    private final Reader reader;
    private LexerToken currentToken = null;
    private int currentChar;


    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        readNextChar();
    }
    public Lexer(String s) throws IOException {
        this(new StringReader(s));
    }

    public LexerToken next() throws IOException {
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
                    currentToken = LexerToken.newVar(readVar());
                } else if (Character.isDigit(currentChar)) {
                    currentToken = LexerToken.newNumber(readNumber());
                } else {
                    currentToken = LexerToken.newError("" + (char) this.currentChar);
                }
                break;
        }

        if (currentToken.isSimpleToken()) {   // if is 1 symbol - read next
            readNextChar();
        }
        return currentToken;
    }

    public LexerToken getToken() throws IOException {
        if (currentToken == null) {
            this.next();
        }
        return currentToken;
    }

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
