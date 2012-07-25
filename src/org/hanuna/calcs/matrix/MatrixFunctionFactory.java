package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public class MatrixFunctionFactory {

    public static <T> MatrixLineFunction<T> matrixRowFunction(final Matrix<T> m, final int numberRow) {
        if (numberRow >= m.width() || numberRow < 0) {
            throw new IllegalArgumentException("incorrect numberRow: " + numberRow + " m.height = " + m.height());
        }
        return new MatrixLineFunction<T>() {
            @Override
            public T get(int n) {
                return m.get(n, numberRow);
            }

            @Override
            public int size() {
                return m.width();
            }
        };
    }

    public static <T> MatrixLineFunction<T> matrixColumnFunction(final Matrix<T> m, final int numberColumn) {
        if (numberColumn >= m.width() || numberColumn < 0) {
            throw new IllegalArgumentException("incorrect numberColumn: " + numberColumn + " m.width = " + m.width());
        }
        return new MatrixLineFunction<T>() {
            @Override
            public T get(int n) {
                return m.get(numberColumn, n);
            }

            @Override
            public int size() {
                return m.height();
            }
        };
    }

    public static <T> MatrixFunction<T> matrixCutCrossFunction(final MatrixFunction<T> m, final int xCut, final int yCut) {
        if (xCut >= m.width() || xCut < 0) {
            throw new IllegalArgumentException("incorrect x: " + xCut + " m.width = " + m.width());
        }
        if (yCut >= m.height() || yCut < 0) {
            throw new IllegalArgumentException("incorrect y: " + yCut + " m.height = " + m.height());
        }
        if (m.height() < 2 || m.width() < 2) {
            throw new IllegalArgumentException("incorrect size:" + m.width() + "x" + m.height());
        }
        return new MatrixFunction<T>() {
            @Override
            public T get(int x, int y) {
                x = (x < xCut) ? x : x + 1;
                y = (y < yCut) ? y : y + 1;

                return m.get(x, y);
            }

            @Override
            public int height() {
                return m.height() - 1;
            }

            @Override
            public int width() {
                return m.width() - 1;
            }
        };
    }

    public static <T> MatrixFunction<T> matrixCutColumnFunction(final MatrixFunction<T> m, final int columnNumber) {
        if (columnNumber >= m.width() || columnNumber < 0) {
            throw new IllegalArgumentException("incorrect columnNumber: " + columnNumber + " m.width = " + m.width());
        }
        if (m.width() < 2) {
            throw new IllegalArgumentException("incorrect size:" + m.width() + "x" + m.height());
        }
        return new MatrixFunction<T>() {
            @Override
            public T get(int x, int y) {
                x = (x < columnNumber) ? x : x + 1;

                return m.get(x, y);
            }

            @Override
            public int height() {
                return m.height();
            }

            @Override
            public int width() {
                return m.width() - 1;
            }
        };
    }


    public static <T> MatrixFunction<T> matrixReplaceColumnFunction(final MatrixFunction<T> m, final MatrixColumn<T> mc,
                                                                    final int replaceColumnNumber) {
        if (m.height() != mc.size()) {
            throw new IllegalArgumentException("incorrect size column = " + mc.size() + " m.height = " + m.height());
        }
        if (replaceColumnNumber >= m.width() || replaceColumnNumber < 0) {
            throw new IllegalArgumentException("incorrect replaceColumnNumber = " + replaceColumnNumber
                    + " m.width = " + m.width());
        }
        return new MatrixFunction<T>() {
            @Override
            public T get(int x, int y) {
                if (x == replaceColumnNumber) {
                    return mc.get(y);
                } else {
                    return m.get(x, y);
                }
            }

            @Override
            public int height() {
                return m.height();
            }

            @Override
            public int width() {
                return m.width();
            }
        };
    }
}
