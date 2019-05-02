package chess.piece.piecefitters;

import chess.board.ChessBoard;
import chess.piece.PieceFitter;
import misc.Position;
import chess.piece.Color;
import chess.piece.PieceFitter;

public class WhiteQueenFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal(Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        return new WhiteRookFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn)
            || new WhiteBishopFitter().isMoveLegal(oldPosition, newPosition, currentBoard, turn);
    }
}
