package org.hanuna.calcs.matrix;

import java.util.List;

/**
 * @author erokhins
 */
public class MatrixColumn<T> extends MatrixLine<T> {

    public MatrixColumn() {
       super();
    }

    public MatrixColumn(List<T> list) {
        super(list);
    }

}
