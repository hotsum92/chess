package chess;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.*;

import static chess.Game.*;
import static chess.GameTestHelper.*;

class BoardTest {

    @Test
    void displayBoard() {
        var board = new Board();
        System.out.println(board);
    }

    @Test
    void whitePlayerStartsFirst() {
        var board = new Board();
        assertEquals(white , board.currentPlayer);
    }

    @ParameterizedTest
    @MethodSource("pieceExistsBetweenSource")
    void pieceExistsBetween(Position position, boolean expected, Board board) {
        var actual = board.pieceExistsBetween(center, position);

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

                arguments(new Position(0, 2), true, oneFriendBoardWith(new Pawn(new Position(1, 2), white, null))),
                arguments(new Position(0, 4), true, oneFriendBoardWith(new Pawn(new Position(1, 3), white, null))),
                arguments(new Position(2, 0), true, oneFriendBoardWith(new Pawn(new Position(2, 1), white, null))),
                arguments(new Position(2, 4), true, oneFriendBoardWith(new Pawn(new Position(2, 3), white, null))),
                arguments(new Position(4, 0), true, oneFriendBoardWith(new Pawn(new Position(3, 1), white, null))),
                arguments(new Position(4, 2), true, oneFriendBoardWith(new Pawn(new Position(3, 2), white, null))),
                arguments(new Position(4, 4), true, oneFriendBoardWith(new Pawn(new Position(3, 3), white, null))),

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

    static Board oneEmptyBoard(Position position) {
        var board = fullPawnBoard();
        board.remove(position);
        return board;
    }
    static Board fullPawnBoard() {
        var board = new Board();
        board.pieces = new Piece[][]{
                {
                        new Pawn(new Position(0, 0), white, board),
                        new Pawn(new Position(0, 1), white, board),
                        new Pawn(new Position(0, 2), white, board),
                        new Pawn(new Position(0, 3), white, board),
                        new Pawn(new Position(0, 4), white, board)
                },
                {
                        new Pawn(new Position(1, 0), white, board),
                        new Pawn(new Position(1, 1), white, board),
                        new Pawn(new Position(1, 2), white, board),
                        new Pawn(new Position(1, 3), white, board),
                        new Pawn(new Position(1, 4), white, board)
                },
                {
                        new Pawn(new Position(2, 0), white, board),
                        new Pawn(new Position(2, 1), white, board),
                        new Pawn(new Position(2, 2), white, board),
                        new Pawn(new Position(2, 3), white, board),
                        new Pawn(new Position(2, 4), white, board)
                },
                {
                        new Pawn(new Position(3, 0), white, board),
                        new Pawn(new Position(3, 1), white, board),
                        new Pawn(new Position(3, 2), white, board),
                        new Pawn(new Position(3, 3), white, board),
                        new Pawn(new Position(3, 4), white, board)
                },
                {
                        new Pawn(new Position(4, 0), white, board),
                        new Pawn(new Position(4, 1), white, board),
                        new Pawn(new Position(4, 2), white, board),
                        new Pawn(new Position(4, 3), white, board),
                        new Pawn(new Position(4, 4), white, board)
                }
        };

        return board;
    }
}
