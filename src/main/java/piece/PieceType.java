package piece;

import piece.piecefitters.BlackPawnFitter;
import piece.piecefitters.WhiteBishopFitter;
import piece.piecefitters.WhitePawnFitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PieceType {
    WHITE_PAWN ("p", Color.WHITE, new WhitePawnFitter()), WHITE_KNIGHT ("n", Color.WHITE), WHITE_BISHOP ("b", Color.WHITE, new WhiteBishopFitter()),
    WHITE_ROOK ("r", Color.WHITE), WHITE_QUEEN ("q", Color.WHITE), WHITE_KING ("k", Color.WHITE),

    BLACK_PAWN ("P", Color.BLACK, new BlackPawnFitter()), BLACK_KNIGHT ("N", Color.BLACK), BLACK_BISHOP ("B", Color.BLACK),
    BLACK_ROOK ("R", Color.BLACK), BLACK_QUEEN ("Q", Color.BLACK), BLACK_KING ("K", Color.BLACK),

    NO_PIECE (".", Color.NO_COLOR);

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

    PieceType (String representation, Color pieceColor) {
        this(representation, pieceColor, null);
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
        return Optional.ofNullable(values.get(string)).orElseThrow(() -> new RuntimeException("Unknown piece type " + string + "!"));
    }

    @Override
    public String toString () {
        return representation;
    }

}
