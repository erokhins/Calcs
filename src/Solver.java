import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.fields.FieldRealNumber;
import org.hanuna.calcs.matrix.ListMatrix;
import org.hanuna.calcs.matrix.MatrixColumn;
import org.hanuna.calcs.matrix.MatrixFunction;
import org.hanuna.calcs.matrix.MatrixFunctionFactory;
import org.hanuna.calcs.solver.SystemSolver;
import org.hanuna.calcs.solver.SystemSolverException;

/**
 * @author erokhins
 */
public class Solver {
    public static void main(String[] args) {
        Field<Double> f = new FieldRealNumber();
        final int[][] mm = {{1,2,1,5},
                            {0,2,-1,6},
                            {0,0,2,4},
                            };

        MatrixFunction<Double> mf = new MatrixFunction<Double>() {
            @Override
            public Double get(int x, int y) {
                return (double) mm[y][x];
            }

            @Override
            public int height() {
                return 3;
            }

            @Override
            public int width() {
                return 4;
            }
        };

        ListMatrix<Double> m = new ListMatrix<Double>(mf);
        MatrixFunction<Double> mOfS = MatrixFunctionFactory.matrixCutColumnFunction(m, 3);
        try {
            MatrixColumn<Double> ss = SystemSolver.solveLinearSystem(mOfS, m.getColumn(3), f);
            System.out.print(ss);
        } catch (SystemSolverException e) {
            System.err.println(e.getMessage());
        }


    }
}