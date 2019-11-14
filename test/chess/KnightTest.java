package chess;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import static chess.Game.*;
import static chess.GameTestHelper.*;

class KnightTest {

    @ParameterizedTest
    @MethodSource("knightCanMoveSource")
    void KnightCanMove(Position position, boolean isMoved, Board board) {

        var knight = board.pieceAt(center);

        assertEquals(isMoved, knight.canMoveTo(position));
    }

    static Stream < Arguments > knightCanMoveSource() {

        return Stream.of(
                arguments(new Position(forward(2), right(1)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(1), right(2)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(2), right(1)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(1), right(2)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(2), left(1)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(1), left(2)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(2), left(1)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(1), left(2)), true, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(2), right(2)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(1), right(1)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(2), right(3)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(3), right(1)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(1), left(1)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(forward(2), left(3)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(1), left(1)), false, oneFriendBoardWith(new Knight(center, white, null))),
                arguments(new Position(backward(3), left(2)), false, oneFriendBoardWith(new Knight(center, white, null))),

                arguments(new Position(forward(2), right(1)), true, oneFriendOneEnemyBoardWith(new Knight(center, white, null), new Pawn(new Position(forward(2), right(1)), black, null))),
                arguments(new Position(forward(2), left(1)), false, oneFriendOneEnemyBoardWith(new Knight(center, white, null), new Pawn(new Position(forward(2), left(1)), white, null)))
        );
    }
}