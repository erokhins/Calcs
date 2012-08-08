package org.hanuna.calcs.polynoms;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;
import static org.hanuna.calcs.testUtils.parseIntList;
import static org.hanuna.calcs.testUtils.parsePV;

/**
 * @author erokhins
 */
public class PowersVariablesTest {

    public void testPV(PowersVariables l, List<Integer> outM) {
        for (int i = 0; i < outM.size(); i++) {
            assertEquals((int) outM.get(i), l.getDegree(i));
        }
    }

    public void runTestListPowerVariables(String in, String out) {
        List<Integer> inM, outM;
        inM = parseIntList(in);
        outM = parseIntList(out);
        PowersVariables lpv = ListPowersVariables.copyFromList(inM);
        testPV(lpv, outM);
    }

    @Test
    public void testListPowerVariables() {
        runTestListPowerVariables("1 3 1 0", "1 3 1 0 0 0 0");
        runTestListPowerVariables("0 0", "0 0 0 0 0 0 0");
        runTestListPowerVariables("", "0 0 0 0 0 0 0");
        runTestListPowerVariables("", "0 0 0 0 0 0 0");
    }

    public void runTestSinglePV(int indexVar, String out) {
        SingleVariable sv = new SingleVariable(indexVar);
        List<Integer> outM = parseIntList(out);
        testPV(sv, outM);
    }

    @Test
    public void testSinglePowerVariables() {
        runTestSinglePV(1, "0 1 0 0");
        runTestSinglePV(0, "1 0 0 0");
        runTestSinglePV(-1, "0 0 0 0 0 0");
    }

    @Test
    public void testEqualsPV() {
        PowersVariables pv1, pv2, pv3, pv4, pv5, pv6;
        pv1 = parsePV("1 2 3 0 0");
        pv2 = parsePV("1 2 3");
        pv3 = new SingleVariable(2);
        pv4 = parsePV("0 0 1");

        assertTrue(pv1.equalPV(pv2) && pv2.equalPV(pv1));
        assertFalse(pv1.equalPV(pv3) || pv3.equalPV(pv1));
        assertTrue(pv3.equalPV(pv4) && pv4.equalPV(pv3));

        pv5 = parsePV("");
        pv6 = new SingleVariable(-1);

        assertTrue(pv5.equalPV(pv6) && pv6.equalPV(pv5));
    }



}
