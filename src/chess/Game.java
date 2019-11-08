package chess;

import static chess.GameTestHelper.DisplayTestConsole;

class Game {
    Board board;
    Display display;

    Game(Board board) {
        this.board = board;
        display = new Display(board);
    }

    Game() {
        this(new Board());
    }

    void start() {

        while (true) {
            display.showBoard();
            var command = display.askCommand();

            if("help".equals(command)) {
                display.showHelp();
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
                DisplayTestConsole();
            }

            var from = new UCI(command.substring(0, 2));
            var to   = new UCI(command.substring(2, 4));
            if(from.isValid() && to.isValid()) {
                board.move(from.toPosition(), to.toPosition());
            }
        }
    }
}
