package chess;

abstract class Piece {
    Position position;

    boolean isValidMove(Position newPosition) {
        return newPosition.getRow() > 0 && newPosition.getCol() > 0
                && newPosition.getRow() < 8 && newPosition.getCol() < 8;
    }
}
