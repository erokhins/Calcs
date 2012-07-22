package org.hanuna.calcs.solver;

import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.matrix.Matrix;
import org.hanuna.calcs.matrix.MatrixColumn;

import java.util.LinkedList;
import java.util.List;

/**
 * @author erokhins
 */
public class SolverSystem<T> {
    private Matrix<T> matrix;
    private MatrixColumn<T> columnConst;
    private Field<T> field;

    public SolverSystem(Matrix<T> matrix, MatrixColumn<T> columnConst, Field<T> field) throws SolverSystemException {
        if (matrix.getHeight() != matrix.getWidth()) {
            throw new SolverSystemException("count vars = " + matrix.getHeight()
                                    + ", but count equations = " + matrix.getWidth());
        }
        if (matrix.getHeight() != columnConst.size()) {
            throw new SolverSystemException("columnConst = " + columnConst.size()
                                    + ", but matrix size = " + matrix.getHeight());
        }
        this.matrix = matrix;
        this.columnConst = columnConst;
        this.field = field;
    }

    public List<T> solve() throws SolverSystemException {
        List<T> solution = new LinkedList<T>();
        T det = matrix.getDeterminant();
        if (field.isZero(det)) {
            throw new SolverSystemException("Determinant of system is zero!");
        }
        for (int i = 0; i < matrix.getHeight(); i++) {
            MatrixColumn<T> tempColumn = matrix.getColumn(i);

            matrix.replaceColumn(i, columnConst);
            T solVal = field.divide(matrix.getDeterminant(), det);
            matrix.replaceColumn(i, tempColumn);

            solution.add(solVal);
        }

        return solution;
    }
}
