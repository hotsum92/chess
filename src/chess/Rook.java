package chess;

import static chess.Game.*;

class Rook extends Piece {

    Rook(Position position, Player owner, Game game) {
        super(position, owner, game);
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
