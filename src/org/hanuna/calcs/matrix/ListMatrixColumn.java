package org.hanuna.calcs.matrix;

/**
 * @author erokhins
 */
public class ListMatrixColumn<T> extends ListMatrixLine<T> implements MatrixColumn<T> {

    public ListMatrixColumn(MatrixLineFunction<T> mlf) {
        super(mlf);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(listOfElement.get(i) + "\n");
        }
        return s.toString();
    }
}
