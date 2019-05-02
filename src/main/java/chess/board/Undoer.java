package chess.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Undoer<T> implements Serializable {
    private Stack<T> states = new Stack<>();

    public void addState (T state) {
        states.add(state);
    }

    public T undo () {
        return states.pop();
    }

    public T undo (int level) {
        if (level < 2) {
            return undo();
        }
        else {
            states.pop();
            return undo(level - 1);
        }
    }
}
