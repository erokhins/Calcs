package org.hanuna.calcs.solver;

import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.matrix.*;

import static org.hanuna.calcs.matrix.MatrixUtils.*;

/**
 * @author erokhins
 */
public class SolverSystem {

    public static <T> Column<T> solveLinearSystem(
            final Matrix<T> matrixOfSystem,
            final Column<T> constColumn,
            final Field<T> field
    ) throws SolverSystemException {

        if (matrixOfSystem.ySize() != matrixOfSystem.xSize()) {
            throw new IllegalArgumentException("count vars = " + matrixOfSystem.xSize()
                    + ", but count equations = " + matrixOfSystem.ySize());
        }
        if (matrixOfSystem.ySize() != constColumn.size()) {
            throw new IllegalArgumentException("columnConst = " + constColumn.size()
                    + ", but matrix size = " + matrixOfSystem.ySize());
        }
        final MatrixUtils<T> mu = new MatrixUtils<T>(field);
        T d = mu.determinant(matrixOfSystem);
        if (field.isZero(d)) {
            throw new SolverSystemException("determinant matrix of system is zero!");
        }

        final T inverseD = field.inverse(d);

        Column<T> mlf = new Column<T>() {
            @Override
            public T get(int n) {
                checkGetRequest(this, n);
                Matrix<T> tmf = MatrixFactory.matrixReplaceColumnFunction(matrixOfSystem, constColumn, n);
                T td = mu.determinant(tmf);
                td = field.mult(inverseD, td);
                return td;
            }

            @Override
            public int size() {
                return matrixOfSystem.ySize();
            }
        };
        return new ListColumn<T>(mlf);
    }

}
