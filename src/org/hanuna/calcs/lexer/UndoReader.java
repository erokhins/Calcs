package org.hanuna.calcs.lexer;

import java.io.IOException;
import java.io.Reader;

/**
 * @author erokhins
 */
public class UndoReader {
    private int prevChar;
    private int currentChar;
    private boolean undoStatus = false;
    private Reader r;

    public UndoReader(Reader r) {
        this.r = r;
    }

    public int read() throws IOException {
        if (undoStatus) {
            undoStatus = false;
            return prevChar;
        }
        prevChar = currentChar;
        currentChar = r.read();
        return currentChar;
    }

    public void undo() {
        undoStatus = true;
    }
}
