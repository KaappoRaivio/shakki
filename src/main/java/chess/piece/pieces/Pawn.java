package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pawn extends Piece {
    private boolean enPassantable = false;

    public Pawn (Color pieceColor) {
        super(PieceType.PAWN, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return Stream.of(
                new Move(currentPosition, currentPosition.offsetY(1), board),
                new Move(currentPosition, currentPosition.offsetY(2), board),
                new Move(currentPosition, currentPosition.offsetY(1).offsetX(1), board),
                new Move(currentPosition, currentPosition.offsetY(1).offsetX(-1), board)
        )
                .filter(move -> board.getSquare(move.getBasePosition()).canReach(board, move.getBasePosition(), move.getTargetPosition()))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
        try {

            int offsetY;

            if (color == Color.WHITE) {
                offsetY = targetPosition.subtract(currentPosition).getY();
            } else {
                offsetY = currentPosition.subtract(targetPosition).getY();
            }


            int offsetX = Math.abs(targetPosition.getX() - currentPosition.getX());

            if (offsetX != 0) {
                return offsetY == 1 && offsetX == 1 && board.getSquare(targetPosition).getColor() == color.invert();
            } else {
                if (offsetY == 1) {
                    return board.getSquare(targetPosition).getColor() == Color.NO_COLOR;
                } else if (offsetY == 2 && hasntMoved(currentPosition, color)) {
                    return board.getSquare(targetPosition).getColor() == Color.NO_COLOR
                            && board.getSquare(new Position(targetPosition.getX(), currentPosition.getY() + targetPosition.getY() / 2)).getColor() == Color.NO_COLOR;
                } else {
                    return false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private boolean hasntMoved (Position position, Color color) {
        switch (color) {
            case WHITE:
                return position.getY() == 1;
            case BLACK:
                return position.getY() == 6;
        }

        throw new RuntimeException("Color must be something!");
    }
}
