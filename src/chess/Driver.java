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
             var command = display.askCommand();

             if("help".equals(command)) {
                 display.showHelp();
                 continue;
             }

             if("board".equals(command)) {
                 continue;
             }

             if("resign".equals(command)) {
                 game.resign();
                 display.showResult(game.winner());
                 return;
             }

             if("moves".equals(command)) {
                 throw new UnsupportedOperationException();
             }

             if("test".equals(command)) {
                 StartTestConsole();
                 continue;
             }

             if(command.length() == 2) {
                 var from = new UCI(command.substring(0, 2));
             }

             if(command.length() == 4) {
                 var from = new UCI(command.substring(0, 2));
                 var to   = new UCI(command.substring(2, 4));
                 if(!(from.isValid() && to.isValid())) continue;
                 if(!game.move(from.toPosition(), to.toPosition())) continue;
                 if(game.judgeWin()) {
                     display.showResult(game.winner());
                     return;
                 }
                 game.togglePlayer();
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

        if(hash.containsKey(str)) return;

        board.pieces[center.row][center.col] = hash.get(str);

        while (true) {
            display.show(board);
            var command = display.askTestCommand();

            if("board".equals(command)) {
                continue;
            }

            if("moves".equals(command)) {
                throw new UnsupportedOperationException();
            }

            if("quit".equals(command)) {
                return;
            }


            if(command.length() == 2) {
                var from = new UCI(command.substring(0, 2), 3);
            }

            if(command.length() == 4) {
                var from = new UCI(command.substring(0, 2), 3);
                var to   = new UCI(command.substring(2, 4), 3);
                if(from.isValid() && to.isValid()) {
                    board.move(from.toPosition(), to.toPosition());
                }
            }
        }
    }
}
