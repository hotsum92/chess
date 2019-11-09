package chess;

import static chess.Game.*;

class Rook extends Piece {

    Rook(Position position, Player owner, Board board) {
        super(position, owner, board);
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
