package chess;

import java.util.*;

import static chess.GameTestHelper.*;

class Game {
    static final Player white = new Player("White");
    static final Player black = new Player("Black");
    static final Player[] players = new Player[] { white, black };

    Board board;
    Display display;

    Game() {
        board = new Board();
        display = new Display();
    }

    void start() {
        while (true) {
            display.show(board);

            while (true) {
                display.showNewLine();
                var command = display.askCommand();

                if("help".equals(command)) {
                    display.showHelp();
                    continue;
                }

                if("board".equals(command)) {
                    break;
                }

                if("resign".equals(command)) {
                    resign();
                    display.showResultOf(winner());
                    return;
                }

                if("moves".equals(command)) {
                    display.showAllPossibleMovementsOf(board);
                    continue;
                }

                if("test".equals(command)) {
                    StartTestConsole();
                    break;
                }

                if(command.length() == 2) {
                    var from = new UCI(command.substring(0, 2));
                    if(!from.isValid()) {
                        display.showInvalidInputError();
                        continue;
                    }
                    var piece = board.pieceAt(from.toPosition());
                    display.showAllPossibleMovementsOf(piece);
                    continue;
                }

                if(command.length() == 4) {
                    var from = new UCI(command.substring(0, 2));
                    var to   = new UCI(command.substring(2, 4));
                    if(!(from.isValid() && to.isValid())) {
                        display.showInvalidInputError();
                        continue;
                    }
                    if(!move(from.toPosition(), to.toPosition())) {
                        display.showInvalidInputError();
                        continue;
                    }
                    if(judgeWin()) {
                        display.showResultOf(winner());
                        return;
                    }
                    togglePlayer();
                    break;
                }

                display.showInvalidInputError();
            }
        }
    }

    void StartTestConsole() {
        var board = emptyBoard();

        var str = display.askPiece();

        var hash = new HashMap<String, Piece>();
        hash.put("1", new King(center, white, board));
        hash.put("2", new Rook(center, white, board));
        hash.put("3", new Bishop(center, white, board));
        hash.put("4", new Queen(center, white, board));
        hash.put("5", new Knight(center, white, board));
        hash.put("6", new Pawn(center, white, board));

        if(!hash.containsKey(str)) return;

        board.pieces[center.row][center.col] = hash.get(str);
        board.select(center);

        while (true) {
            display.show(board);

            while (true) {
                var command = display.askTestCommand();

                if("board".equals(command)) {
                    break;
                }

                if("moves".equals(command)) {
                    display.showAllPossibleMovementsOf(board);
                }

                if("quit".equals(command)) {
                    return;
                }

                if(command.length() == 2) {
                    var to = new UCI(command.substring(0, 2), 3);
                    if(!board.move(to.toPosition())) {
                        display.showInvalidInputError();
                    }
                    board.select(to.toPosition());
                    break;
                }

                display.showInvalidInputError();
            }
        }
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
