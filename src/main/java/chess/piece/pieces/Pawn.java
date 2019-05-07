package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.List;

public class Pawn extends Piece {
    private boolean hasMoved;

    public Pawn (PieceType pieceType, Color pieceColor, Position positionOnBoard) {
        super(pieceType, pieceColor, positionOnBoard);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board) {
        return null;
    }

    @Override
    public boolean canReach (ChessBoard board, Position position) {
        return false;
    }
}
