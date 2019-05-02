package piece.piecefitters;

import board.ChessBoard;
import misc.Position;
import piece.Color;
import piece.PieceFitter;
import piece.PieceType;

public class BlackRookFitter implements PieceFitter {
    @Override
    public boolean isMoveLegal (Position oldPosition, Position newPosition, ChessBoard currentBoard, Color turn) {
        boolean rightTurn = currentBoard.getSquare(oldPosition).getPieceColor() == Color.WHITE && turn == Color.WHITE;
        boolean correctFile =  oldPosition.getX() == newPosition.getX()
                || oldPosition.getY() == newPosition.getY();

        if (!correctFile || !rightTurn) {
            return false;
        }

        int offsetX = Integer.compare(newPosition.getX() - oldPosition.getX(), 0);
        int offsetY = Integer.compare(newPosition.getX() - oldPosition.getX(), 0);

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
