package org.hanuna.calcs.matrix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erokhins
 */
public class MatrixLine<T> {
    protected List<T> list;

    public MatrixLine() {
        list = new LinkedList<T>();
    }

    public MatrixLine(List<T> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public T get(int index) {
        return list.get(index);
    }

    public void add(T t) {
        list.add(t);
    }

    public void replace(int index, T t) {
        list.remove(index);
        list.add(index, t);
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Iterator<T> i = list.iterator(); i.hasNext(); ) {
            s.append(i.next().toString() + " ");
        }
        return s.toString();
    }
}
