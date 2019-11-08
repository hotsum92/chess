package chess;

import static chess.Board.*;

class Position {

    int row;
    int col;

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    boolean isDiagonal(Position position) {
        return Math.abs(col - position.col) == Math.abs(row - position.row);
    }

    boolean isCross(Position position) {
        return isVetical(position) || isHotizontal(position);
    }

    boolean isVetical(Position position) {
        return col == position.col;
    }

    boolean isHotizontal(Position position) {
        return row == position.row;
    }

    boolean isRight(Position position) {
        return col > position.col;
    }

    boolean isLeft(Position position) {
        return col < position.col;
    }

    boolean isAbove(Position position) {
        return row < position.row;
    }

    boolean isBelow(Position position) {
        return row > position.row;
    }

    boolean isForward(Position position, Board board) {
        if(board.currentPlayer == white) {
            return row < position.row;
        } else {
            return row > position.row;
        }
    }

    int verticalDistanceTo(Position position) {
        return Math.abs(row - position.row);
    }

    int horizontalDistanceTo(Position position) {
        return Math.abs(col - position.row);
    }

    boolean isOutOfBoard() {
        return row >= 0 && col >= 0
                && row <= 8 && col <= 8;
    }

    @Override
    public String toString() {
        return "row: " + row + " col: " + col;
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Position)) return false;
        var position = (Position)object;
        return row == position.row && col == position.col;
    }
}
