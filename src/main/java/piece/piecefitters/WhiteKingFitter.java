package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.PieceFitter;

public class WhiteKingFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal(Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        return new WhiteQueenFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn)
                && Math.abs(oldPosition.getX() - newPosition.getX()) < 2
                && Math.abs(oldPosition.getY() - newPosition.getY()) < 2;
    }
}
