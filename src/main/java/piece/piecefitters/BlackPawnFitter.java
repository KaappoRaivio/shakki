package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.Piece;
import piece.PieceType;

public class BlackPawnFitter implements Piece {
    @Override
    public boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        if (currentBoard.getSquare(oldPosition).getPieceColor() != Color.BLACK || turn != Color.BLACK) {
            return false;
        }

        boolean isDoubleMove = false;
        if (newPosition.getY() - oldPosition.getY() == -2) {
            isDoubleMove = true;
        }

        if (isDoubleMove) {
            return oldPosition.getY() == 6
                    && currentBoard.getSquare(newPosition) == PieceType.NO_PIECE
                    && currentBoard.getSquare(new Position(oldPosition.getX(), oldPosition.getY() - 1)) == PieceType.NO_PIECE
                    && oldPosition.getX() == newPosition.getX();
        } else {
            return newPosition.getY() - oldPosition.getY() == -1
                    && currentBoard.getSquare(newPosition) == PieceType.NO_PIECE
                    && oldPosition.getX() == newPosition.getX();
        }
    }
}
