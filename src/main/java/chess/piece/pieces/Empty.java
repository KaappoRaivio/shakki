package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Empty extends Piece {
    public Empty () {
        super(PieceType.EMPTY, Color.NO_COLOR);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return null;
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        return false;
    }
}
