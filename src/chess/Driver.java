package chess;

import java.util.HashMap;

import static chess.Game.*;
import static chess.GameTestHelper.*;

public class Driver {

    static Display display =  new Display();

     public static void main(String[] args) {
         var game = new Game();

         while (true) {
             display.show(game.board);

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
                     game.resign();
                     display.showResultOf(game.winner());
                     return;
                 }

                 if("moves".equals(command)) {
                     display.showAllPossibleMovementsOf(game.board);
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
                     var piece = game.board.pieceAt(from.toPosition());
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
                     if(!game.move(from.toPosition(), to.toPosition())) {
                         display.showInvalidInputError();
                         continue;
                     }
                     if(game.judgeWin()) {
                         display.showResultOf(game.winner());
                         return;
                     }
                     game.togglePlayer();
                     break;
                 }

                 display.showInvalidInputError();
             }
         }
    }

    static void StartTestConsole() {
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
}
