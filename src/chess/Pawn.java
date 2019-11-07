package chess;

class Pawn extends Piece {

    Pawn(Position position, Player owner, Game game) {
        super(position, owner, game);
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

            game.remove(position);
            game.replace(newPosition, this);

            position = newPosition;
            return true;
        }

        return false;
    }

    boolean canMoveOneStepForward(Position newPosition) {
        return newPosition.isForward(position, game)
                && newPosition.isVetical(position)
                && position.verticalDistanceTo(newPosition) == 1
                && game.pieceAt(newPosition).isEmpty;
    }

    boolean canMoveTwoStepForward(Position newPosition) {
        return newPosition.isForward(position, game)
                && newPosition.isVetical(position)
                && position.verticalDistanceTo(newPosition) == 2
                && game.pieceAt(newPosition).isEmpty
                && !game.pieceExistsBetween(position, newPosition)
                && !hasMoved;
    }

    boolean canMoveOneStepDiagonally(Position newPosition) {
        var piece = game.pieceAt(newPosition);
        return position.isDiagonal(newPosition)
                && newPosition.isForward(position, game)
                && position.verticalDistanceTo(newPosition) == 1
                && !piece.isEmpty
                && !owner.has(piece);
    }

    @Override
    public String toString() {
        if(owner == game.white) {
            return "♙";
        } else {
            return "♟";
        }
    }
}
