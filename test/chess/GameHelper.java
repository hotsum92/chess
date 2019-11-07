package chess;

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


    static Game emptyBoard() {
        var game = new Game();
        game.board = new Piece[][]{
                {
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty
                },
                {
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty
                },
                {
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty
                },
                {
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty
                },
                {
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty,
                        game.empty
                }
        };
        return game;
    }

    static Game onePawnBoardOn(Position position) {
        var game = emptyBoard();
        game.board[position.row][position.col] = new Pawn(position, game.white, game);
        return game;
    }
}
