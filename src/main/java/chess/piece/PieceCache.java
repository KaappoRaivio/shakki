package chess.piece;

import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.pieces.*;
import misc.Position;
import misc.Saver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class PieceCache {
    private static final Map<String, Piece> pieces = Map.ofEntries(
            Map.entry("k", new King(Color.WHITE)),
            Map.entry("K", new King(Color.BLACK)),

            Map.entry("q", new Queen(Color.WHITE)),
            Map.entry("Q", new Queen(Color.BLACK)),

            Map.entry("b", new Bishop(Color.WHITE)),
            Map.entry("B", new Bishop(Color.BLACK)),

            Map.entry("n", new Knight(Color.WHITE)),
            Map.entry("N", new Knight(Color.BLACK)),

            Map.entry("r", new Rook(Color.WHITE)),
            Map.entry("R", new Rook(Color.BLACK)),

            Map.entry("p", new Pawn(Color.WHITE)),
            Map.entry("P", new Pawn(Color.BLACK)),

            Map.entry(".", new Empty())

    );
    private static final Map<Piece, String> strings = pieces
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));


    public static Piece valueOf (String string) {
        return Saver.deepCopy(Optional.of(pieces.get(string)).orElseThrow(() -> new RuntimeException("Unknown string " + string + "!")));
    }

    public static String toString (Piece piece) {
        return Optional.of(strings.get(piece)).orElseThrow(() -> new RuntimeException("Unknown piece!"));
    }

}
