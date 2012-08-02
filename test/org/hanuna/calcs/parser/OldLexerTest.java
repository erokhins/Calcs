package org.hanuna.calcs.parser;

import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.lexer.OldLexer;

import java.io.Reader;

/**
 * @author erokhins
 */
public class OldLexerTest extends LexerTest {
    @Override
    public Lexer getLexer(Reader r) {
        return new OldLexer(r);
    }
}
