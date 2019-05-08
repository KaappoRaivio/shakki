import chess.board.ChessBoard;
import chess.move.Move;

public class Main {
    public static void main(String[] args) {
        var board = new ChessBoard("/home/kaappo/git/shakki/src/main/resources/boards/board1.dat");
        System.out.println(board);
        var move = Move.valueOf("e2e3", board);
        System.out.println(move);
        System.out.println(board.isMoveLegal(move));
    }
}
