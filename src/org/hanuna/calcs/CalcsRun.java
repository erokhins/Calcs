package org.hanuna.calcs;

import org.hanuna.calcs.evaluator.CalcEvaluatorException;
import org.hanuna.calcs.evaluator.RingEvaluator;
import org.hanuna.calcs.fields.IntegerRing;
import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.parser.Parser;
import org.hanuna.calcs.parser.ParserException;
import org.hanuna.calcs.parser.ParserTableVars;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.IntegerVarTable;

import java.io.IOException;
import java.io.Reader;

/**
 * @author erokhins
 */
public class CalcsRun {

    public static Integer calcsRun(Reader r) {
        try {
            FlexLexer l = new FlexLexer(r);
            IntegerVarTable table = ParserTableVars.parserTableVars(l);
            SyntaxTreeNode n = Parser.parseExpression(l);

            try {
                RingEvaluator<Integer, Ring<Integer>> ce = new RingEvaluator<Integer, Ring<Integer>>(new IntegerRing());
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
