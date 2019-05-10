package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    public King (Color pieceColor) {
        super(PieceType.KING, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return Stream.of(
                new Move(currentPosition, currentPosition.offsetX(1).offsetY(1), board),
                new Move(currentPosition, currentPosition.offsetX(1).offsetY(0), board),
                new Move(currentPosition, currentPosition.offsetX(1).offsetY(-1), board),
                new Move(currentPosition, currentPosition.offsetX(0).offsetY(-1), board),
                new Move(currentPosition, currentPosition.offsetX(-1).offsetY(-1), board),
                new Move(currentPosition, currentPosition.offsetX(-1).offsetY(0), board),
                new Move(currentPosition, currentPosition.offsetX(-1).offsetY(1), board),
                new Move(currentPosition, currentPosition.offsetX(0).offsetY(1), board)
        ).filter(move -> board.getSquare(move.getBasePosition()).canReach(board, move.getBasePosition(), move.getTargetPosition())).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        int offsetX = targetPosition.getX() - currentPosition.getX();
        int offsetY = targetPosition.getY() - currentPosition.getY();

        boolean correctPath = Math.abs(offsetX) == 1 || Math.abs(offsetY) == 1;
        if (!correctPath) {
            return false;
        }

        Piece square;

        try {
            square = board.getSquare(targetPosition);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return square.getColor() != color;
    }
}
