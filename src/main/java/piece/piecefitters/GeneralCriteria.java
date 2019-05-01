package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.PieceType;

public class GeneralCriteria {
    public static boolean areSquaresEmpty (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        return currentBoard.getSquare(oldPosition) == PieceType.NO_PIECE && currentBoard.getSquare(newPosition).getPieceColor() != turn;
    }
}
