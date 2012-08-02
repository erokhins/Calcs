package org.hanuna.calcs;

import org.hanuna.calcs.evaluator.CalcEvaluatorException;
import org.hanuna.calcs.evaluator.FieldEvaluator;
import org.hanuna.calcs.fields.DoubleField;
import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.lexer.OldLexer;
import org.hanuna.calcs.parser.Parser;
import org.hanuna.calcs.parser.ParserDouble;
import org.hanuna.calcs.parser.ParserException;
import org.hanuna.calcs.parser.ParserTableVars;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.DoubleVarTable;

import java.io.IOException;
import java.io.Reader;

/**
 * @author erokhins
 */
public class RunCalcs {

    public static Double calcsRun(Reader r) {
        try {
            Lexer l = new OldLexer(r);
            DoubleVarTable table = ParserTableVars.parserTableVars(l);
            SyntaxTreeNode n = Parser.parseExpression(l);
            ParserDouble<Double> pd = new ParserDouble<Double>() {
                @Override
                public Double parseDouble(double d) {
                    return d;
                }
            };

            try {
                FieldEvaluator<Double> ce = new FieldEvaluator<Double>(new DoubleField(), pd);
                return n.accept(ce, table);
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
