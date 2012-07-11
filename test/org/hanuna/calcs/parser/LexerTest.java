package org.hanuna.calcs.parser;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static junit.framework.Assert.assertTrue;

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
        s += t.getT();
        return s;
    }

    @Test
    public void test1(){
       StringReader r = new StringReader("  2 \n dd _dfg _25");
       assertTrue("Чё за хрень???", lexerStringReturn(r).equals("11:2 12:dd 12:_dfg 12:_2 -1"));

        r = new StringReader("  2 \n dd _dfg _1");
        assertTrue("Lexer", lexerStringReturn(r).equals("11:2 12:dd 12:_dfg 12:_2 -1"));

    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("junitfaq.SimpleTest");
    }

}
