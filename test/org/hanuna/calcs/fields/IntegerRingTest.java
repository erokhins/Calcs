package org.hanuna.calcs.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */
public class IntegerRingTest extends RingTest<Integer> {

    @Override
    public Ring<Integer> getRing() {
        return new IntegerRing();
    }

    @Override
    public List<Integer> getStartListOfElements() {
        List<Integer> list = new ArrayList<Integer>(10);
        list.add(100);
        list.add(200);
        list.add(5);
        list.add(27342);
        return list;
    }
}
