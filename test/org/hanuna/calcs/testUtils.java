package org.hanuna.calcs;

import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.LexerToken;
import org.hanuna.calcs.lexer.LexerTokenType;
import org.hanuna.calcs.polynoms.ListPowersVariables;
import org.hanuna.calcs.polynoms.Monom;
import org.hanuna.calcs.polynoms.PowersVariables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author erokhins
 */
public class testUtils {
    public static List<Integer> parseIntArray(String s) {
        FlexLexer lexer = new FlexLexer(s);
        List<Integer> al = new ArrayList<Integer>();
        try {
            while (lexer.next() != LexerToken.TOKEN_STOP) {
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
        List<Integer> l = parseIntArray(s);
        return new ListPowersVariables(l);
    }

    public static <T> Monom<T> createMonom(String sPV, T value) {
        return new Monom<T>(parsePV(sPV), value);
    }
}
