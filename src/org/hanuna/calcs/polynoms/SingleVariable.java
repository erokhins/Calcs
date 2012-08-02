package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public class SingleVariable extends AbstractPowersVariables {

    private final int indexVar;

    /**
     * if(indexVar < 0) {
     *    All Powers of Variable = 0 (Monom "const")
     * }
     */
    public SingleVariable(int indexVar) {
        this.indexVar = indexVar;
    }

    @Override
    public int getDegree(int indexVar) {
        if (indexVar < 0) {
            throw new IllegalArgumentException("indexVar is < 0!");
        }
        if (indexVar != this.indexVar) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int maxCountVar() {
        return indexVar + 1;
    }
}
