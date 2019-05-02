package chess.piece.piecefitters;

import chess.board.ChessBoard;
import misc.Position;
import chess.piece.Color;
import chess.piece.PieceType;

public class GeneralCriteria {
    public static boolean areSquaresEmpty (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        return currentBoard.getSquare(oldPosition) == PieceType.NO_PIECE && currentBoard.getSquare(newPosition).getPieceColor() != turn;
    }
}
