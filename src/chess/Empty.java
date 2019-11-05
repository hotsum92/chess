package chess;

public class Empty extends Piece {

    public Empty() {
        super(null, null, null, true);
    }

    @Override
    boolean move(Position newPosition) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "・";
    }
}
