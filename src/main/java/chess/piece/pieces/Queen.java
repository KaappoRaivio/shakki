package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.List;

public class Queen extends Piece {
    private Rook rook;
    private Bishop bishop;

    public Queen (Color pieceColor) {
        super(PieceType.QUEEN, pieceColor);
        rook = new Rook(color);
        bishop = new Bishop(color);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        var list = rook.getAllMoves(board, currentPosition);
        list.addAll(bishop.getAllMoves(board, currentPosition));

        return list;
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        return rook.canReach(board, currentPosition, targetPosition) || bishop.canReach(board, currentPosition, targetPosition);
    }
}
