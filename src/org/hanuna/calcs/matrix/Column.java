package org.hanuna.calcs.matrix;

import static org.hanuna.calcs.matrix.MatrixUtils.checkGetRequest;

/**
 * @author erokhins
 */
public abstract class Column<T> implements Matrix<T> {
    @Override
    public final T get(int x, int y) {
        checkGetRequest(this, x, y);
        return null;
    }

    @Override
    public final int xSize() {
        return 1;
    }

    @Override
    public final int ySize() {
        return size();
    }

    public abstract T get(int n);
    public abstract int size();

}
