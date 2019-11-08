package chess;

import java.util.SplittableRandom;

import static chess.GameTestHelper.*;
import static chess.Board.*;

class Game {
    Display display;

    Game() {
        display = new Display();
    }

    void start() {
        var board = new Board();

        while (true) {
            display.show(board);
            var command = display.askCommand();

            if("help".equals(command)) {
                display.showHelp();
                continue;
            }

            if("board".equals(command)) {
                throw new UnsupportedOperationException();
            }

            if("resign".equals(command)) {
                throw new UnsupportedOperationException();
            }

            if("moves".equals(command)) {
                throw new UnsupportedOperationException();
            }

            if("test".equals(command)) {
                StartTestConsole();
                continue;
            }

            var from = new UCI(command.substring(0, 2));
            var to   = new UCI(command.substring(2, 4));
            if(from.isValid() && to.isValid()) {
                board.move(from.toPosition(), to.toPosition());
            }
        }
    }

    void StartTestConsole() {
        var board = emptyBoard();
        Piece target = null;

        var str = display.askPiece();

        if("1".equals(str)) {
            target = new King(center, white, board);
        }

        if("2".equals(str)) {
            target = new Rook(center, white, board);
        }

        if("3".equals(str)) {
            target = new Bishop(center, white, board);
        }

        if("4".equals(str)) {
            target = new Queen(center, white, board);
        }

        if("5".equals(str)) {
            target = new Knight(center, white, board);
        }

        if("6".equals(str)) {
            target = new Pawn(center, white, board);
        }

        if(target == null) return;

        board.pieces[center.row][center.col] = target;

        while (true) {
            display.show(board);
            var command = display.askTestCommand();

            if("board".equals(command)) {
                throw new UnsupportedOperationException();
            }

            if("moves".equals(command)) {
                throw new UnsupportedOperationException();
            }

            if("quit".equals(command)) {
                return;
            }

            var from = new UCI(command.substring(0, 2), 3);
            var to   = new UCI(command.substring(2, 4), 3);
            if(from.isValid() && to.isValid()) {
                var piece = board.pieceAt(from.toPosition());
                if(piece.canMoveTo(to.toPosition())) {
                    piece.move(to.toPosition());
                }
            }
        }
    }
}
