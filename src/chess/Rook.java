package chess;

import static chess.Game.*;

class Rook extends Piece {

    Rook(Position position, Player owner, Board board) {
        super(position, owner, board);
    }

    @Override
    boolean canMoveTo(Position newPosition) {
        if(!super.canMoveTo(newPosition)) {
            return false;
        }

        return canMoveVertically(newPosition)
                || canMoveHorizontally(newPosition);
    }

    boolean canMoveVertically(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return position.isVetical(newPosition)
                && !board.pieceExistsBetween(position, newPosition)
                && (board.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    boolean canMoveHorizontally(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return position.isHotizontal(newPosition)
                && !board.pieceExistsBetween(position, newPosition)
                && (board.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }


    @Override
    public String toString() {
        if(owner == white) {
            return "♖";
        } else {
            return "♜";
        }
    }
}
