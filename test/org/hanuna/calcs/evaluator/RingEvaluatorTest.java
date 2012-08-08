package org.hanuna.calcs.evaluator;

import org.hanuna.calcs.fields.DoubleField;
import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.parser.Parser;
import org.hanuna.calcs.parser.ParserDouble;
import org.hanuna.calcs.parser.ParserException;
import org.hanuna.calcs.parser.ParserTableVars;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.DoubleVarTable;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author erokhins
 */
public class RingEvaluatorTest {

    public void runParserEvaluatorTest(String s, double k) {
        try {
            Lexer l = new FlexLexer(s);
            DoubleVarTable list = ParserTableVars.parserTableVars(l);
            SyntaxTreeNode n = Parser.parseExpression(l);
            ParserDouble<Double> pd = new ParserDouble<Double>() {
                @Override
                public Double parseDouble(double d) {
                    return d;
                }
            };
            double x = n.accept(new FieldEvaluator<Double>(new DoubleField(), pd), list);
            assertEquals((Double) x,(Double)  k);
        } catch (ParserException e) {
            fail(e.getMessage());
        } catch (IOException e) {
            fail(e.getMessage());
        } catch (CalcEvaluatorException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testVisit() {
        runParserEvaluatorTest("a=1 b=2 >a+2*b+a-b", 4);
        runParserEvaluatorTest("a=-2 b=1 >(a)*a - b + a", 1);
        runParserEvaluatorTest("a=-2 b=1 >a*(b-2)", 2);

        runParserEvaluatorTest("> -2 + 2", 0);
    }
}
