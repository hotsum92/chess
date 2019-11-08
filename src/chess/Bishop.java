package chess;

import static chess.Board.*;

class Bishop extends Piece {

    Bishop(Position position, Player owner, Board board) {
        super(position, owner, board);
    }

    @Override
    public String toString() {
        if(owner == white) {
            return "♗";
        } else {
            return "♝";
        }
    }

}
