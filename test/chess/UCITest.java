package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UCITest {

    @Test
    void isValid() {
    }

    @Test
    void toPosition() {
        var uci = new UCI("f2");
        assertEquals(new Position(6, 5), uci.toPosition());
    }
}
