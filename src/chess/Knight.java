package chess;

import static chess.Game.*;

class Knight extends Piece {

    Knight(Position position, Player owner, Board board) {
        super(position, owner, board);
    }

    @Override
    boolean canMoveTo(Position newPosition) {
        if(!super.canMoveTo(newPosition)) {
            return false;
        }

        return canMoveInLShape(newPosition);
    }

    boolean canMoveInLShape(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return (position.horizontalDistanceTo(newPosition) == 2 && position.verticalDistanceTo(newPosition) == 1)
                || (position.horizontalDistanceTo(newPosition) == 1 && position.verticalDistanceTo(newPosition) == 2)
                && (board.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    @Override
    public String toString() {
        if(owner == white) {
            return "♘";
        } else {
            return "♞";
        }
    }
}
