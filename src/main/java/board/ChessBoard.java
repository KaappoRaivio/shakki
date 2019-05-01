package board;

import misc.Position;
import piece.Color;
import piece.PieceType;

import java.io.*;

import java.nio.charset.StandardCharsets;


public class ChessBoard implements Serializable {
    private boolean hasWhiteKingMoved;
    private boolean hasBlackKingMoved;

    private PieceType[][] board;

    public ChessBoard(String path) {
        resetBoard(path);
    }

    public ChessBoard() {
        this("/home/kaappo/git/shakki/src/main/resources/boards/default_board.dat");
    }

    private void setSquare (Position position, PieceType square) {
        board[position.getY()][position.getX()] = square;
    }

    public PieceType getSquare (Position position) {
        return board[position.getY()][position.getX()];
    }

    private void resetBoard (String path) {
        board = new PieceType[8][8];

        String rawText;
        try {
            rawText = new String(new FileInputStream(new File(path)).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var lines = rawText.split("\n");

        if (lines.length != 8) {
            throw new RuntimeException("Invalid board format!");
        }

        for (int y = 0; y < board.length; y++) {
            String[] currentRow = lines[y].split(" ");

            for (int x = 0; x < board[y].length; x++) {
                setSquare(new Position(x, board.length - 1 - y), PieceType.fromHumanReadable(currentRow[x]));
            }
        }
    }

    public boolean isMoveLegal (Position oldPosition, Position newPosition, Color turn) {
        System.out.println(getSquare(oldPosition));
        return getSquare(oldPosition).getFitter().isMoveLegal(oldPosition, newPosition, this, turn);
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

    public static void main(String[] args) {
        var board = new ChessBoard();
        System.out.println(board.isMoveLegal(new Position(2, 0), new Position(4, 2), Color.WHITE));
        System.out.println(board);
    }
}
