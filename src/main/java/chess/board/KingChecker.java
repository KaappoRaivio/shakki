package chess.board;

import chess.piece.Color;
import chess.piece.PieceType;
import misc.Position;

import java.io.Serializable;
import java.lang.reflect.Array;

class KingChecker implements Serializable {
    private ChessBoard board;

    KingChecker (ChessBoard board) {
        this.board = board;
    }

    boolean isKingInCheck (Color color) {
        var kingPos = findKing(color);

        PieceType rookType;
        PieceType bishopType;
        PieceType knightType;
        PieceType queenType;
        PieceType kingType;

        if (color == Color.WHITE) {
            rookType = PieceType.BLACK_ROOK;
            bishopType = PieceType.BLACK_BISHOP;
            knightType = PieceType.BLACK_KNIGHT;
            queenType = PieceType.BLACK_QUEEN;
            kingType = PieceType.BLACK_KING;
        } else {
            rookType = PieceType.WHITE_ROOK;
            bishopType = PieceType.WHITE_BISHOP;
            knightType = PieceType.WHITE_KNIGHT;
            queenType = PieceType.WHITE_QUEEN;
            kingType = PieceType.WHITE_KING;
        }
//        System.out.println(findNextPiece(kingPos, 0, 1, 5));

        boolean rookSafety
                = findNextPiece(kingPos, 1, 0, 8) != rookType
                && findNextPiece(kingPos, -1, 0, 8) != rookType
                && findNextPiece(kingPos, 0, 1, 8) != rookType
                && findNextPiece(kingPos, 0, -1, 8) != rookType

                && findNextPiece(kingPos, 1, 0, 8) != queenType
                && findNextPiece(kingPos, -1, 0, 8) != queenType
                && findNextPiece(kingPos, 0, 1, 8) != queenType
                && findNextPiece(kingPos, 0, -1, 8) != queenType;

        boolean bishopSafety
                = findNextPiece(kingPos, 1, 1, 8) != bishopType
                && findNextPiece(kingPos, -1, 1, 8) != bishopType
                && findNextPiece(kingPos, -1, -1, 8) != bishopType
                && findNextPiece(kingPos, 1, -1, 8) != bishopType

                && findNextPiece(kingPos, 1, 1, 8) != queenType
                && findNextPiece(kingPos, -1, 1, 8) != queenType
                && findNextPiece(kingPos, -1, -1, 8) != queenType
                && findNextPiece(kingPos, 1, -1, 8) != queenType;

        boolean knightSafety
                = findNextPiece(kingPos, 2, 1, 1) != knightType
                && findNextPiece(kingPos, -2, 1, 1) != knightType
                && findNextPiece(kingPos, -2, -1, 1) != knightType
                && findNextPiece(kingPos, 2, -1, 1) != knightType
                && findNextPiece(kingPos, 1, 2, 1) != knightType
                && findNextPiece(kingPos, -1, 2, 1) != knightType
                && findNextPiece(kingPos, -1, -2, 1) != knightType
                && findNextPiece(kingPos, 1, -2, 1) != knightType;

        boolean kingSafety
                = findNextPiece(kingPos, 1, 0, 1) != kingType
                && findNextPiece(kingPos, 1, 1, 1) != kingType
                && findNextPiece(kingPos, 0, 1, 1) != kingType
                && findNextPiece(kingPos, -1, 1, 1) != kingType
                && findNextPiece(kingPos, -1, 0, 1) != kingType
                && findNextPiece(kingPos, -1, -1, 1) != kingType
                && findNextPiece(kingPos, 0, -1, 1) != kingType
                && findNextPiece(kingPos, 1, -1, 1) != kingType;

        return !(rookSafety && bishopSafety && knightSafety && kingSafety);

    }

    private PieceType findNextPiece (Position basePosition, int offsetX, int offsetY, int maximumDistance) {
        return findNextPiece(basePosition, offsetX, offsetY, maximumDistance, 1);
    }

    private PieceType findNextPiece (Position basePosition, int offsetX, int offsetY, int maximumRecursion, int timesRecursed) {
        var newPosition = basePosition.offsetX(offsetX).offsetY(offsetY);
        if (timesRecursed > maximumRecursion) {
            return board.getSquare(newPosition);
        }

        PieceType square;

        try {
            square = board.getSquare(newPosition);
        } catch (ArrayIndexOutOfBoundsException e) {
            return PieceType.NO_PIECE;
        }

        if (square != PieceType.NO_PIECE) {
            return square;
        } else {
            try {
                return findNextPiece(newPosition, offsetX, offsetY, maximumRecursion, timesRecursed + 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                return square;
            }
        }
    }


    private Position findKing (Color color) {
        for (int y = 0; y < board.board.length; y++) {
            for (int x = 0; x < board.board[y].length; x++) {
                var square = board.getSquare(new Position(x, y));
                if (square.getPieceColor() == color && (square == PieceType.BLACK_KING ||square == PieceType.WHITE_KING)) {
                    return new Position(x, y);
                }
            }
        }

        throw new RuntimeException("King is captured! " + toString());
    }
}
