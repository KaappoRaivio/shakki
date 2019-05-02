package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.PieceFitter;

public class BlackQueenFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal(Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        return new BlackRookFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn)
                || new BlackBishopFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn);
    }
}
