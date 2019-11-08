package chess;

class Queen extends Piece {

    Queen(Position position, Player owner, Game game) {
        super(position, owner, game);
    }

    @Override
    boolean move(Position newPosition) {
        if(!super.isValidMove(newPosition)) {
            return false;
        }

        if(canMoveVertically(newPosition)
                || canMoveHorizontally(newPosition)
                || canMoveDiagonally(newPosition)) {

            game.remove(position);
            game.replace(newPosition, this);

            position = newPosition;
            return true;
        }
        return false;
    }

    boolean canMoveVertically(Position newPosition) {
        var piece = game.pieceAt(newPosition);
        return newPosition.isVetical(position)
                && !game.pieceExistsBetween(position, newPosition)
                && (game.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    boolean canMoveHorizontally(Position newPosition) {
        var piece = game.pieceAt(newPosition);
        return newPosition.isHotizontal(position)
                && !game.pieceExistsBetween(position, newPosition)
                && (game.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    boolean canMoveDiagonally(Position newPosition) {
        var piece = game.pieceAt(newPosition);
        return newPosition.isDiagonal(position)
                && !game.pieceExistsBetween(position, newPosition)
                && (game.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    @Override
    public String toString() {
        if(owner == game.white) {
            return "♕";
        } else {
            return "♛";
        }
    }
}
