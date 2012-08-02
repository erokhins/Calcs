package org.hanuna.calcs.lexer;

import java.io.IOException;

/**
 * @author erokhins
 */
public interface Lexer {
    public LexerToken next() throws IOException;
    public LexerToken getToken() throws IOException;
    public LexerTokenType getTokenType() throws IOException;
}
