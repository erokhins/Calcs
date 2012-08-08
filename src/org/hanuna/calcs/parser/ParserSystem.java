package org.hanuna.calcs.parser;

import org.hanuna.calcs.evaluator.RingEvaluator;
import org.hanuna.calcs.fields.DoubleField;
import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.polynoms.*;
import org.hanuna.calcs.syntaxtree.SyntaxTreeNode;
import org.hanuna.calcs.vartable.PolynomVarTable;

import java.io.IOException;

/**
 * @author erokhins
 */
public class ParserSystem extends Parser {
    public static final Ring<Double> numberRing = new DoubleField();
    public static final PolynomRing<Double> ring = new PolynomRing<Double>(numberRing);
    public static final PolynomVarTable<Double> pvTable = new PolynomVarTable<Double>(numberRing);
    
    public static final ParserDouble<Polynom<Double>> parserDouble = new ParserDouble<Polynom<Double>>() {
        @Override
        public Polynom<Double> parseDouble(double d) {
            return ListPolynom.constantPolynom(d);
        }
    };
    
    public static Polynom<Double> parsePolynom(Lexer l) throws ParserException, IOException{
        SyntaxTreeNode n = parseSum(l);
        
        RingEvaluator<Polynom<Double>> ce = new RingEvaluator<Polynom<Double>>(ring, parserDouble);
        Polynom<Double> p = n.accept(ce, pvTable);
        p = ring.simplify(p);
        return p;
    }

    public static String polynomToStr(Polynom<Double> p) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < p.size(); i++) {
            Monom<Double> m = p.getMonom(i);
            PowersVariables pv = m.getPowersVariables();
            double v = m.getValue();
                if (s.length() != 0) {
                    s.append(" + ");
                }
                s.append(v + " ");
                for (int j = 0; j < pv.maxCountVar(); j++) {
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

        return s.toString();
    }
}
