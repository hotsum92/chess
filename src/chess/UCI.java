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

    UCI(Position position) {
        string =  colIndex[position.col] + rowIndex[position.row];
    }

    boolean isValid() {
        var col = string.substring(0, 1);
        var row = string.substring(1, 2);
        return searchRowIndex(row) != -1 && searchColIndex(col) != -1;
    }

    Position toPosition() {
        var col = string.substring(0, 1);
        var row = string.substring(1, 2);
        return new Position(searchRowIndex(row), searchColIndex(col));
    }

    int searchRowIndex(String row) {
        for(int i = offset; i < rowIndex.length; ++i) {
            if (rowIndex[i].equals(row)) {
                return i - offset;
            }
        }
        return -1;
    }

    int searchColIndex(String col) {
        for(int i = 0; i < colIndex.length; ++i) {
            if(colIndex[i].equals(col)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return string;
    }
}
