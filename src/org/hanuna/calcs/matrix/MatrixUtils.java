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

    public T determinant(MatrixFunction<T> m) {
        if (m.height() != m.width() || m.height() == 0) {
            throw new IllegalArgumentException("determinant not supported for matrix " + m.width() + "x" + m.height());
        }
        if (m.height() == 1) {
            return m.get(0, 0);
        }
        T sum = field.getZero();
        T sign = field.getUnityElement();
        for (int y = 0; y < m.height(); y++) {
            T d = determinant(MatrixFunctionFactory.matrixCutCrossFunction(m, 0, y));
            d = field.mult(d, m.get(0, y));
            d = field.mult(d, sign);
            sum = field.add(sum, d);
            sign = field.negative(sign);
        }

        return sum;
    }

}
