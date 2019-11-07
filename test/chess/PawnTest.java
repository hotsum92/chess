package chess;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import static chess.GameHelper.*;

class PawnTest {

    @ParameterizedTest
    @MethodSource("pawnMovesSource")
    void pawnMoves(Position position, boolean isMoved, Game game) {

        var pawn = game.pieceAt(center);
        var newPosition = isMoved ? position : center;

        assertEquals(isMoved, pawn.move(position));
        assertEquals(pawn, game.pieceAt(newPosition));
    }

    static Stream<Arguments> pawnMovesSource() {

        return Stream.of(
                arguments(new Position(forward(1),  center.col), true,  onePawnBoardOn(center)),
                arguments(new Position(forward(1),  left(1)),    false, onePawnBoardOn(center)),
                arguments(new Position(forward(1),  right(1)),   false, onePawnBoardOn(center)),
                arguments(new Position(center.row,  left(1)),    false, onePawnBoardOn(center)),
                arguments(new Position(center.row,  left(1)),    false, onePawnBoardOn(center)),
                arguments(new Position(backward(1), center.col), false, onePawnBoardOn(center)),
                arguments(new Position(backward(1), right(1)),   false, onePawnBoardOn(center)),
                arguments(new Position(backward(1), left(1)),    false, onePawnBoardOn(center)),
                arguments(new Position(forward(2),  center.col), true,  onePawnBoardOn(center)),
                arguments(new Position(backward(2), center.col), false, onePawnBoardOn(center)),
                arguments(new Position(forward(3),  center.col), false, onePawnBoardOn(center)),

                arguments(new Position(forward(1), right(1)), true, oneFriendOneEnemyBoardOn(center, new Position(forward(1), right(1)))),
                arguments(new Position(forward(1), left(1)),  true, oneFriendOneEnemyBoardOn(center, new Position(forward(1), left(1))))
        );
    }

    static Game oneFriendOneEnemyBoardOn(Position friend, Position enemy) {
        var game = onePawnBoardOn(friend);
        game.board[enemy.row][enemy.col] = new Pawn(enemy, game.black, game);
        return game;
    }
}
