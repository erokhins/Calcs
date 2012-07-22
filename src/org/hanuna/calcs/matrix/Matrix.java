package org.hanuna.calcs.matrix;

import org.hanuna.calcs.fields.Field;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erokhins
 */

public class Matrix<T> {
    private List<MatrixRow<T>> listOfRow;
    private Field<T> field;

    public Matrix(Field<T> field) {
        listOfRow = new LinkedList<MatrixRow<T>>();
        this.field = field;
    }

    public void addRow(MatrixRow<T> row) throws MatrixException {
        if (listOfRow.size() > 0) {
            int lastSizeRow = listOfRow.get(0).size();
            int newSizeRow = row.size();
            if (lastSizeRow != newSizeRow) {
                throw new MatrixException("lastSizeRow = " + lastSizeRow + ", but new size = " + newSizeRow);
            }
        }
        listOfRow.add(row);
    }

    public int getWidth() {
        if (listOfRow.size() > 0) {
            return listOfRow.get(0).size();
        } else {
            return 0;
        }
    }

    public int getHeight() {
        return listOfRow.size();
    }

    public T getDeterminant() throws MatrixException {
        int n = this.getHeight();
        int w = this.getWidth();
        if (n != w) {
            throw new MatrixException("determinant not supported for matrix " + w + "*" + n);
        }
        if (n == 0) {
            throw new MatrixException("determinant not supported for matrix 0*0");
        }
        if (n == 1) {
            return listOfRow.get(0).get(0);
        }

        T sum = field.getZero();
        T sign = field.getUnityElement();
        for (int currentRow = 0; currentRow < n; currentRow++) {
            Matrix<T> m = new Matrix<T>(field);
            int c = 0;
            T currentCel = null;
            for (Iterator<MatrixRow<T>> i = listOfRow.iterator(); i.hasNext(); ) {
                MatrixRow<T> row = i.next();
                if (c != currentRow) {
                    m.addRow(row.subRow(1));
                } else {
                    currentCel = row.get(0);
                }
                c++;
            }
            T t = field.mult(currentCel, m.getDeterminant());
            sum = field.add(sum, field.mult(sign, t) );
            sign = field.negative(sign);
        }
        return sum;
    }

    public MatrixColumn<T> getColumn(int columnNumber) {
        if (columnNumber >= this.getWidth() || columnNumber < 0) {
            throw new MatrixException("incorrect columnNumber in replaceColumn");
        }
        MatrixColumn<T> column = new MatrixColumn<T>();
        for (Iterator<MatrixRow<T>> i = listOfRow.iterator(); i.hasNext(); ) {
            MatrixRow<T> row = i.next();
            column.add(row.get(columnNumber));
        }
        return column;
    }

    public void replaceColumn(int columnNumber, MatrixColumn<T> column) throws MatrixException {
        if (columnNumber >= this.getWidth() || columnNumber < 0) {
            throw new MatrixException("incorrect columnNumber in replaceColumn");
        }
        if (column.size() != this.getHeight()) {
            throw new MatrixException("incorrect column.size in replaceColumn");
        }
        Iterator<T> columnI = column.iterator();
        for (Iterator<MatrixRow<T>> i = listOfRow.iterator(); i.hasNext(); ) {
            MatrixRow<T> row = i.next();
            T currentT = columnI.next();
            row.replace(columnNumber, currentT);
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Iterator<MatrixRow<T>> i = listOfRow.iterator(); i.hasNext(); ) {
            MatrixRow<T> row = i.next();
            s.append(row.toString());
            s.append("\n");
        }
        return s.toString();
    }
}
