package org.hanuna.calcs.vartable;

import org.hanuna.calcs.fields.Ring;
import org.hanuna.calcs.polynoms.ListPolynom;
import org.hanuna.calcs.polynoms.Polynom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erokhins
 */
public class PolynomVarTable<T> implements VarTable<Polynom<T>> {
    private final Map<String, Integer> mapVars = new HashMap<String, Integer>();
    private final Map<Integer, String> inverseMapVars = new HashMap<Integer, String>();
    private int k = 0;
    private final Ring<T> ring;

    public PolynomVarTable(Ring<T> ring) {
        this.ring = ring;
    }


    @Override
    public Polynom<T> get(String name) {
        Integer t = mapVars.get(name);
        if (t == null) {
            mapVars.put(name, k);
            inverseMapVars.put(k, name);
            t = k;
            k++;
        }

        return ListPolynom.singleVarPolynom(t, ring.getUnityElement());
    }

    @Override
    public void put(String name, Polynom<T> value) {
    }

    public String varNumberToStr(int varNumber) {
        return inverseMapVars.get(varNumber);
    }
}
