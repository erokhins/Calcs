package org.hanuna.calcs.parser;

import org.hanuna.calcs.evaluator.CalcEvaluatorException;
import org.hanuna.calcs.evaluator.StringEvaluator;
import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.DoubleVarTable;
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
            Lexer l = new FlexLexer(s);
            DoubleVarTable list = ParserTableVars.parserTableVars(l);
            assertEquals(list.get("a"), (Double) 4.0);
            assertEquals(list.get("b"), (Double) (-3.0));
            assertEquals(list.get("c"), (Double) 0.0);
            assertEquals(list.get("bb"), null);
        } catch (ParserException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    public void runTestErrorsListOfVars(String inputS, String errorS) {
        try {
            Lexer l = new FlexLexer(inputS);
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
            Lexer l = new FlexLexer(inputS);
            SyntaxTreeNode n = Parser.parseExpression(l);
            try {
                String s = n.accept(new StringEvaluator(), null);
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
                            "{number:1.0 + {number:2.0 * {var:a - number:4.0}}}");

        runTestParseExpression(">a/b-c",
                "{{var:a / var:b} - var:c}");

        runTestParseExpression(">a - b + c - d",
                               "{var:a - {var:b - {var:c - var:d}}}");

        runTestParseExpression(">abc/b/c*d",
                               "{var:abc / {var:b * {var:c / var:d}}}");
        runTestParseExpression(">-1+1",
                               "{{- number:1.0} + number:1.0}");

    }
}
