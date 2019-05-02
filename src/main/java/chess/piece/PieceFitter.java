package chess.piece;

import chess.board.ChessBoard;
import misc.Position;

public interface PieceFitter {
    boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn);
}
