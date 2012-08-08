package org.hanuna.calcs.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erokhins
 */

public class ListMatrix<T> implements Matrix<T> {
    private final int width;
    private final int height;

    /**
     * M[x, y] = listOfElement.get(y * xSize + x)
     */
    private final List<T> listOfElement;

    public ListMatrix(Matrix<T> mf) {
        width = mf.xSize();
        height = mf.ySize();
        listOfElement = new ArrayList<T>(width * height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                listOfElement.add(mf.get(x, y));
            }
        }
    }

    @Override
    public int xSize() {
        return width;
    }

    @Override
    public int ySize() {
        return height;
    }

    @Override
    public T get(int x, int y) {
        return listOfElement.get(y * width + x);
    }






    public String toString() {
        StringBuilder s = new StringBuilder(width * height * 2);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                s.append(get(x, y)).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
