package chess;

class Empty extends Piece {

    Empty() {
        super(null, null, null, true);
    }

    @Override
    boolean canMoveTo(Position newPosition) {
        return false;
    }

    @Override
    public String toString() {
        return "・";
    }
}
