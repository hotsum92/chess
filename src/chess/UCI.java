package chess;

import java.lang.reflect.Array;
import java.util.Arrays;

class UCI {

    static String[] rowIndex = { "8", "7", "6", "5", "4", "3", "2", "1" };
    static String[] colIndex = { "a", "b", "c", "d", "e", "f", "g", "h" };

    String string;
    int offset;

    UCI(String string) {
        this.string = string;
    }
    UCI(String string, int offset) { this(string); this.offset = offset;}

    boolean isValid() {
        return true;
    }

    Position toPosition() {
        var col = string.substring(0, 1);
        var row = string.substring(1, 2);

        var position = new Position(0, 0);
        for(int i = 0; i < rowIndex.length; ++i) {
            if (rowIndex[i].equals(row)) {
                position.row = i - offset;
                break;
            }
        }
        for(int i = 0; i < colIndex.length; ++i) {
            if(colIndex[i].equals(col)) {
                position.col = i;
                break;
            }
        }
        return position;
    }

    @Override
    public String toString() {
        return string;
    }
}
