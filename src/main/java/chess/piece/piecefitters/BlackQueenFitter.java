package chess.piece.piecefitters;

import chess.board.ChessBoard;
import chess.piece.PieceFitter;
import misc.Position;
import chess.piece.Color;
import chess.piece.PieceFitter;

public class BlackQueenFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal(Position oldPosition, Position newPosition, ChessBoard currentBoard) {
        return new BlackRookFitter().isMoveLegal(oldPosition, newPosition, currentBoard)
                || new BlackBishopFitter().isMoveLegal(oldPosition, newPosition, currentBoard);
    }
}
