package org.hanuna.calcs.parser;


import static org.hanuna.calcs.parser.LexerToken.*;

/**
 * @author erokhins
 */
public class Parser {

    public static ListOfVars parseListOfVars(Lexer l) throws ParserError {
        ListOfVars list = new ListOfVars();
        while (l.getToken().getT() == TT_VAR) {
            String s = l.getToken().getS();
            if (l.next().getT() != TT_EQ) {
                throw new ParserError("expected '=', but found " + l.getToken().toString());
            }
            String sign = "";

            switch (l.next().getT()) {
                case TT_PLUS:
                    sign = "+";
                    l.next();
                    break;

                case TT_MINUS:
                    sign = "-";
                    l.next();
                    break;

                default:
                    // Do nothing
            }
            if (l.getToken().getT() != TT_INT) {
                throw new ParserError("expected number, but found " + l.getToken().toString());
            }

            int k = new Integer(sign+l.getToken().getS());
            list.put(s, k);
            l.next();
        }
        return list;
    }


    /*
   * S => M + S | M
   * M = F | F * M
   * F = (S) | Var | Int | + F
   * */
    public static ParserNode parseExpression(Lexer l) throws ParserError {
        if (l.getToken().getT() == TT_RUN) {
            l.next();
            ParserNode p = parseSum(l);
            if (l.getToken().getT() != TT_STOP) {
                throw new ParserError("expected end of file, but found " + l.getToken().toString());
            } else {
                return p;
            }
        } else {
            throw new ParserError("expected '>', but found " + l.getToken().toString());
        }
    }

    public static ParserNode parseSum(Lexer l) throws ParserError {
        ParserNode left = parseMult(l);
        int type = l.getToken().getT();
        ParserNode right = null;


        if ((type == TT_PLUS) || (type == TT_MINUS)) {
            l.next();
            right = parseSum(l);
            return new ParserNodeBin(left, right, type);
        } else {
            return left;
        }
    }


    public static ParserNode parseMult(Lexer l) throws ParserError {
        ParserNode left = parseFactor(l);
        int type = l.getToken().getT();
        ParserNode right = null;


        if((type == TT_MULT) || (type == TT_DIV)){
            l.next();
            right = parseMult(l);
            return new ParserNodeBin(left, right, type);
        } else {
            return left;
        }

    }


    public static ParserNode parseFactor(Lexer l) throws ParserError {
        int type = l.getToken().getT();
        ParserNode left = null;
        switch (type){
            case TT_VAR:
                left = new ParserNodeVar(TT_VAR, l.getToken().getS());
                l.next();
                return left;

            case TT_INT:
                left = new ParserNodeVar(TT_INT, l.getToken().getS());
                l.next();
                return left;

            case TT_PLUS:
                l.next();
                left = parseFactor(l);
                return new ParserNodeUn(TT_PLUS, left);

            case TT_MINUS:
                l.next();
                left = parseFactor(l);
                return new ParserNodeUn(TT_MINUS, left);

            case TT_BKT_O:
                l.next();
                left = parseSum(l);
                if (l.getToken().getT() == TT_BKT_C){
                    l.next();
                    return left;
                } else {
                    throw new ParserError("expected ')', but found " + l.getToken().toString());
                }


            default:
                throw new ParserError("expected var, number, '+', '-', '(' or ')', but found " + l.getToken().toString());
        }
    }
}
