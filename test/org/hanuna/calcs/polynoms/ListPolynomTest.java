package org.hanuna.calcs.polynoms;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hanuna.calcs.testUtils.*;

/**
 * @author erokhins
 */
public class ListPolynomTest {

    @Test
    public void testSingleVarPolynom() {
        Polynom<Double> p = ListPolynom.singleVarPolynom(3, 3.4);
        assertEquals(polynomToString(p), "(0 0 0 1):3.4");

        p = ListPolynom.singleVarPolynom(0, -2.0);
        assertEquals(polynomToString(p), "(1):-2.0");

        p = ListPolynom.singleVarPolynom(-1, 0.0);
        assertEquals(polynomToString(p), "():0.0");
    }

    @Test
    public void testConstantPolynom() throws Exception {

    }

    @Test
    public void testSize() throws Exception {

    }

    @Test
    public void testGetMonom() throws Exception {

    }

}
