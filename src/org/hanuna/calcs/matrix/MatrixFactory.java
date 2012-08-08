package org.hanuna.calcs.matrix;

import static org.hanuna.calcs.matrix.MatrixUtils.checkGetRequest;

/**
 * @author erokhins
 */
public class MatrixFactory {



    public static <T> Column<T> matrixColumnFunction(final Matrix<T> m, final int numberColumn) {
        if (numberColumn >= m.xSize() || numberColumn < 0) {
            throw new IllegalArgumentException("incorrect numberColumn: " + numberColumn + " m.xSize = " + m.xSize());
        }
        return new Column<T>() {
            @Override
            public T get(int n) {
                checkGetRequest(this, n);
                return m.get(numberColumn, n);
            }

            @Override
            public int size() {
                return m.ySize();
            }
        };
    }

    public static <T> Matrix<T> matrixCutCrossFunction(final Matrix<T> m, final int xCut, final int yCut) {
        checkGetRequest(m, xCut, yCut);
        if (m.ySize() < 2 || m.xSize() < 2) {
            throw new IllegalArgumentException("incorrect matrix size:" + m.xSize() + "x" + m.ySize());
        }
        return new Matrix<T>() {
            @Override
            public T get(int x, int y) {
                checkGetRequest(this, x, y);
                x = (x < xCut) ? x : x + 1;
                y = (y < yCut) ? y : y + 1;
                return m.get(x, y);
            }

            @Override
            public int ySize() {
                return m.ySize() - 1;
            }

            @Override
            public int xSize() {
                return m.xSize() - 1;
            }
        };
    }

    public static <T> Matrix<T> matrixCutColumnFunction(final Matrix<T> m, final int columnNumber) {
        if (columnNumber >= m.xSize() || columnNumber < 0) {
            throw new IllegalArgumentException("incorrect columnNumber: " + columnNumber + " m.xSize = " + m.xSize());
        }
        if (m.xSize() < 2) {
            throw new IllegalArgumentException("incorrect size:" + m.xSize() + "x" + m.ySize());
        }
        return new Matrix<T>() {
            @Override
            public T get(int x, int y) {
                checkGetRequest(this, x, y);
                x = (x < columnNumber) ? x : x + 1;
                return m.get(x, y);
            }

            @Override
            public int ySize() {
                return m.ySize();
            }

            @Override
            public int xSize() {
                return m.xSize() - 1;
            }
        };
    }


    public static <T> Matrix<T> matrixReplaceColumnFunction(
            final Matrix<T> m,
            final Column<T> cm,
            final int replaceColumnNumber)
    {
        if (m.ySize() != cm.size()) {
            throw new IllegalArgumentException("incorrect size column = " + cm.size() + " m.ySize = " + m.ySize());
        }
        if (replaceColumnNumber >= m.xSize() || replaceColumnNumber < 0) {
            throw new IllegalArgumentException("incorrect replaceColumnNumber = " + replaceColumnNumber
                    + " m.xSize = " + m.xSize());
        }
        return new Matrix<T>() {
            @Override
            public T get(int x, int y) {
                if (x == replaceColumnNumber) {
                    return cm.get(y);
                } else {
                    return m.get(x, y);
                }
            }

            @Override
            public int ySize() {
                return m.ySize();
            }

            @Override
            public int xSize() {
                return m.xSize();
            }
        };
    }
}
