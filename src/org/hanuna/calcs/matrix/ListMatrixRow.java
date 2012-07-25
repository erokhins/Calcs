package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public class ListMatrixRow<T> extends ListMatrixLine<T> implements MatrixRow<T> {

    public ListMatrixRow(MatrixLineFunction mlf) {
        super(mlf);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(listOfElement.get(i) + " ");
        }
        return s.toString();
    }
}
