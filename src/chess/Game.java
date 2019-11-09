package chess;

class Game {
    static final Player white = new Player("White");
    static final Player black = new Player("Black");
    static final Player[] players = new Player[] { white, black };

    Board board;

    Game() {
        board = new Board();
    }

    boolean move(Position from, Position to) {
        if(!board.currentPlayerHasPieceAt(from)) return false;
        if(board.move(from, to)) return true;
        return false;
    }

    void togglePlayer() {
        board.currentPlayer = players[0].equals(board.currentPlayer) ? players[1] : players[0];
    }

    boolean judgeWin() {
        var count = 0;
        for(int row = 0; row < board.pieces.length; ++row) {
            for(int col = 0; col < board.pieces.length; ++col) {
                if(board.pieces[row][col] instanceof King) count += 1;
            }
        }

        return count != 2;
    }

    void resign() {
        togglePlayer();
    }

    Player winner() {
        return board.currentPlayer;
    }
}
