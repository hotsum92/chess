package chess;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import static chess.Game.*;
import static chess.GameTestHelper.*;

class PawnTest {

    @ParameterizedTest
    @MethodSource("pawnCanMoveSource")
    void pawnCanMove(Position position, boolean isMoved, Board board) {

        var pawn = board.pieceAt(center);

        assertEquals(isMoved, pawn.canMoveTo(position));
    }

    static Stream<Arguments> pawnCanMoveSource() {

        return Stream.of(
                arguments(new Position(forward(1),  center.col), true,  oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(forward(1),  left(1)),    false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(forward(1),  right(1)),   false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(center.row,  left(1)),    false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(center.row,  left(1)),    false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(backward(1), center.col), false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(backward(1), right(1)),   false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(backward(1), left(1)),    false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(forward(2),  center.col), true,  oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(backward(2), center.col), false, oneFriendBoardWith(new Pawn(center, white, null))),
                arguments(new Position(forward(3),  center.col), false, oneFriendBoardWith(new Pawn(center, white, null))),

                arguments(new Position(forward(1), right(1)), true, oneFriendOneEnemyBoardWith(new Pawn(center, white, null), new Pawn(new Position(forward(1), right(1)), black, null))),
                arguments(new Position(forward(1), left(1)),  true, oneFriendOneEnemyBoardWith(new Pawn(center, white, null), new Pawn(new Position(forward(1), left(1)),  black, null)))
        );
    }
}
