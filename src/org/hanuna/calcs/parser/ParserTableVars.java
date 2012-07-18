package org.hanuna.calcs.parser;

import java.io.IOException;
import static org.hanuna.calcs.parser.LexerTokenType.*;

/**
 * @author erokhins
 */
public class ParserTableVars {

    public static TableVars parserTableVars(Lexer l) throws ParserException, IOException {
        TableVars list = new TableVars();
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

            int k = sign * Integer.parseInt(l.getToken().getString());
            list.put(s, k);
            l.next();
        }
        return list;
    }

}
