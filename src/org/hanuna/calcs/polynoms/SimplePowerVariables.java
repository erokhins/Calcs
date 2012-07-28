package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public class SimplePowerVariables extends AbstractPowerVariables {
    private final int numberVar;

    public SimplePowerVariables(int varNumber) {
        this.numberVar = varNumber;
    }

    @Override
    public int getDegree(int numberVar) {
        if (numberVar < 0) {
            throw new IllegalArgumentException("numberVar is < 0!");
        }
        if (numberVar != this.numberVar) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int maxVarNumber() {
        return numberVar + 1;
    }
}
