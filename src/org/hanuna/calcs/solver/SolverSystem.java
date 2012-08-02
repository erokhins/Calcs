package org.hanuna.calcs.solver;

import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.matrix.*;

/**
 * @author erokhins
 */
public class SolverSystem {

    public static <T> MatrixColumn<T> solveLinearSystem(
            final MatrixFunction<T> matrixOfSystem,
            final MatrixColumn<T> constColumn,
            final Field<T> field
    ) throws SolverSystemException {

        if (matrixOfSystem.height() != matrixOfSystem.width()) {
            throw new IllegalArgumentException("count vars = " + matrixOfSystem.width()
                    + ", but count equations = " + matrixOfSystem.height());
        }
        if (matrixOfSystem.height() != constColumn.size()) {
            throw new IllegalArgumentException("columnConst = " + constColumn.size()
                    + ", but matrix size = " + matrixOfSystem.height());
        }
        final MatrixUtils<T> mu = new MatrixUtils<T>(field);
        T d = mu.determinant(matrixOfSystem);
        if (field.isZero(d)) {
            throw new SolverSystemException("determinant matrix of system is zero!");
        }

        final T inverseD = field.inverse(d);

        MatrixLineFunction<T> mlf = new MatrixLineFunction<T>() {
            @Override
            public T get(int n) {
                MatrixFunction<T> tmf = MatrixFunctionFactory.matrixReplaceColumnFunction(matrixOfSystem, constColumn, n);
                T td = mu.determinant(tmf);
                td = field.mult(inverseD, td);
                return td;
            }

            @Override
            public int size() {
                return matrixOfSystem.height();
            }
        };
        return new ListMatrixColumn<T>(mlf);
    }

}
