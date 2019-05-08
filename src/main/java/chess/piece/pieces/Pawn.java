package chess.piece.pieces;

import chess.board.ChessBoard;
import chess.move.Move;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import misc.Position;

import java.util.List;

public class Pawn extends Piece {
    private boolean enPassantable = false;

    public Pawn (Color pieceColor) {
        super(PieceType.PAWN, pieceColor);
    }

    @Override
    public List<Move> getAllMoves (ChessBoard board, Position currentPosition) {
        return null;
    }

    @Override
    public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition) {
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
