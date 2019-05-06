package chess.piece.piecefitters;

import chess.board.ChessBoard;
import chess.piece.PieceFitter;
import misc.Position;
import chess.piece.Color;
import chess.piece.PieceFitter;
import chess.piece.PieceType;

public class WhiteRookFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard) {
        boolean rightTurn = currentBoard.getSquare(oldPosition).getPieceColor() == Color.WHITE;
        boolean correctFile =  oldPosition.getX() == newPosition.getX()
                                || oldPosition.getY() == newPosition.getY();

        if (!correctFile || !rightTurn) {
            return false;
        }

        int offsetX = Integer.compare(newPosition.getX() - oldPosition.getX(), 0);
        int offsetY = Integer.compare(newPosition.getY() - oldPosition.getY(), 0);

        return isMoveLegalRecursive(oldPosition, newPosition, currentBoard, false, offsetX, offsetY);
    }

    private boolean isMoveLegalRecursive (Position oldPosition, Position newPosition, ChessBoard currentBoard, boolean recursive, int offsetX, int offsetY) {
        if (oldPosition.equals(newPosition) && recursive) {
            return true;
        }
        if (oldPosition.equals(newPosition)) {
            return false;
        }
        try {
            if (currentBoard.getSquare(newPosition) != PieceType.NO_PIECE) {
                return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }

        return isMoveLegalRecursive(oldPosition, new Position(newPosition.getX() + offsetX, newPosition.getY() + offsetY), currentBoard, true, offsetX, offsetY);


    }

}
