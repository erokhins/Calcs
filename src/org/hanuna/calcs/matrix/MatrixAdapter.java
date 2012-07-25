package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public class MatrixAdapter {
    public static <T> MatrixRow<T> toMatrixRow(final MatrixLineFunction<T> mlf) {
        return new MatrixRow<T>() {
            @Override
            public T get(int n) {
                return mlf.get(n);
            }

            @Override
            public int size() {
                return mlf.size();
            }
        };
    }
    public static <T> MatrixColumn<T> toMatrixColumn(final MatrixLineFunction<T> mlf) {
        return new MatrixColumn<T>() {
            @Override
            public T get(int n) {
                return mlf.get(n);
            }

            @Override
            public int size() {
                return mlf.size();
            }
        };
    }
}
