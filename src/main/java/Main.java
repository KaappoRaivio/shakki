import chess.board.ChessBoard;
import chess.move.Move;
import misc.Position;

public class Main {
    public static void main(String[] args) {
        var board = new ChessBoard("/home/kaappo/git/shakki/src/main/resources/boards/board1.dat");
        System.out.println(board);
        var move = Move.valueOf("c2d3", board);
        System.out.println(move);
        System.out.println(board.isMoveLegal(move));
        System.out.println(board.getSquare(Position.fromChessPosition("e2")).getAllMoves(board, Position.fromChessPosition("e2")));
    }
}
