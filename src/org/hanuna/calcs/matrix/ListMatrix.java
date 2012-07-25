package org.hanuna.calcs.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */

public class ListMatrix<T> implements Matrix<T> {
    private final int width;
    private final int height;

    /**
     * M[x, y] = listOfElement.get(y * width + x)
     */
    private final List<T> listOfElement;

    public ListMatrix(MatrixFunction<T> mf) {
        width = mf.width();
        height = mf.height();
        listOfElement = new ArrayList<T>(width * height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                listOfElement.add(mf.get(x, y));
            }
        }
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public T get(int x, int y) throws MatrixException {
        return listOfElement.get(y * width + x);
    }

    @Override
    public MatrixRow<T> getRow(final int numberRow) {
        return getRow(numberRow, true);
    }

    public MatrixRow<T> getRow(final int numberRow, boolean onlyLink) {
        MatrixLineFunction<T> mlf = MatrixFunctionFactory.matrixRowFunction(this, numberRow);
        if (onlyLink) {
            return MatrixAdapter.toMatrixRow(mlf);
        } else {
            return new ListMatrixRow<T>(mlf);
        }
    }

        @Override
    public MatrixColumn<T> getColumn(int numberColumn) {
        return getColumn(numberColumn, true);
    }

    public MatrixColumn<T> getColumn(int numberColumn, boolean onlyLink) {
        MatrixLineFunction<T> mlf = MatrixFunctionFactory.matrixColumnFunction(this, numberColumn);
        if (onlyLink) {
            return MatrixAdapter.toMatrixColumn(mlf);
        } else {
            return new ListMatrixColumn<T>(mlf);
        }
    }


    public String toString() {
        StringBuilder s = new StringBuilder(width * height * 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                s.append(get(x, y) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
