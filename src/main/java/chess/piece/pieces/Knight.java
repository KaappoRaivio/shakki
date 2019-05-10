package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Pair;
import misc.Position;

import java.util.List;

public class Knight extends Piece {
    public Knight (Color pieceColor) {
        super(PieceType.KNIGHT, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return null;
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        int offsetX = targetPosition.getX() - currentPosition.getX();
        int offsetY = targetPosition.getY() - currentPosition.getY();

        boolean correctPath = Math.abs(offsetX) == 2 && Math.abs(offsetY) == 1 || Math.abs(offsetX) == 1 && Math.abs(offsetY) == 2;
        if (!correctPath) {
            return false;
        }

        Piece nextPiece = board.getSquare(targetPosition);
        return nextPiece.getColor() != color;
    }
}
