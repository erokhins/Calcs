package org.hanuna.calcs.parser;


import java.io.IOException;

import static org.hanuna.calcs.parser.LexerTokenType.*;

/**
 * @author erokhins
 */
public class Parser {



    /*
   * S => M + S | M
   * M = F | F * M
   * F = (S) | Var | Int | + F
   * */
    public static ParserNode parseExpression(Lexer l) throws ParserException, IOException {
        if (l.getTokenType() == RUN) {
            l.next();
            ParserNode p = parseSum(l);
            if (l.getTokenType() != STOP) {
                throw new ParserException("expected end of file, but found " + l.getToken().toString());
            } else {
                return p;
            }
        } else {
            throw new ParserException("expected '>', but found " + l.getToken().toString());
        }
    }

    public static ParserNode parseSum(Lexer l) throws ParserException, IOException {
        return parseSum(l, false);
    }

    /**
     * change need for this: a-b+c -> {var:a - {var:b - var:c}}
     * a-b+c-d -> a-(b-c+d) -> a-(b-(c-d))
    */
    public static ParserNode parseSum(Lexer l, boolean change) throws ParserException, IOException {
        ParserNode left = parseMult(l);
        LexerTokenType type = l.getTokenType();
        ParserNode right;

        switch (type) {
            case PLUS:
                l.next();
                if (change) {
                    right = parseSum(l, !change);
                    return new ParserNodeBinary(left, right, MINUS);
                } else {
                    right = parseSum(l, change);
                    return new ParserNodeBinary(left, right, PLUS);
                }

            case MINUS:
                l.next();
                if (change) {
                    right = parseSum(l, change);
                    return new ParserNodeBinary(left, right, PLUS);
                } else {
                    right = parseSum(l, !change);
                    return new ParserNodeBinary(left, right, MINUS);
                }

            default:
                return left;
        }

    }


    // change: see parseSum
    public static ParserNode parseMult(Lexer l) throws ParserException, IOException {
        return parseMult(l, false);
    }

    public static ParserNode parseMult(Lexer l, boolean change) throws ParserException, IOException {
        ParserNode left = parseFactor(l);
        LexerTokenType type = l.getTokenType();
        ParserNode right;

        switch (type) {
            case MULT:
                l.next();
                if (change) {
                    right = parseMult(l, !change);
                    return new ParserNodeBinary(left, right, DIV);
                } else {
                    right = parseMult(l, change);
                    return new ParserNodeBinary(left, right, MULT);
                }

            case DIV: {
                l.next();
                if (change) {
                    right = parseMult(l, change);
                    return new ParserNodeBinary(left, right, MULT);
                } else {
                    right = parseMult(l, !change);
                    return new ParserNodeBinary(left, right, DIV);
                }
            }

            default:
                return left;
        }

    }


    public static ParserNode parseFactor(Lexer l) throws ParserException, IOException {
        LexerTokenType type = l.getTokenType();
        ParserNode left;
        switch (type) {
            case VAR:
                left = new ParserNodeVar(l.getToken().getString());
                l.next();
                return left;

            case NUMBER:
                left = new ParserNodeNumber(l.getToken().getString());
                l.next();
                return left;

            case PLUS:
                l.next();
                left = parseFactor(l);
                return new ParserNodeUnary(PLUS, left);

            case MINUS:
                l.next();
                left = parseFactor(l);
                return new ParserNodeUnary(MINUS, left);

            case BRACKET_OPEN:
                l.next();
                left = parseSum(l);
                if (l.getTokenType() == BRACKET_CLOSE){
                    l.next();
                    return left;
                } else {
                    throw new ParserException("expected ')', but found " + l.getToken().toString());
                }


            default:
                throw new ParserException("expected var, number, '+', '-', '(' or ')', but found " + l.getToken().toString());
        }
    }
}
