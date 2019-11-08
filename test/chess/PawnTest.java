package chess;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import static chess.GameTestHelper.*;

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
                arguments(new Position(forward(1),  center.col), true,  oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(forward(1),  left(1)),    false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(forward(1),  right(1)),   false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(center.row,  left(1)),    false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(center.row,  left(1)),    false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(backward(1), center.col), false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(backward(1), right(1)),   false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(backward(1), left(1)),    false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(forward(2),  center.col), true,  oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(backward(2), center.col), false, oneFriendBoardWith(new Pawn(center, Game.white, null))),
                arguments(new Position(forward(3),  center.col), false, oneFriendBoardWith(new Pawn(center, Game.white, null))),

                arguments(new Position(forward(1), right(1)), true, oneFriendOneEnemyBoardWith(new Pawn(center, Game.white, null), new Pawn(new Position(forward(1), right(1)), Game.black, null))),
                arguments(new Position(forward(1), left(1)),  true, oneFriendOneEnemyBoardWith(new Pawn(center, Game.white, null), new Pawn(new Position(forward(1), left(1)),  Game.black, null)))
        );
    }
}
