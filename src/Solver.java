import org.hanuna.calcs.fields.DoubleField;
import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.matrix.Column;
import org.hanuna.calcs.matrix.ListMatrix;
import org.hanuna.calcs.matrix.Matrix;
import org.hanuna.calcs.matrix.MatrixFactory;
import org.hanuna.calcs.solver.SolverSystem;
import org.hanuna.calcs.solver.SolverSystemException;

/**
 * @author erokhins
 */
public class Solver {
    public static void main(String[] args) throws Exception{
        Field<Double> f = new DoubleField();
        final double[][] mm = {
                {1, 2,  1, 5},
                {0, 2, -1, 6},
                {0, 0,  2, 4},
        };

        Matrix<Double> mf = new Matrix<Double>() {
            @Override
            public Double get(int x, int y) {
                return mm[y][x];
            }

            @Override
            public int ySize() {
                return 3;
            }

            @Override
            public int xSize() {
                return 4;
            }
        };

        ListMatrix<Double> m = new ListMatrix<Double>(mf);
        Matrix<Double> mOfS = MatrixFactory.matrixCutColumnFunction(m, 3);
        try {
            Column<Double> ss = SolverSystem.solveLinearSystem(mOfS, MatrixFactory.matrixColumnFunction(m, 3), f);
            for (int i = 0; i < ss.size(); i++) {
                System.out.println(ss.get(i));
            }
        } catch (SolverSystemException e) {
            System.err.println(e.getMessage());
        }

        //Lexer l = new FlexLexer("- 44 + 44");
        //Polynom<Double> p = ParserSystem.parsePolynom(l);

        //System.out.println(ParserSystem.polynomToStr(p));

    }
}