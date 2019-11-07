package chess;

class Display {

    Console console;
    Game game;

    Display(Game game) {
        console = new Console();
        this.game = game;
    }

    void showBoard() {
        String[] rowNumber = { "8", "7", "6", "5", "4", "3", "2", "1" };
        String[] colNumber = { "a", "b", "c", "d", "e", "f", "g", "h" };
        var str = "";

        var array = game.board;

        for(int row = 0; row < array.length; ++row) {
            for(int col = 0; col < array[0].length; ++col) {
                str += array[row][col];
            }
            str += " " + rowNumber[row] + "\n";
        }

        str += "\n";

        str += colNumber[0];
        for(int col = 1; col < array[0].length; ++col) {
            str += " " + colNumber[col];
        }

        str += "\n\n";
        str += game.currentPlayer.color + " to move\n";

        console.show(str);
    }

    void showHelp() {
        var str = "* type 'help' for help\n"
                + "* type 'board' to see the board again\n"
                + "* type 'resign' to resign\n"
                + "* type 'moves' to list all possible moves\n"
                + "* type a square (e.g. b1, e2) to list possible moves for that square\n"
                + "* type UCI (e.g. b1c3, e78q) to make a move\n";
        console.show(str);
    }

    String askCommand() {
        console.show("Enter UCI (type 'help' for help):");
        return console.read();
    }
}
