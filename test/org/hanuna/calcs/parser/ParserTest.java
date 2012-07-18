package org.hanuna.calcs.parser;

import org.hanuna.calcs.evaluator.CalcEvaluatorException;
import org.hanuna.calcs.evaluator.StringEvaluator;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author erokhins
 */
public class ParserTest {


    @Test
    public void testParseListOfVars() {
        String s = "a = 4 b = -3 c = 0";
        try {
            Lexer l = new Lexer(s);
            TableVars list = ParserTableVars.parserTableVars(l);
            assertEquals(list.get("a"), (Integer) 4);
            assertEquals(list.get("b"), (Integer) (-3));
            assertEquals(list.get("c"), (Integer) 0);
            assertEquals(list.get("bb"), null);
        } catch (ParserException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    public void runTestErrorsListOfVars(String inputS, String errorS) {
        try {
            Lexer l = new Lexer(inputS);
            ParserTableVars.parserTableVars(l);
            fail();
        } catch (ParserException e) {
            assertEquals(e.getMessage(), errorS);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testErrorsParseListOfVars() {
        runTestErrorsListOfVars("a a","expected '=', but found a" );
        runTestErrorsListOfVars("a = a", "expected number, but found a");
    }


    public void runTestParseExpression(String inputS, String result) {
        try {
            Lexer l = new Lexer(inputS);
            ParserNode n = Parser.parseExpression(l);
            try {
                String s = n.accept(new StringEvaluator());
                assertEquals(s, result);
            } catch (CalcEvaluatorException e) {
                fail(e.getMessage());
            }
        } catch (ParserException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParseExpression() {
       runTestParseExpression(">1+2*(a-4)",
                            "{number:1 + {number:2 * {var:a - number:4}}}");

        runTestParseExpression(">a/b-c",
                "{{var:a / var:b} - var:c}");

        runTestParseExpression(">a - b + c - d",
                               "{var:a - {var:b - {var:c - var:d}}}");

        runTestParseExpression(">abc/b/c*d",
                               "{var:abc / {var:b * {var:c / var:d}}}");

    }
}
