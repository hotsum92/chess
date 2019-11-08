package chess;

import static chess.Board.*;

class GameTestHelper {
    static Position center = new Position(2,2);

    static void DisplayTestConsole() {
        var game = new Board();
        var board = emptyBoard();
        board[center.row][center.col] = new Queen(new Position(center.row, center.col), white, game);
        game.board = board;
        game.start();
    }

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

    static Piece[][] emptyBoard() {
        var board = new Piece[5][5];
        for(int row = 0; row < 5; ++row) {
            for(int col = 0; col < 5; ++col) {
                board[row][col] = empty;
            }
        }
        return board;
    }

    static Board oneFriendBoardWith(Piece piece) {
        var game = new Board();
        piece.board = game;
        game.board = emptyBoard();
        game.board[piece.position.row][piece.position.col] = piece;
        return game;
    }

    static Board oneFriendOneEnemyBoardWith(Piece friend, Piece enemy) {
        var game = oneFriendBoardWith(friend);
        game.board[enemy.position.row][enemy.position.col] = enemy;
        return game;
    }

}
