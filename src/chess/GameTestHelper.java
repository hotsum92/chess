package chess;

import static chess.Board.*;

class GameTestHelper {
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

    static Board emptyBoard() {
        var pieces = new Piece[5][5];
        for(int row = 0; row < 5; ++row) {
            for(int col = 0; col < 5; ++col) {
                pieces[row][col] = empty;
            }
        }
        return new Board(pieces);
    }

    static Board oneFriendBoardWith(Piece piece) {
        var board = emptyBoard();
        piece.board = board;
        board.pieces[piece.position.row][piece.position.col] = piece;
        return board;
    }

    static Board oneFriendOneEnemyBoardWith(Piece friend, Piece enemy) {
        var board = oneFriendBoardWith(friend);
        enemy.board = board;
        board.pieces[enemy.position.row][enemy.position.col] = enemy;
        return board;
    }

}
