package org.hanuna.calcs;

import org.hanuna.calcs.evaluator.CalcEvaluator;
import org.hanuna.calcs.evaluator.CalcEvaluatorException;
import org.hanuna.calcs.fields.IntegerRing;
import org.hanuna.calcs.parser.*;

import java.io.IOException;
import java.io.Reader;

/**
 * @author erokhins
 */
public class CalcsRun {

    public static Integer calcsRun(Reader r) {
        try {
            Lexer l = new Lexer(r);
            IntegerVarTable table = ParserTableVars.parserTableVars(l);
            ParserNode n = Parser.parseExpression(l);

            try {
                CalcEvaluator<Integer> ce = new CalcEvaluator<Integer>(new IntegerRing());
                int k = n.accept(ce, table);
                return k;
            } catch (CalcEvaluatorException e) {
                System.err.println(e.getMessage());
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ParserException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
