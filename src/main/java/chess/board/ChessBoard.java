package chess.board;

import chess.exceptions.CheckmateException;
import chess.exceptions.ChessException;
import chess.move.Move;
import chess.piece.Piece;
import chess.piece.pieces.Empty;
import misc.Pair;
import misc.Position;
import chess.piece.Color;
import misc.Saver;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;


public class ChessBoard implements Serializable {

    private boolean hasWhiteKingMoved = false;
    private boolean hasBlackKingMoved = false;

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
        return board[position.getY()][position.getX()];
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

    private boolean isKingInCheck (Color color) {
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

    private List<Move> getAllFittingMoves (Color color) {
        return getAllFittingMoves(color, false);
    }

    private List<Move> getAllFittingMoves (Color color, boolean noException) {
        if (!noException) {
            if (isCheckMate(color)) {
                throw new CheckmateException("Color " + color + " is in checkmate!");
            }
        }

        var moves = new LinkedList<Move>();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                moves.addAll(getAllFittingMoves(new Position(x, y), color));
            }
        }

        return moves;
    }

    private boolean isCheckMate (Color color) {
        return false;
    }

    private List<Move> getAllFittingMoves (Position oldPosition, Color color) {
        var moves = new LinkedList<Move>();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (isMoveLegal(oldPosition, new Position(x, y), color)) {
                    moves.add(new Move(oldPosition, new Position(x, y), deepCopy(), color));
                }
            }
        }

        return moves;
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
        setSquare(oldPosition, new Empty());
        setSquare(newPosition, getSquare(oldPosition));
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

    public static void main(String[] args) {
        var board = new ChessBoard("/home/kaappo/git/shakki/src/main/resources/boards/board1.dat");
//

        var a = board.getAllFittingMoves(Color.WHITE, true);
        System.out.println(a);
//        System.out.println(board.isMoveLegal(Move.valueOf("D8D7", board)));

//        a.forEach(item -> {
//            board.makeMove(item);
//            System.out.println(board);
//            board.undo();
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(board.isMoveLegal(new Position(3, 5), new Position(3, 4), Color.BLACK));
    }
}
