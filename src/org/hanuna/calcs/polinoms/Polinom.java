package org.hanuna.calcs.polinoms;

/**
 * @author erokhins
 */
public interface Polinom<T> {

    public int size();
    public Monom<T> getMonom(int numberMonom);

}
