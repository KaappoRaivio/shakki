package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.List;

public class Queen extends Piece {
    public Queen (Color pieceColor) {
        super(PieceType.QUEEN, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return null;
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        return new Rook(color).canReach(board, currentPosition, targetPosition) || new Bishop(color).canReach(board, currentPosition, targetPosition);
    }
}
