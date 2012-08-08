package org.hanuna.calcs.matrix;

import org.hanuna.calcs.fields.Field;

/**
 * @author erokhins
 */
public class MatrixUtils<T> {
    private final Field<T> field;

    public MatrixUtils(Field<T> field) {
        this.field = field;
    }

    public T determinant(Matrix<T> m) {
        if (m.ySize() != m.xSize() || m.ySize() == 0) {
            throw new IllegalArgumentException("determinant not supported for matrix " + m.xSize() + "x" + m.ySize());
        }
        if (m.ySize() == 1) {
            return m.get(0, 0);
        }
        T sum = field.getZero();
        T sign = field.getUnityElement();
        for (int y = 0; y < m.ySize(); y++) {
            T d = determinant(MatrixFactory.matrixCutCrossFunction(m, 0, y));
            d = field.mult(d, m.get(0, y));
            d = field.mult(d, sign);
            sum = field.add(sum, d);
            sign = field.negative(sign);
        }

        return sum;
    }

    public static void checkGetRequest(Matrix m, int x, int y) {
        if (m.xSize() <= x || x < 0) {
            throw new IllegalArgumentException("bad x coordinate = " + x + ". Matrix size is " + x + "x" + y);
        }
        if (m.ySize() <= y || y < 0) {
            throw new IllegalArgumentException("bad y coordinate = " + y + ". Matrix size is " + x + "x" + y);
        }
    }

    public static void checkGetRequest(Column c, int n) {
        if (c.size() <= n || n < 0) {
            throw new IllegalArgumentException("bad index = " + n + ". Column size is " + n);
        }
    }

}
