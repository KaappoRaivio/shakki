package chess.move;

import chess.board.ChessBoard;
import chess.piece.Color;
import misc.Position;

public class Move {
    private Position targetPosition;
    private Position basePosition;
    private ChessBoard board;
    private Color turn;

    public Move (Position targetPosition, Position basePosition, ChessBoard board, Color turn) {
        this.targetPosition = targetPosition;
        this.basePosition = basePosition;
        this.board = board;
        this.turn = turn;
    }

    @Override
    public String toString () {
        return targetPosition + "" + basePosition;
    }

    public Position getTargetPosition () {
        return targetPosition;
    }

    public Position getBasePosition () {
        return basePosition;
    }

    public ChessBoard getBoard () {
        return board;
    }

    public Color getTurn () {
        return turn;
    }
}
