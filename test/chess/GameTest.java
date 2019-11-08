package chess;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.*;

import static chess.GameTestHelper.*;

class GameTest {

    @Test
    void displayBoard() {
        var game = new Game();
        System.out.println(game);
    }

    @Test
    void whitePlayerStartsFirst() {
        var game = new Game();
        assertEquals(Game.white,game.currentPlayer);
    }

    @ParameterizedTest
    @MethodSource("pieceExistsBetweenSource")
    void pieceExistsBetween(Position position, boolean expected, Game game) {
        var actual = game.pieceExistsBetween(center, position);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> pieceExistsBetweenSource() {

        var fullPieceBoard = fullPawnBoard();

        return Stream.of(
                arguments(new Position(1, 1), false, fullPieceBoard),
                arguments(new Position(1, 2), false, fullPieceBoard),
                arguments(new Position(1, 3), false, fullPieceBoard),
                arguments(new Position(2, 1), false, fullPieceBoard),
                arguments(new Position(2, 3), false, fullPieceBoard),
                arguments(new Position(3, 1), false, fullPieceBoard),
                arguments(new Position(3, 2), false, fullPieceBoard),
                arguments(new Position(3, 3), false, fullPieceBoard),

                arguments(new Position(0, 2), true, oneFriendBoardWith(new Pawn(new Position(1, 2), Game.white, null))),
                arguments(new Position(0, 4), true, oneFriendBoardWith(new Pawn(new Position(1, 3), Game.white, null))),
                arguments(new Position(2, 0), true, oneFriendBoardWith(new Pawn(new Position(2, 1), Game.white, null))),
                arguments(new Position(2, 4), true, oneFriendBoardWith(new Pawn(new Position(2, 3), Game.white, null))),
                arguments(new Position(4, 0), true, oneFriendBoardWith(new Pawn(new Position(3, 1), Game.white, null))),
                arguments(new Position(4, 2), true, oneFriendBoardWith(new Pawn(new Position(3, 2), Game.white, null))),
                arguments(new Position(4, 4), true, oneFriendBoardWith(new Pawn(new Position(3, 3), Game.white, null))),

                arguments(new Position(0, 0), false, oneEmptyBoard(new Position(1, 1))),
                arguments(new Position(0, 2), false, oneEmptyBoard(new Position(1, 2))),
                arguments(new Position(0, 4), false, oneEmptyBoard(new Position(1, 3))),
                arguments(new Position(2, 0), false, oneEmptyBoard(new Position(2, 1))),
                arguments(new Position(2, 4), false, oneEmptyBoard(new Position(2, 3))),
                arguments(new Position(4, 0), false, oneEmptyBoard(new Position(3, 1))),
                arguments(new Position(4, 2), false, oneEmptyBoard(new Position(3, 2))),
                arguments(new Position(4, 4), false, oneEmptyBoard(new Position(3, 3)))
        );
    }

    static Game oneEmptyBoard(Position position) {
        var game = fullPawnBoard();
        game.board[position.row][position.col] = Game.empty;
        return game;
    }
    static Game fullPawnBoard() {
        var game = new Game();
        game.board = new Piece[][]{
                {
                        new Pawn(new Position(0, 0), Game.white, game),
                        new Pawn(new Position(0, 1), Game.white, game),
                        new Pawn(new Position(0, 2), Game.white, game),
                        new Pawn(new Position(0, 3), Game.white, game),
                        new Pawn(new Position(0, 4), Game.white, game)
                },
                {
                        new Pawn(new Position(1, 0), Game.white, game),
                        new Pawn(new Position(1, 1), Game.white, game),
                        new Pawn(new Position(1, 2), Game.white, game),
                        new Pawn(new Position(1, 3), Game.white, game),
                        new Pawn(new Position(1, 4), Game.white, game)
                },
                {
                        new Pawn(new Position(2, 0), Game.white, game),
                        new Pawn(new Position(2, 1), Game.white, game),
                        new Pawn(new Position(2, 2), Game.white, game),
                        new Pawn(new Position(2, 3), Game.white, game),
                        new Pawn(new Position(2, 4), Game.white, game)
                },
                {
                        new Pawn(new Position(3, 0), Game.white, game),
                        new Pawn(new Position(3, 1), Game.white, game),
                        new Pawn(new Position(3, 2), Game.white, game),
                        new Pawn(new Position(3, 3), Game.white, game),
                        new Pawn(new Position(3, 4), Game.white, game)
                },
                {
                        new Pawn(new Position(4, 0), Game.white, game),
                        new Pawn(new Position(4, 1), Game.white, game),
                        new Pawn(new Position(4, 2), Game.white, game),
                        new Pawn(new Position(4, 3), Game.white, game),
                        new Pawn(new Position(4, 4), Game.white, game)
                }
        };

        return game;
    }
}
