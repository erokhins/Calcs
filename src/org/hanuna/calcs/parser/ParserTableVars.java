package org.hanuna.calcs.parser;

import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.vartable.DoubleVarTable;

import java.io.IOException;
import static org.hanuna.calcs.lexer.LexerTokenType.*;

/**
 * @author erokhins
 */
public class ParserTableVars {

    public static DoubleVarTable parserTableVars(Lexer l) throws ParserException, IOException {
        DoubleVarTable list = new DoubleVarTable();
        while (l.getToken().getType() == VAR) {
            String s = l.getToken().getString();
            if (l.next().getType() != EQUAL) {
                throw new ParserException("expected '=', but found " + l.getToken().toString());
            }
            int sign = 1;

            switch (l.next().getType()) {
                case PLUS:
                    l.next();
                    break;

                case MINUS:
                    sign = -1;
                    l.next();
                    break;

                default:
                    // Do nothing
            }
            if (l.getToken().getType() != NUMBER) {
                throw new ParserException("expected number, but found " + l.getToken().toString());
            }

            double k = sign * Double.parseDouble(l.getToken().getString());
            list.put(s, k);
            l.next();
        }
        return list;
    }

}
