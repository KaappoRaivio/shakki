package chess.piece;

import chess.board.ChessBoard;
import chess.move.Move;
import misc.Position;

import java.util.List;

abstract public class Piece {
    private PieceType pieceType;
    private Color pieceColor;

    private Position positionOnBoard;

    public Piece (PieceType pieceType, Color pieceColor, Position positionOnBoard) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.positionOnBoard = positionOnBoard;
    }

    public PieceType getPieceType () {
        return pieceType;
    }

    public Color getPieceColor () {
        return pieceColor;
    }

    public Position getPositionOnBoard () {
        return positionOnBoard;
    }

    public void moveTo (Position newPosition) {
        positionOnBoard = newPosition;
    }

    abstract public List<Move> getAllMoves (ChessBoard board);
    abstract public boolean canReach (ChessBoard board, Position position);
}
