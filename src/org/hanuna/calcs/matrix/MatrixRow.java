package org.hanuna.calcs.matrix;

import java.util.List;

/**
 * @author erokhins
 */
public class MatrixRow<T> extends MatrixLine<T> {

    public MatrixRow() {
        super();
    }

    public MatrixRow(List<T> list) {
        super(list);
    }

    public MatrixRow<T> subRow(int start) {
        List<T> newList = list.subList(start, list.size());
        return new MatrixRow<T>(newList);
    }


}
