package org.hanuna.calcs.fields;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * @author erokhins
 */
public abstract class RingTest<T> {

    private final Ring<T> ring;
    private final List<T> listOfElements;

    private T getN(int n) {
        int absN = Math.abs(n);
        T t1 = ring.getUnityElement();
        T t = ring.getZero();
        for (int i = 0; i < absN; i++) {
            t = ring.add(t, t1);
        }
        if (n < 0) {
            return  ring.negative(t);
        } else {
            return t;
        }
    }

    public RingTest() {
        this.ring = getRing();
        List<T> startList = getStartListOfElements();
        listOfElements = new ArrayList<T>(2 * startList.size() + 10);
        for (int i = -3; i <= 3; i++) {
            listOfElements.add(getN(i));
        }
        for (int i = 0; i < startList.size(); i++) {
            T t = startList.get(i);
            listOfElements.add(t);
            listOfElements.add(ring.negative(t));
        }
    }

    public abstract Ring<T> getRing();

    public abstract List<T> getStartListOfElements();

    public boolean equalT(T a, T b) {
        T t = ring.negative(b);
        return ring.isZero(ring.add(a, t));
    }


    @Test
    public void testUnityElement() {
        assertTrue(ring.isUnityElement(ring.getUnityElement()));
        for (T t : listOfElements) {
            assertTrue(equalT(t, ring.mult(t, ring.getUnityElement())));
        }
    }

    @Test
    public void testZero() {
        assertTrue(ring.isZero(ring.getZero()));
        for (T t : listOfElements) {
            assertTrue(equalT(t, ring.add(t, ring.getZero())));
        }
    }


    @Test
    public void testAdd() {
        T t0 = ring.getZero();
        T t1 = ring.getUnityElement();
        T m1 = ring.negative(t1);
        assertTrue(ring.isUnityElement(ring.add(t0, t1)));
        assertTrue(ring.isZero(ring.add(m1, t1)));

        T t2 = ring.add(t1, t1);
        T m2 = ring.negative(t2);
        T t = ring.add(t1, m2);
        t = ring.add(t, t1);
        assertTrue(ring.isZero(t));
    }

    public void testMult() {
        assertTrue(ring.isUnityElement(ring.mult(getN(1), getN(1))));

        T t6 = ring.mult(getN(2), getN(3));
        assertTrue(equalT(t6, getN(6)));

        T m6 = ring.mult(getN(-2), getN(3));
        assertTrue(equalT(t6, getN(-6)));
    }

    @Test
    public void testNegative() {
        for (T t : listOfElements) {
            T nt = ring.negative(t);
            assertTrue(ring.isZero(ring.add(t, nt)));
        }
    }


    @Test
    public void testCommutative() {
        for (T a : listOfElements) {
            for (T b : listOfElements) {
                T t = ring.add(a, b);
                T tn = ring.add(b, a);
                assertTrue(equalT(t, tn));
            }
        }
    }


    @Test
    public void testAssociativeAdd() {
        for (T a : listOfElements) {
            for (T b : listOfElements) {
                for (T c : listOfElements) {
                    T t = ring.add(a, b);
                    t = ring.add(t, c);

                    T tn = ring.add(b, c);
                    tn = ring.add(a, tn);
                    assertTrue(equalT(t, tn));
                }
            }
        }
    }


    @Test
    public void testAssociativeMult() {
        for (T a : listOfElements) {
            for (T b : listOfElements) {
                for (T c : listOfElements) {
                    T t = ring.mult(a, b);
                    T t1 = ring.mult(t, c);

                    T tn = ring.mult(b, c);
                    tn = ring.mult(a, tn);
                    assertTrue( equalT(t, t));
                }
            }
        }
    }


    @Test
    public void testDistributive() {
        for (T a : listOfElements) {
            for (T b : listOfElements) {
                for (T c : listOfElements) {
                    T t = ring.add(a, b);
                    t = ring.mult(t, c);

                    T tn = ring.mult(a, c);
                    T tn1 =  ring.mult(b, c);
                    tn = ring.add(tn, tn1);
                    assertTrue("" + t + tn,equalT(t, tn));
                }
            }
        }
    }


}
