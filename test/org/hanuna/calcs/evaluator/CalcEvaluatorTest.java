package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.parser.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author erokhins
 */
public class CalcEvaluatorTest {

    public void runParserEvaluatorTest(String s, int k) {
        Lexer l = new Lexer(s, true);
        try{
            ListOfVars list = Parser.parseListOfVars(l);
            ParserNode n = Parser.parseExpression(l);
            try{
                int x = n.accept(new CalcEvaluator(list));
                assertEquals(x, k);
            } catch (ExpressionVisitorError e) {
                fail(e.getMessage());
            }
        } catch (ParserError e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testVisitBin() {
        runParserEvaluatorTest("a=1 b=2 >a+2*b+a-b", 4);
        runParserEvaluatorTest("a=-2 b=1 >(a)*a - b + a", 1);
        runParserEvaluatorTest("a=-2 b=1 >a*(b-2)", 2);
    }
}
