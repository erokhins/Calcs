import org.hanuna.calcs.fields.DoubleField;
import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.lexer.FlexLexer;
import org.hanuna.calcs.lexer.Lexer;
import org.hanuna.calcs.matrix.MatrixFunction;
import org.hanuna.calcs.parser.ParserPolynom;
import org.hanuna.calcs.polynoms.Polynom;

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
              /*
        ListMatrix<Double> m = new ListMatrix<Double>(mf);
        MatrixFunction<Double> mOfS = MatrixFunctionFactory.matrixCutColumnFunction(m, 3);
        try {
            MatrixColumn<Double> ss = SolverSystem.solveLinearSystem(mOfS, m.getColumn(3), f);
            System.out.print(ss);
        } catch (SolverSystemException e) {
            System.err.println(e.getMessage());
        }
            */
        Lexer l = new FlexLexer("- 44 + 44");
        Polynom<Double> p = ParserPolynom.parsePolynom(l);

        System.out.println(ParserPolynom.polynomToStr(p));

    }
}