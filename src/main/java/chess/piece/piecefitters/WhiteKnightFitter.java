package chess.piece.piecefitters;

import chess.board.ChessBoard;
import chess.piece.PieceFitter;
import misc.Position;
import chess.piece.Color;
import chess.piece.PieceFitter;

public class WhiteKnightFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard) {
        int offsetX = Math.abs(oldPosition.getX() - newPosition.getX());
        int offsetY = Math.abs(oldPosition.getY() - newPosition.getY());

        boolean emptiness = GeneralCriteria.areSquaresEmpty(oldPosition, newPosition, currentBoard, Color.WHITE);

        boolean correctPath = (offsetX == 1 && offsetY == 2) || (offsetX == 2 && offsetY == 1);
        boolean rightTurn = currentBoard.getSquare(oldPosition).getPieceColor() == Color.WHITE;

        return emptiness && correctPath && rightTurn;
    }
}
