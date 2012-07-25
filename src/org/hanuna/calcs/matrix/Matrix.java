package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public interface Matrix<T> extends MatrixFunction<T> {

    public MatrixRow<T> getRow(int numberRow);
    public MatrixColumn<T> getColumn(int numberColumn);

}
