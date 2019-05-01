package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.PieceFitter;
import piece.PieceType;

public class WhiteBishopFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        boolean rightTurn = currentBoard.getSquare(oldPosition).getPieceColor() == Color.WHITE && turn == Color.WHITE;
        boolean correctDiagonal =  oldPosition.getX() + oldPosition.getY() == newPosition.getX() + newPosition.getY()
                                || oldPosition.getX() - oldPosition.getY() == newPosition.getX() - newPosition.getY();

        if (!correctDiagonal || !rightTurn) {
            return false;
        }

        int offsetX = newPosition.getX() - oldPosition.getX() > 0 ? -1 : 1;
        int offsetY = newPosition.getY() - oldPosition.getY() > 0 ? -1 : 1;

        return isMoveLegalRecursive(oldPosition, newPosition, currentBoard, turn, false, offsetX, offsetY);
    }

    private boolean isMoveLegalRecursive (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn, boolean recursive, int offsetX, int offsetY) {
        if (oldPosition.equals(newPosition) && recursive) {
            return true;
        }

        if (currentBoard.getSquare(newPosition) != PieceType.NO_PIECE) {
            return false;
        }

        return isMoveLegalRecursive(oldPosition, new Position(newPosition.getX() + offsetX, newPosition.getY() + offsetY), currentBoard, turn, true, offsetX, offsetY);


    }
}
