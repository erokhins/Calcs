package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.fields.IntegerRing;
import org.hanuna.calcs.parser.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author erokhins
 */
public class CalcEvaluatorTest {

    public void runParserEvaluatorTest(String s, int k) {
        try {
            Lexer l = new Lexer(s);
            IntegerVarTable list = ParserTableVars.parserTableVars(l);
            ParserNode n = Parser.parseExpression(l);
            int x = n.accept(new CalcEvaluator<Integer>(new IntegerRing()), list);
            assertEquals(x, k);
        } catch (ParserException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (CalcEvaluatorException e) {
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
