package chess.board;

import chess.move.Move;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Undoer implements Serializable {
    private ChessBoard currentBoard;
    private Stack<Move> moves = new Stack<>();

    public Undoer (ChessBoard originalBoard) {
        this.currentBoard = originalBoard.deepCopy();
    }

    public void makeMove (Move move) {
        moves.push(move);

        currentBoard.makeMove(move);
    }
}
