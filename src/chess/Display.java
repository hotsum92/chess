package chess;

import static chess.UCI.*;

class Display {

    Console console;

    Display() {
        console = new Console();
    }

    void show(Board board) {
        var str = "";

        var pieces = board.pieces;
        var firstIndex = rowIndex.length - pieces.length;
        for(int row = firstIndex; row < rowIndex.length; ++row) {
            for(int col = firstIndex; col < colIndex.length; ++col) {
                str += pieces[row - firstIndex][col - firstIndex];
            }
            str += " " + rowIndex[row] + "\n";
        }

        str += "\n";

        str += colIndex[0];
        for(int col = 1; col < pieces.length; ++col) {
            str += " " + colIndex[col];
        }

        str += "\n\n";
        str += board.currentPlayer.color + " to move\n";

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

    void showResultOf(Player winner) {
        console.show(winner + " won!\n");
    }

    void showInvalidInputError() {
        console.show("Invalid input, please try again\n");
    }

    void showNewLine() {
        console.show("\n");
    }

    void showAllPossibleMovementsOf(Piece piece) {
        console.show("Possible moves for " + new UCI(piece.position) + ":\n");
        var positions = piece.allPossibleMovements();
        show(positions);
    }

    void showAllPossibleMovementsOf(Board board) {
        console.show("Possible moves for :\n");
        var positions = board.allPossibleMovements();
        show(positions);
    }

    void show(Position[] positions) {
        if(positions.length < 1) {
            console.show("{ }\n");
            return;
        }
        var str = "{";
        for(int i = 0; i < positions.length - 1; ++i) {
            str += " " + new UCI(positions[i]) + ",";
        }
        str += " " + new UCI(positions[positions.length - 1]);
        str += " }\n";
        console.show(str);
    }

    String askCommand() {
        console.show("Enter UCI (type 'help' for help):");
        return console.read();
    }

    String askTestCommand() {
        console.show("Enter UCI (test mode):");
        return console.read();
    }

    String askPiece() {
        console.show("Select piece(1:king 2:rook 3:bishop 4:queen 5:knight 6:pawn):");
        return console.read();
    }
}
