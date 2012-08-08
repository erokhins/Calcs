package org.hanuna.calcs;

import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.lexer.LexerToken;
import org.hanuna.calcs.lexer.LexerTokenType;
import org.hanuna.calcs.parser.ParserException;
import org.hanuna.calcs.polynoms.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author erokhins
 */
public class testUtils {

    public static List<Integer> parseIntList(String s) {
        FlexLexer lexer = new FlexLexer(s);
        return parseIntList(lexer);
    }

    public static List<Integer> parseIntList(Lexer lexer) {
        List<Integer> al = new ArrayList<Integer>();
        try {
            while (lexer.next().getType() == LexerTokenType.NUMBER) {
                if (lexer.getTokenType() == LexerTokenType.NUMBER) {
                    al.add(Integer.parseInt(lexer.getToken().getString()));
                }
            }
        } catch (IOException e) {
            return Collections.<Integer>emptyList();
        }

        return al;
    }

    public static PowersVariables parsePV(String s) {
        List<Integer> l = parseIntList(s);
        return ListPowersVariables.copyFromList(l);
    }

    public static String powerVariablesToString(PowersVariables pv) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < pv.maxCountVar(); i++) {
            if (i > 0) {
                s.append(' ');
            }
            s.append(pv.getDegree(i));
        }
        return s.toString();
    }

    /**
     * Example: "(1 2 3):5 ():-3" = x_1 * x_2 ^ 2 * x_3 ^ 3 * 5 + -3
     */
    public static Polynom<Double> parseDoublePolynom(String s) throws ParserException {
        List<Monom<Double>> listMonom = new ArrayList<Monom<Double>>();
        FlexLexer lexer = new FlexLexer(s);
        try {
            while (lexer.next() != LexerToken.TOKEN_STOP) {
                if (lexer.getToken() != LexerToken.TOKEN_BRACKET_OPEN) {
                    throw new ParserException("excepted '(', but found " + lexer.getToken());
                }
                List<Integer> lPV = parseIntList(lexer);
                PowersVariables PV = ListPowersVariables.copyFromList(lPV);
                if (lexer.getToken() != LexerToken.TOKEN_BRACKET_CLOSE) {
                    throw new ParserException("excepted ')', but found " + lexer.getToken());
                }

                if (! lexer.next().getString().equals(":")) {
                    throw new ParserException("excepted ':', but found " + lexer.getToken());
                }
                    double sign = 1;
                double value;

                if (lexer.next() == LexerToken.TOKEN_MINUS) {
                    sign = -1;
                    lexer.next();
                }

                if (lexer.getTokenType() == LexerTokenType.NUMBER) {
                    value = sign * Double.parseDouble(lexer.getToken().getString());
                } else {
                    throw new ParserException("excepted number, but found " + lexer.getToken());
                }
                Monom<Double> m = new Monom<Double>(PV, value);
                listMonom.add(m);

            }
        } catch (IOException e) {
            throw new ParserException("unknown exception");
        }

        return ListPolynom.copyFromListMonom(listMonom);
    }

    public static <T> String polynomToString(Polynom<T> p) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < p.size(); i++) {
            if (i > 0) {
                s.append(' ');
            }
            Monom<T> m = p.getMonom(i);
            s.append('(');
            s.append(powerVariablesToString(m.getPowersVariables()));
            s.append("):");
            s.append(m.getValue());
        }
        return s.toString();
    }
}
