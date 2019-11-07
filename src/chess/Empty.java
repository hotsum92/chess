package chess;

class Empty extends Piece {

    Empty() {
        super(null, null, null, true);
    }

    @Override
    boolean move(Position newPosition) {
        return false;
    }

    @Override
    public String toString() {
        return "・";
    }
}
