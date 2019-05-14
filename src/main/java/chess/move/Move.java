package chess.move;

import chess.board.ChessBoard;
import chess.piece.Color;
import chess.piece.PieceType;
import misc.Position;

import static chess.piece.Color.BLACK;
import static chess.piece.PieceType.KING;

public class Move {
    private Position targetPosition;
    private Position basePosition;

    private PieceType startPiece;
    private PieceType endPiece;

    private Color turn;
    private CastlingType castlingType;

    public Move (Position basePosition, Position targetPosition, ChessBoard board) {
        this(basePosition, targetPosition, board, board.getSquare(basePosition).getColor());
    }

    public Move (Position basePosition, Position targetPosition, ChessBoard board, Color turn) {
        this.targetPosition = targetPosition;
        this.basePosition = basePosition;
        this.turn = turn;
        this.startPiece = board.getSquare(basePosition).getPieceType();
        this.endPiece = board.getSquare(targetPosition).getPieceType();

        handleCastling(board, basePosition, targetPosition);
    }

    private void handleCastling (ChessBoard board, Position basePosition, Position targetPosition) {
        var square = board.getSquare(basePosition);

        switch (square.getColor()) {
            case WHITE:
                if (square.getPieceType() == KING && basePosition.equals(4, 0)) {
                    if (targetPosition.equals(6, 0)) {
                        castlingType = CastlingType.SHORT;
                    } else if (targetPosition.equals(2, 0)) {
                        castlingType = CastlingType.LONG;
                    }
                }

                break;
            case BLACK:
                if (square.getPieceType() == KING && basePosition.equals(4, 7)) {
                    if (targetPosition.equals(6, 7)) {
                        castlingType = CastlingType.SHORT;
                    } else if (targetPosition.equals(2, 7)) {
                        castlingType = CastlingType.LONG;
                    }
                }

                break;
            default:
                castlingType = CastlingType.NO_CASTLING;
        }
    }


    public PieceType getStartPiece () {
        return startPiece;
    }

    public PieceType getEndPiece () {
        return endPiece;
    }

    @Override
    public String toString () {
        return basePosition.toString() + targetPosition.toString();
    }


    public Position getTargetPosition () {
        return targetPosition;
    }

    public Position getBasePosition () {
        return basePosition;
    }

    public Color getTurn () {
        return turn;
    }

    public static Move valueOf (String string, ChessBoard board) {
        string = string.toUpperCase();
        var oldPos = Position.fromChessPosition(string.substring(0, 2));
        return new Move(oldPos, Position.fromChessPosition(string.substring(2)), board, board.getSquare(oldPos).getColor());
    }
}
