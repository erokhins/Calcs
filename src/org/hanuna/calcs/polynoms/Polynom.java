package org.hanuna.calcs.polynoms;

/**
 * @author erokhins
 */
public interface Polynom<T> {

    public int size();
    public Monom<T> getMonom(int numberMonom);

}
