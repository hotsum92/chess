package chess;

class Bishop extends Piece {

    boolean isValidMove(Position newPosition) {
        if (!super.isValidMove(position)) {
            return false;
        }

        return Math.abs(newPosition.getCol() - this.position.getCol()) == Math.abs(newPosition.getRow() - this.position.getRow());
    }

}
