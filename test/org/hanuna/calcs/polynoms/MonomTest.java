package org.hanuna.calcs.polynoms;

import org.junit.Test;
import static org.hanuna.calcs.testUtils.*;
import static junit.framework.Assert.*;


/**
 * @author erokhins
 */
public class MonomTest {

    public void  assertMonom(Monom<Integer> m, String sPV, int value) {
        assertTrue(m.getPowersVariables().equalPV(parsePV(sPV)));
        assertTrue(m.getValue() == value);
    }

    @Test
    public void testConstMonom() throws Exception {
        Monom<Integer> m1 = Monom.constMonom(5);
        assertMonom(m1, "", 5);
        Monom<Integer> m2 = Monom.constMonom(0);
        assertMonom(m2, "", 0);
    }

    @Test
    public void testSingleVarMonom() throws Exception {
        Monom<Integer> m1 = Monom.SingleVarMonom(5, -4);
        assertMonom(m1, "0 0 0 0 0 1", -4);
        Monom<Integer> m2 = Monom.SingleVarMonom(-1, 0);
        assertMonom(m2, "0 0 0 0 0 0", 0);
    }

    public void runTestMonom(String sPV, int k) {
        Monom<Integer> m = new Monom<Integer>(parsePV(sPV), k);
        PowersVariables pv = m.getPowersVariables();
        assertTrue(pv.equalPV(parsePV(sPV)));
    }

    @Test
    public void testMonom() {
        runTestMonom("1 2 3", 20);
        runTestMonom("", -239239);
    }

}
