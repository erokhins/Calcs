package org.hanuna.calcs.parser;

import org.hanuna.calcs.evaluator.StringEvaluator;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author erokhins
 */
public class ParserTest {


    @Test
    public void testParseListOfVars() {
        String s = "a = 4 b = -3 c = 0";
        Lexer l = new Lexer(s, true);
        try{
            ListOfVars list = Parser.parseListOfVars(l);
            assertEquals(list.get("a"), (Integer) 4);
            assertEquals(list.get("b"), (Integer) (-3));
            assertEquals(list.get("c"), (Integer) 0);
            assertEquals(list.get("bb"), null);
        } catch (ParserError e) {
            fail(e.getMessage());
        }
    }

    public void runTestErrorsListOfVars(String inputS, String errorS) {
        Lexer l = new Lexer(inputS, true);
        try{
            Parser.parseListOfVars(l);
            fail();
        } catch (ParserError e) {
            assertEquals(e.getMessage(), errorS);
        }
    }

    @Test
    public void testErrorsParseListOfVars() {
        runTestErrorsListOfVars("a a","expected '=', but found a" );
        runTestErrorsListOfVars("a = a", "expected number, but found a");
    }


    public void runTestParseExpression(String inputS, String result) {
        Lexer l = new Lexer(inputS, true);
        try{
            ParserNode n = Parser.parseExpression(l);
            try{
                String s = n.accept(new StringEvaluator());
                assertEquals(s, result);
            } catch (ExpressionVisitorError e) {
                fail(e.getMessage());
            }
        } catch (ParserError e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParseExpression() {
       runTestParseExpression(">1+2*(a-4)",
                            "{number:1 + {number:2 * {var:a - number:4}}}");

        runTestParseExpression(">a/b-c",
                "{{var:a / var:b} - var:c}");
    }
}
