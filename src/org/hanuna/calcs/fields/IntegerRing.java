package org.hanuna.calcs.fields;

/**
 * @author erokhins
 */
public class IntegerRing implements Ring<Integer> {
    @Override
    public Integer getZero() {
        return 0;
    }

    @Override
    public Integer getUnityElement() {
        return 1;
    }

    @Override
    public boolean isZero(Integer a) {
        return a == 0;
    }

    @Override
    public boolean isUnityElement(Integer a) {
        return a == 1;
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer negative(Integer a) {
        return  -a;
    }

    @Override
    public Integer mult(Integer a, Integer b) {
        return a * b;
    }

    @Override
    public Integer parseNumber(String s) {
        return Integer.parseInt(s);
    }
}
