package org.hanuna.calcs.parser;

import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.Lexer;

import java.io.Reader;

/**
 * @author erokhins
 */
public class FlexLexerTest extends LexerTest {

    @Override
    public Lexer getLexer(Reader r) {
        return new FlexLexer(r);
    }

}
