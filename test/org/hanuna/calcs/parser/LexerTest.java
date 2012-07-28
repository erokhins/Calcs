package org.hanuna.calcs.parser;

import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.LexerToken;
import org.hanuna.calcs.lexer.LexerTokenType;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author erokhins
 */
public class LexerTest {

    public String lexerStringReturn(Reader r) throws IOException {
        StringBuilder s = new StringBuilder();
        FlexLexer l = new FlexLexer(r);
        LexerToken t = l.next();
        while (t.getType() != LexerTokenType.STOP) {
            s.append(t.getType().toString() + ":" + t.getString() + " ");
            t = l.next();
        }
        s.append(t.getType().toString() + ":" + t.getString());
        return s.toString();
    }

    public void runTest(String inL, String outL) {
        try {
            String s = lexerStringReturn(new StringReader(inL));
            assertEquals(s, outL);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSymbols() {
        runTest("12>2 a+_= - */",
                "number:12 >: number:2 var:a +: var:_ =: -: *: /: end:");
    }


    @Test
    public void testVarsAndNumbers() {
        runTest("aAzZbb34_d 23a2-2 +as-",
                "var:aAzZbb34_d number:23 var:a2 -: number:2 +: var:as -: end:");
    }


    @Test
    public void testErrorSymbols() {
        runTest("asd%_2!",
                "var:asd error:% var:_2 error:! end:");

        runTest("+-!",
                "+: -: error:! end:");
    }


    @Test
    public void testSpace() {
        runTest("as\n12\r2 2",
                "var:as number:12 number:2 number:2 end:");
    }

    @Test
    public void testNumbers() {
        runTest("12.1 2. ",
                "number:12.1 number:2 error:. end:");
        runTest(".1 ",
                "error:. number:1 end:");
    }


}