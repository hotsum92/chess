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
        return newPosition.isVetical(position)
                && !board.pieceExistsBetween(position, newPosition)
                && (board.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    boolean canMoveHorizontally(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return newPosition.isHotizontal(position)
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