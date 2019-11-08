package chess;

import static chess.Board.*;

class King extends Piece {

    King(Position position, Player owner, Board board) {
        super(position, owner, board);
    }

    @Override
    public String toString() {
        if(owner == white) {
            return "♔";
        } else {
            return "♚";
        }
    }
}
