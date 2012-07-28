package org.hanuna.calcs.parser;

import org.hanuna.calcs.evaluator.RingEvaluator;
import org.hanuna.calcs.fields.IntegerRing;
import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.polynoms.*;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.PolynomVarTable;

import java.io.IOException;

/**
 * @author erokhins
 */
public class ParserPolynom extends Parser {
    public static final Ring<Integer> integerRing = new IntegerRing();
    public static final Ring<Polynom<Integer>> ring = new PolynomRing<Integer>(integerRing);
    public static final PolynomVarTable<Integer> pvTable = new PolynomVarTable<Integer>(integerRing);

    public static Polynom<Integer> parsePolynom(FlexLexer l) throws ParserException, IOException{
        SyntaxTreeNode n = parseSum(l);
        RingEvaluator<Polynom<Integer>, Ring<Polynom<Integer>>> ce = new RingEvaluator<Polynom<Integer>, Ring<Polynom<Integer>>>(ring);
        return n.accept(ce, pvTable);
    }

    public static String polynomToStr(Polynom<Integer> p) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < p.size(); i++) {
            Monom<Integer> m = p.getMonom(i);
            PowerVariables pv = m.getPowerVariables();
            int v = m.getValue();
            if (v != 0) {
                if (s.length() != 0) {
                    s.append(" + ");
                }
                s.append(v + " ");
                for (int j = 0; j < pv.maxVarNumber(); j++) {
                    int d = pv.getDegree(j);
                    if (d != 0) {
                        if (d == 1) {
                            s.append("* " + pvTable.varNumberToStr(j) + " ");
                        } else {
                            s.append("* " + pvTable.varNumberToStr(j) + "^" + d + " ");
                        }
                    }
                }
            }
        }

        return s.toString();
    }
}
