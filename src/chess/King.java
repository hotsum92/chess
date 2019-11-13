package chess;

import static chess.Game.*;

class King extends Piece {

    King(Position position, Player owner, Board board) {
        super(position, owner, board);
    }


    @Override
    boolean canMoveTo(Position newPosition) {
        if(!super.canMoveTo(newPosition)) {
            return false;
        }

        // TODO: implement castling

        return canMoveVertically(newPosition)
                || canMoveHorizontally(newPosition);
    }

    boolean canMoveVertically(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return position.isVetical(newPosition)
                && position.verticalDistanceTo(newPosition) == 1
                && !board.pieceExistsBetween(position, newPosition)
                && (board.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    boolean canMoveHorizontally(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return position.isHotizontal(newPosition)
                && position.horizontalDistanceTo(newPosition) == 1
                && !board.pieceExistsBetween(position, newPosition)
                && (board.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }


    @Override
    public String toString() {
        if(owner == white) {
            return "♔";
        } else {
            return "♚";
        }
    }
}
