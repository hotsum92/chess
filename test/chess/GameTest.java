package chess;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void start() {
        MockConsole mock = new MockConsole();
        Display display = new Display(mock);
        Game game = new Game(display);

        mock.inputList = new ArrayList<>();
        mock.inputList.add("a2a3");
        mock.inputList.add("resign");

        game.start();

        var originalPositionPiece = game.board.pieceAt(new Position(6, 0)); // Should be "Empty"
        var newPositionPiece = game.board.pieceAt(new Position(5, 0)); // Should be "Pawn"

        assertTrue(originalPositionPiece instanceof Empty);
        assertTrue(newPositionPiece instanceof Pawn);
        assertEquals("resign", mock.inputList.get(1));
    }
}
