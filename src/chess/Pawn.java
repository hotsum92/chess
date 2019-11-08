package chess;

import static chess.Board.*;

class Pawn extends Piece {

    Pawn(Position position, Player owner, Board board) {
        super(position, owner, board);
    }

    @Override
    boolean canMoveTo(Position newPosition) {
        if(!super.canMoveTo(newPosition)) {
            return false;
        }

        // TODO: implement En Passant
        // TODO: implement Promotion

        return canMoveOneStepForward(newPosition)
                || canMoveTwoStepForward(newPosition)
                || canMoveOneStepDiagonally(newPosition);
    }

    boolean canMoveOneStepForward(Position newPosition) {
        return board.isForward(newPosition ,position)
                && newPosition.isVetical(position)
                && position.verticalDistanceTo(newPosition) == 1
                && board.pieceAt(newPosition).isEmpty;
    }

    boolean canMoveTwoStepForward(Position newPosition) {
        return board.isForward(newPosition ,position)
                && newPosition.isVetical(position)
                && position.verticalDistanceTo(newPosition) == 2
                && board.pieceAt(newPosition).isEmpty
                && !board.pieceExistsBetween(position, newPosition)
                && !hasMoved;
    }

    boolean canMoveOneStepDiagonally(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return position.isDiagonal(newPosition)
                && board.isForward(newPosition ,position)
                && position.verticalDistanceTo(newPosition) == 1
                && !piece.isEmpty
                && !owner.has(piece);
    }

    @Override
    public String toString() {
        if(owner == white) {
            return "♙";
        } else {
            return "♟";
        }
    }
}
