package org.hanuna.calcs.lexer;


import java.io.IOException;
import java.io.StringReader;

import org.hanuna.calcs.lexer.LexerToken;
import org.hanuna.calcs.lexer.LexerTokenType;
import static org.hanuna.calcs.lexer.LexerToken.*;

/**
 * @author erokhins
 */


%%


%public
%class FlexLexer
%unicode

%apiprivate
%function nextToken
%type LexerToken

%{
		
    public FlexLexer(String s) {
        this(new StringReader(s));
    }
    
	private LexerToken currentToken = null;
	
	public LexerToken next() throws IOException {
		currentToken = nextToken();
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

%}

LineTerminator = \r | \n | \r\n

WhiteSpace     = {LineTerminator} | [ ]

Identifier = ([:letter:] | _) ([:letter:] | [:digit:] | _)*

Number = [:digit:]+ | [:digit:]+ [.] [:digit:]+


%%


<YYINITIAL> {

    /* identifiers */
    {Identifier}                    { return LexerToken.newVar(yytext()); }

    /* number */
    {Number}						{ return LexerToken.newNumber(yytext()); }

    /* symbols */
    "-"                             { return TOKEN_MINUS; }
    "+"                             { return TOKEN_PLUS; }
    "*"                             { return TOKEN_MULT; }
    "/"                             { return TOKEN_DIV; }
    ">"                             { return TOKEN_RUN; }
    "("                             { return TOKEN_BRACKET_OPEN; }
    ")"                             { return TOKEN_BRACKET_CLOSE; }
    "="                             { return TOKEN_EQUAL; }


    /* whitespace */
    {WhiteSpace}                   { /* ignore */ }

    /* end of file */
    <<EOF>>          				 { return TOKEN_STOP; }
}



/* error fallback */
.                             { return LexerToken.newError(this.yytext()); }

