package piece;

import piece.piecefitters.BlackPawnFitter;
import piece.piecefitters.WhitePawnFitter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PieceType {
    WHITE_PAWN ("p", new WhitePawnFitter()), WHITE_KNIGHT ("n"), WHITE_BISHOP ("b"),
    WHITE_ROOK ("r"), WHITE_QUEEN ("q"), WHITE_KING ("k"),

    BLACK_PAWN ("P", new BlackPawnFitter()), BLACK_KNIGHT ("N"), BLACK_BISHOP ("B"),
    BLACK_ROOK ("R"), BLACK_QUEEN ("Q"), BLACK_KING ("K"),

    NO_PIECE (".");

    private static Map<String, PieceType> values = new HashMap<>();

    static {
        Arrays.asList(PieceType.values()).forEach(value -> values.put(value.getRepresentation(), value));
    }

    private String representation;

    public Piece getFitter() {
        return fitter;
    }

    private Piece fitter;

    PieceType(String representation) {
        this(representation, null);
    }

    PieceType(String representation, Piece fitter) {
        this.representation = representation;
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
