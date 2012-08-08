package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public interface Matrix<T> {
    public T get(int x, int y);
    public int xSize();
    public int ySize();
}
