package chess.piece;

import chess.piece.piecefitters.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PieceType {
    WHITE_PAWN ("p", Color.WHITE, new WhitePawnFitter()),
    WHITE_KNIGHT ("n", Color.WHITE, new WhiteKnightFitter()),
    WHITE_BISHOP ("b", Color.WHITE, new WhiteBishopFitter()),
    WHITE_ROOK ("r", Color.WHITE, new WhiteRookFitter()),
    WHITE_QUEEN ("q", Color.WHITE, new WhiteQueenFitter()),
    WHITE_KING ("k", Color.WHITE, new WhiteKingFitter()),

    BLACK_PAWN ("P", Color.BLACK, new BlackPawnFitter()),
    BLACK_KNIGHT ("N", Color.BLACK, new BlackKnightFitter()),
    BLACK_BISHOP ("B", Color.BLACK, new BlackBishopFitter()),
    BLACK_ROOK ("R", Color.BLACK, new BlackRookFitter()),
    BLACK_QUEEN ("Q", Color.BLACK, new BlackQueenFitter()),
    BLACK_KING ("K", Color.BLACK, new BlackKingFitter()),

    NO_PIECE (".", Color.NO_COLOR, (oldPosition, newPosition, currentBoard, turn) -> false);

    private static Map<String, PieceType> values = new HashMap<>();

    static {
        Arrays.asList(PieceType.values()).forEach(value -> values.put(value.getRepresentation(), value));
    }

    private String representation;
    private PieceFitter fitter;
    private Color pieceColor;

    public Color getPieceColor() {
        return pieceColor;
    }


    public PieceFitter getFitter() {
        return fitter;
    }

    PieceType (String representation, Color pieceColor, PieceFitter fitter) {
        this.representation = representation;
        this.pieceColor = pieceColor;
        this.fitter = fitter;
    }

    public String getRepresentation() {
        return representation;
    }


    public static PieceType fromHumanReadable (String string) {
        return Optional.ofNullable(values.get(string)).orElseThrow(() -> new RuntimeException("Unknown chess.piece type " + string + "!"));
    }

    @Override
    public String toString () {
        return representation;
    }

}
