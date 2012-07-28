package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.fields.IntegerRing;
import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.parser.*;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.IntegerVarTable;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author erokhins
 */
public class RingEvaluatorTest {

    public void runParserEvaluatorTest(String s, int k) {
        try {
            FlexLexer l = new FlexLexer(s);
            IntegerVarTable list = ParserTableVars.parserTableVars(l);
            SyntaxTreeNode n = Parser.parseExpression(l);
            int x = n.accept(new RingEvaluator<Integer, Ring<Integer>>(new IntegerRing()), list);
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
