package org.hanuna.calcs.fields;

/**
 * @author erokhins
 */
public class DoubleField implements Field<Double> {
    @Override
    public Double getZero() {
        return 0.0;
    }

    @Override
    public Double getUnityElement() {
        return 1.0;
    }

    @Override
    public boolean isZero(Double a) {
        return a == 0.0;
    }

    @Override
    public boolean isUnityElement(Double a) {
        return a == 1.0;
    }

    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double negative(Double a) {
        return - a;
    }

    @Override
    public Double mult(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double inverse(Double a) throws FieldCalculateException {
        if (a == 0) {
            throw new FieldCalculateException("divide by zero!");
        }
        return 1 / a;
    }


}
