package chess.piece;

import chess.board.ChessBoard;
import chess.move.Move;
import misc.Position;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

abstract public class Piece implements Serializable {
    private PieceType pieceType;
    protected Color color;

    public Piece (PieceType pieceType, Color pieceColor) {
        this.pieceType = pieceType;
        this.color = pieceColor;
    }

    public static Piece valueOf (String string) {
        return PieceCache.valueOf(string);
    }

    public PieceType getPieceType () {
        return pieceType;
    }

    public Color getColor () {
        return color;
    }

    abstract public List<Move> getAllMoves (ChessBoard board, Position currentPosition);
    abstract public boolean canReach (ChessBoard board, Position currentPosition, Position targetPosition);

    @Override
    public String toString () {
        return PieceCache.toString(this);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return getPieceType() == piece.getPieceType() &&
                getColor() == piece.getColor();
    }

    @Override
    public int hashCode () {
        return Objects.hash(getPieceType(), getColor());
    }
}
