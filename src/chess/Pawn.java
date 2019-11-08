package chess;

import static chess.Board.*;

class Pawn extends Piece {

    Pawn(Position position, Player owner, Board board) {
        super(position, owner, board);
    }

    @Override
    boolean move(Position newPosition) {
        if(!super.isValidMove(newPosition)) {
            return false;
        }

        if(canMoveOneStepForward(newPosition)
                || canMoveTwoStepForward(newPosition)
                || canMoveOneStepDiagonally(newPosition)) {

            // TODO: implement En Passant
            // TODO: implement Promotion

            board.remove(position);
            board.replace(newPosition, this);

            position = newPosition;
            return true;
        }

        return false;
    }

    boolean canMoveOneStepForward(Position newPosition) {
        return newPosition.isForward(position, board)
                && newPosition.isVetical(position)
                && position.verticalDistanceTo(newPosition) == 1
                && board.pieceAt(newPosition).isEmpty;
    }

    boolean canMoveTwoStepForward(Position newPosition) {
        return newPosition.isForward(position, board)
                && newPosition.isVetical(position)
                && position.verticalDistanceTo(newPosition) == 2
                && board.pieceAt(newPosition).isEmpty
                && !board.pieceExistsBetween(position, newPosition)
                && !hasMoved;
    }

    boolean canMoveOneStepDiagonally(Position newPosition) {
        var piece = board.pieceAt(newPosition);
        return position.isDiagonal(newPosition)
                && newPosition.isForward(position, board)
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
