package chess;

class Knight extends Piece {

    Knight(Position position, Player owner, Game game) {
        super(position, owner, game);
    }

    @Override
    boolean move(Position newPosition) {
        if(!super.isValidMove(newPosition)) {
            return false;
        }

        if(canMoveInLShape(newPosition)) {

            game.remove(position);
            game.replace(newPosition, this);

            position = newPosition;
            return true;
        }
        return false;
    }

    boolean canMoveInLShape(Position newPosition) {
        var piece = game.pieceAt(newPosition);
        return (position.horizontalDistanceTo(newPosition) == 2 && position.verticalDistanceTo(newPosition) == 1)
                || (position.horizontalDistanceTo(newPosition) == 1 && position.verticalDistanceTo(newPosition) == 2)
                && (game.pieceAt(newPosition).isEmpty || !owner.has(piece));
    }

    @Override
    public String toString() {
        if(owner == game.white) {
            return "♘";
        } else {
            return "♞";
        }
    }
}
