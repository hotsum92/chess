package chess;

import static chess.Game.*;

class Knight extends Piece {

    Knight(Position position, Player owner, Game game) {
        super(position, owner, game);
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
