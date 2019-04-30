package piece;

import misc.Pair;
import misc.Position;

abstract public class Piece {
    PieceType pieceType;
    boolean onBoard;

    Pair<Boolean, Position> stateOnBoard;
}
