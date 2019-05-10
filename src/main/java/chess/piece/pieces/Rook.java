package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Pair;
import misc.Position;

import java.util.List;

public class Rook extends Piece {
    public Rook (Color pieceColor) {
        super(PieceType.ROOK, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return null;
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        int offsetX = Integer.compare(targetPosition.getX(), currentPosition.getX());
        int offsetY = Integer.compare(targetPosition.getY(), currentPosition.getY());

        boolean correctFile = Boolean.logicalXor(Math.abs(offsetX) == 1, Math.abs(offsetY) == 1);
        if (!correctFile) {
            return false;
        }

        Pair<Position, Piece> nextPiece = board.findNextPiece(currentPosition, offsetX, offsetY, targetPosition);
        return nextPiece.getV().getColor() != color && nextPiece.getK().equals(targetPosition);
    }
}
