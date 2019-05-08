package chess.piece;

public enum Color {
    WHITE, BLACK, NO_COLOR;

    public Color invert () {
        switch (this) {
            case WHITE:
                return BLACK;
            case BLACK:
                return WHITE;
            default:
                return NO_COLOR;
        }
    }
}
