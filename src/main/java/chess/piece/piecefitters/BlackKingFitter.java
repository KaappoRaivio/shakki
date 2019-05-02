package chess.piece.piecefitters;

import chess.board.ChessBoard;
import chess.piece.Color;
import chess.piece.PieceFitter;
import misc.Position;
import chess.piece.Color;
import chess.piece.PieceFitter;

public class BlackKingFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal(Position oldPosition, Position newPosition, ChessBoard currentBoard) {
        return new WhiteQueenFitter().isMoveLegal(oldPosition, newPosition, currentBoard)
                && Math.abs(oldPosition.getX() - newPosition.getX()) < 2
                && Math.abs(oldPosition.getY() - newPosition.getY()) < 2;
    }

}
