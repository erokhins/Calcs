import org.hanuna.calcs.fields.Field;
import org.hanuna.calcs.fields.FieldRealNumber;
import org.hanuna.calcs.solver.SolverSystem;
import org.hanuna.calcs.matrix.Matrix;
import org.hanuna.calcs.matrix.MatrixColumn;
import org.hanuna.calcs.matrix.MatrixRow;

import java.util.Iterator;
import java.util.List;

/**
 * @author erokhins
 */
public class Solver {
    public static void main(String[] args) {
        Field f = new FieldRealNumber();
        Matrix<Double> m = new Matrix<Double>(f);
        MatrixRow<Double> l1 = new MatrixRow<Double>();
        MatrixRow<Double> l2 = new MatrixRow<Double>();
        MatrixRow<Double> l3 = new MatrixRow<Double>();
        MatrixColumn<Double> l4 = new MatrixColumn<Double>();

        l1.add(1.);
        l1.add(1.);
        l1.add(0.);

        l2.add(0.);
        l2.add(1.);
        l2.add(1.);

        l3.add(0.);
        l3.add(0.);
        l3.add(1.);

        l4.add(2.);
        l4.add(3.);
        l4.add(1.);

        m.addRow(l1);
        m.addRow(l2);
        m.addRow(l3);

        SolverSystem<Double> s = new SolverSystem<Double>(m, l4, f);
        List<Double> l = s.solve();

        for (Iterator<Double> i = l.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }
        System.out.println(m.toString());
        System.out.println(m.getDeterminant());

    }
}