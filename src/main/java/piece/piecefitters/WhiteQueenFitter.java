package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.PieceFitter;

public class WhiteQueenFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal(Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        return new WhiteRookFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn)
            || new WhiteBishopFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn);
    }
}
