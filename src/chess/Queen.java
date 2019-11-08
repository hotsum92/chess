package chess;

class Queen extends Piece {

    Queen(Position position, Player owner, Game game) {
        super(position, owner, game);
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
