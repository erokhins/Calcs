package org.hanuna.calcs.polynoms;

import org.hanuna.calcs.fields.DoubleField;
import org.hanuna.calcs.fields.IntegerRing;
import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.fields.RingTest;
import org.hanuna.calcs.parser.ParserException;
import org.hanuna.calcs.polynoms.ListPolynom;
import org.hanuna.calcs.polynoms.Polynom;
import org.hanuna.calcs.polynoms.PolynomRing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.hanuna.calcs.testUtils.parseDoublePolynom;
import static org.hanuna.calcs.testUtils.polynomToString;


/**
 * @author erokhins
 */
public class PolynomRingTest extends RingTest<Polynom<Integer>> {
    @Override
    public Ring<Polynom<Integer>> getRing() {
        return new PolynomRing<Integer>(new IntegerRing());
    }

    @Override
    public List<Polynom<Integer>> getStartListOfElements() {
        List<Polynom<Integer>> list = new ArrayList<Polynom<Integer>>(10);
        list.add(ListPolynom.singleVarPolynom(2, 239));
        list.add(ListPolynom.singleVarPolynom(1, -239));
        list.add(ListPolynom.singleVarPolynom(3, 2));
        list.add(ListPolynom.singleVarPolynom(5, 239));
        list.add(ListPolynom.constantPolynom(5));

        return list;
    }

    public static Ring<Polynom<Double>>  doubleRing = new PolynomRing<Double>(new DoubleField());

    @Test
    public void testMultPolynom() throws ParserException {
        Polynom<Double> a = parseDoublePolynom("(1 2):2");
        Polynom<Double> b = parseDoublePolynom("(2 3):3 (3 4):5 (4 2):8");
        Polynom<Double> c = doubleRing.mult(a, b);
        assertEquals("(3 5):6.0 (4 6):10.0 (5 4):16.0", polynomToString(c));
    }
}
