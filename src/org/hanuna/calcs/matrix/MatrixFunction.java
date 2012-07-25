package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public interface MatrixFunction<T> {
    /**
     * x <--> width
     * y <--> height
     */
    public T get(int x, int y);
    public int height();
    public int width();
}
