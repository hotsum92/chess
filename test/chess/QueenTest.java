package chess;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.stream.Stream;

import static chess.Game.*;
import static chess.GameTestHelper.*;

public class QueenTest {

    @ParameterizedTest
    @MethodSource("queenCanMoveSource")
    void queenCanMove(Position position, boolean isMoved, Board board) {

        var queen = board.pieceAt(center);

        assertEquals(isMoved, queen.canMoveTo(position));
    }

    static Stream < Arguments > queenCanMoveSource() {
        return Stream.of(
                arguments(new Position(forward(2), center.col), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(backward(2), center.col), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(right(2), center.col), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(left(2), center.col), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(forward(1), right(1)), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(forward(1), left(1)), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(backward(1), right(1)), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(backward(1), left(1)), true, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(forward(2), right(1)), false, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(forward(2), left(1)), false, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(backward(2), right(1)), false, oneFriendBoardWith(new Queen(center, white, null))),
                arguments(new Position(backward(2), left(1)), false, oneFriendBoardWith(new Queen(center, white, null))),

                arguments(new Position(forward(1), right(1)), true, oneFriendOneEnemyBoardWith(new Queen(center, white, null), new Pawn(new Position(forward(1), right(1)), black, null))),
                arguments(new Position(forward(1), right(1)), false, oneFriendOneEnemyBoardWith(new Queen(center, white, null), new Pawn(new Position(forward(1), right(1)), white, null))),
                arguments(new Position(forward(2), right(2)), false, oneFriendOneEnemyBoardWith(new Queen(center, white, null), new Pawn(new Position(forward(1), right(1)), black, null))),
                arguments(new Position(forward(2), right(2)), false, oneFriendOneEnemyBoardWith(new Queen(center, white, null), new Pawn(new Position(forward(1), right(1)), white, null)))
        );
    }
}