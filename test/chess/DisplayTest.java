package chess;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {

    @Test
    void showHelp() {
        var expected = "* type 'help' for help\n"
                + "* type 'board' to see the board again\n"
                + "* type 'resign' to resign\n"
                + "* type 'moves' to list all possible moves\n"
                + "* type a square (e.g. b1, e2) to list possible moves for that square\n"
                + "* type UCI (e.g. b1c3, e78q) to make a move\n";
        MockConsole mock = new MockConsole();
        Display display = new Display(mock);
        display.showHelp();
        assertEquals(expected, mock.result);
    }

    @Test
    void askCommand(){
        var expected = "Enter UCI (type 'help' for help):";
        MockConsole mock = new MockConsole();
        Display display = new Display(mock);
        mock.input = "test";
        String command = display.askCommand();
        assertEquals(expected, mock.result);
        assertEquals("test", command);
    }
}