package org.hanuna.calcs.matrix;

import java.util.ArrayList;
import java.util.List;

import static org.hanuna.calcs.matrix.MatrixUtils.checkGetRequest;

/**
 * @author erokhins
 */
public class ListColumn<T> extends Column<T> {

    private final List<T> listOfElements;

    public ListColumn(Column<T> column) {
        listOfElements = new ArrayList<T>(column.size());
        for (int i = 0; i < column.size(); i++) {
            listOfElements.add(column.get(i));
        }
    }


    @Override
    public T get(int n) {
        checkGetRequest(this, n);
        return listOfElements.get(n);
    }

    @Override
    public int size() {
        return listOfElements.size();
    }
}
