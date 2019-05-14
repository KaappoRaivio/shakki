package chess.board;

import chess.exceptions.CheckmateException;
import chess.exceptions.ChessException;
import chess.move.Move;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.pieces.Empty;
import misc.Pair;
import misc.Position;
import chess.piece.Color;
import misc.Saver;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class ChessBoard implements Serializable {

    private boolean hasWhiteKingMoved = false;
    private boolean hasBlackKingMoved = false;

    private Position whiteKingPos;
    private Position blackKingPos;

    private Piece[][] board;

    private Undoer<Piece[][]> undoer = new Undoer<>();

    public ChessBoard(String path) {
        resetBoard(path);
    }

    public ChessBoard() {
        this("/home/kaappo/git/shakki/src/main/resources/boards/default_board.dat");
    }

    private void setSquare (Position position, Piece square) {
        board[position.getY()][position.getX()] = square;
    }

    public Piece getSquare (Position position) {
        return Optional.of(board[position.getY()][position.getX()]).orElseThrow();
    }

    private void resetBoard (String path) {
        board = new Piece[8][8];
        
        String rawText;
        try {
            rawText = new String(new FileInputStream(new File(path)).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var lines = rawText.split("\n");

        if (lines.length != 8) {
            throw new ChessException("Invalid board format!");
        }

        for (int y = 0; y < board.length; y++) {
            String[] currentRow = lines[y].split(" ");

            for (int x = 0; x < board[y].length; x++) {
                setSquare(new Position(x, board.length - 1 - y), Piece.valueOf(currentRow[x]));
            }
        }
    }


    public boolean isMoveLegal (Position oldPosition, Position newPosition, Color turn) {
        if (getSquare(oldPosition).canReach(this, oldPosition, newPosition) && turn == getSquare(oldPosition).getColor()) {
            makeDummyMove(oldPosition, newPosition);
            try {
                boolean check = isKingInCheck(turn);
                undo();
                return !check;
            } catch (ChessException e) {
                undo();
                return false;
            }
        } else {
            return false;
        }
    }

    private Position getKingPos (Color color) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                var position = new Position(x, y);
                if (getSquare(position).getColor() == color && getSquare(position).getPieceType() == PieceType.KING) {
                    return position;
                }
            }
        }

        throw new ChessException(color + " king was not found on board " + toString());
    }

    public boolean isKingInCheck (Color color) {
        Position kingPos = getKingPos(color);

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                var currentPos = new Position(x, y);
                var square = getSquare(currentPos);

                if (square.canReach(this, currentPos, kingPos) && square.getColor() == color.invert()) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isMoveLegal (Move move) {
        return isMoveLegal(move.getBasePosition(), move.getTargetPosition(), move.getTurn());
    }



    @Override
    public String toString() {
        var builder = new StringBuilder();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                builder.append(getSquare(new Position(x, board.length - 1 - y))).append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }

    public List<Move> getAllFittingMoves (Color color) {
        List<Move> moveList = new LinkedList<>();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                var pos = new Position(x, y);
                moveList.addAll(getSquare(pos).getAllMoves(this, pos));
            }
        }

        return moveList;
    }

    public boolean hasKingMoved (Color color) {
        switch (color) {
            case WHITE:
                return hasWhiteKingMoved;
            case BLACK:
                return hasBlackKingMoved;
            default:
                throw new ChessException("Color must be something!");
        }
    }

    public void makeMove (Move move) {
        makeMove(move.getBasePosition(), move.getTargetPosition(), move.getTurn());
    }

    private void makeMove (Position oldPosition, Position newPosition, Color turn) {
        if (isMoveLegal(oldPosition, newPosition, turn)) {

            makeDummyMove(oldPosition, newPosition);
        } else {
            throw new RuntimeException("Move" + new Move(oldPosition, newPosition, this, turn) + " is not legal!");
        }
    }

    private void makeDummyMove (Position oldPosition, Position newPosition) {
        saveState();
        var square = getSquare(oldPosition);
        if (square.getPieceType() == PieceType.KING) {
            switch (square.getColor()) {
                case WHITE:
                    whiteKingPos = newPosition;
                    break;
                case BLACK:
                    blackKingPos = newPosition;
                    break;
                case NO_COLOR:
                    throw new RuntimeException("King must be some color!");
            }
        }
        setSquare(oldPosition, new Empty());
        setSquare(newPosition, square);
    }

    public ChessBoard deepCopy () {
        return Saver.deepCopy(this);
    }

    public void undo () {
        board = undoer.undo();
    }

    private void saveState () {
        Piece[][] oldBoard = new Piece[8][8];

        for (int y = 0; y < 8; y++) {
            System.arraycopy(board[y], 0, oldBoard[y], 0, 8);
        }

        undoer.addState(oldBoard);
    }

    public Pair<Position, Piece> findNextPiece (Position start, int offsetX, int offsetY, Position end) {
        start = start.offsetX(offsetX).offsetY(offsetY);
        Piece square = getSquare(start);

        if (start.equals(end)) {
            return new Pair<>(start, square);
        }

        if (square.getColor() == Color.NO_COLOR) {
            try {
                return findNextPiece(start, offsetX, offsetY, end);
            } catch (ArrayIndexOutOfBoundsException e) {
                return new Pair<>(start, square);
            }
        } else {
            return new Pair<>(start, square);
        }
    }
}
