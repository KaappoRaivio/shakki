package piece;

import board.ChessBoard;
import misc.Position;

public interface Piece {
    boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard);
}
