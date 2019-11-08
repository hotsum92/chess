package chess;

import javax.swing.text.StyledEditorKit;
import java.awt.geom.Dimension2D;
import java.security.cert.TrustAnchor;

import static chess.Game.*;

class GameHelper {
    static Position center = new Position(2,2);

    static int forward(int steps) {
        return center.row - steps;
    }

    static int backward(int steps) {
        return center.row + steps;
    }

    static int right(int steps) {
        return center.col + steps;
    }

    static int left(int steps) {
        return center.col - steps;
    }

    static void DisplayTestConsole(Display display) {
        var game = new Game();
        var board = emptyBoard();
        board[center.row][center.col] = new Queen(new Position(center.row, center.col), white, game);
        game.board = board;
        game.start();
    }

    static Piece[][] emptyBoard() {
        var board = new Piece[8][8];
        for(int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                board[row][col] = empty;
            }
        }
        return board;
    }

    static Game emptyGame() {
        var game = new Game();
        game.board = emptyBoard();
        return game;
    }

    static Game onePawnBoardOn(Position position) {
        var game = emptyGame();
        game.board[position.row][position.col] = new Pawn(position, game.white, game);
        return game;
    }
}
