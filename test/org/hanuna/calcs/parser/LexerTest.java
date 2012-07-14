package org.hanuna.calcs.parser;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

/**
 * @author erokhins
 */
public class LexerTest {

    public String lexerStringReturn(Reader r) {
        String s = "";
        Lexer l = new Lexer(r);
        LexerToken t = l.next();
        while (t.getT() >= 0) {
            s += t.getT() + ":" + t.getS() + " ";
            t = l.next();
        }
        s += t.getT()+":"+t.getS();
        return s;
    }

    public void runTest(String inL, String outL) {
        String s = lexerStringReturn(new StringReader(inL));
        assertEquals(s, outL);
    }

    @Test
    public void testSymbols() {
        runTest("12>2 a+_= - */",
                "11:12 10: 11:2 12:a 1: 12:_ 7: 2: 3: 4: -1:");
    }


    @Test
    public void testVarsAndNumbers() {
        runTest("aAzZbb34_d 23a2-2 +as-",
                "12:aAzZbb34_d 11:23 12:a2 2: 11:2 1: 12:as 2: -1:");
    }


    @Test
    public void testErrorSymbols() {
        runTest("asd%_2!",
                "12:asd -3:%");

        runTest("+-!",
                "1: 2: -3:!");
    }


    @Test
    public void testSpace() {
        runTest("as\n12\r2 2",
                "12:as 11:12 11:2 11:2 -1:");
    }



}