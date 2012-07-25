package org.hanuna.calcs.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */
public class ListMatrixLine<T> implements MatrixLineFunction<T> {
    protected final List<T> listOfElement;
    protected final int size;


    public ListMatrixLine(MatrixLineFunction<T> mlf) {
        size = mlf.size();
        listOfElement = new ArrayList<T>(size);
        for (int i = 0; i < size; i++) {
            listOfElement.add(mlf.get(i));
        }
    }

    @Override
    public T get(int n) {
        return listOfElement.get(n);
    }

    @Override
    public int size() {
        return size;
    }
}
