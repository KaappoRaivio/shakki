package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Pair;
import misc.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    public Bishop (Color pieceColor) {
        super(PieceType.BISHOP, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        List<Move> moves = new LinkedList<>();


        var newPosition = currentPosition;
        while (newPosition.getX() < 8 && newPosition.getY() < 8) {
            moves.add(new Move(currentPosition, newPosition, board));
            newPosition = newPosition.offsetX(1).offsetY(1);
        }

        newPosition = currentPosition;
        while (newPosition.getX() >= 0 && newPosition.getY() < 8) {
            moves.add(new Move(currentPosition, newPosition, board));
            newPosition = newPosition.offsetX(-1).offsetY(1);
        }

        newPosition = currentPosition;
        while (newPosition.getX() >= 0 && newPosition.getY() >= 0) {
            moves.add(new Move(currentPosition, newPosition, board));
            newPosition = newPosition.offsetX(-1).offsetY(-1);
        }

        newPosition = currentPosition;
        while (newPosition.getX() < 8 && newPosition.getY() >= 0) {
            moves.add(new Move(currentPosition, newPosition, board));
            newPosition = newPosition.offsetX(1).offsetY(-1);

        }

        return moves
                .stream()
                .filter(move -> board.getSquare(move.getBasePosition()).canReach(board, move.getBasePosition(), move.getTargetPosition()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        int offsetX = Integer.compare(targetPosition.getX(), currentPosition.getX());
        int offsetY = Integer.compare(targetPosition.getY(), currentPosition.getY());

        boolean correctDiagonal = Math.abs(targetPosition.getX() - currentPosition.getX()) == Math.abs(targetPosition.getY() - currentPosition.getY())
                || Math.abs(targetPosition.getX() + currentPosition.getX()) == Math.abs(targetPosition.getY() + currentPosition.getY());
        if (!correctDiagonal) {
            return false;
        }

        Pair<Position, Piece> nextPiece = board.findNextPiece(currentPosition, offsetX, offsetY, targetPosition);
        return nextPiece.getV().getColor() != color && nextPiece.getK().equals(targetPosition);
    }
}
