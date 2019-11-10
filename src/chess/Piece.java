package chess;

import java.util.*;

abstract class Piece {
    Board board;
    Player owner;
    Position position;
    boolean isEmpty;
    boolean hasMoved;

    Piece(Position position, Player owner, Board board, boolean isEmpty) {
        this.position = position;
        this.board = board;
        this.owner = owner;
        this.isEmpty = isEmpty;
        hasMoved = false;
    }

    Piece(Position position, Player owner, Board board) {
        this(position, owner, board, false);
    }

    boolean canMoveTo(Position newPosition) {
        return board.isOutOfBoardAt(newPosition);
    }

    void move(Position newPosition) {
        board.remove(position);
        board.replace(newPosition, this);

        position = newPosition;
    }

    Position[] allPossibleMovements() {
        ArrayList<Position> positions = new ArrayList<>();
        var pieces = board.pieces;
        for(int row = 0; row < pieces.length; ++row) {
            for(int col = 0; col < pieces.length; ++col) {
                if(canMoveTo(new Position(row, col))) positions.add(new Position(row, col));
            }
        }
        return positions.toArray(new Position[positions.size()]);
    }
}
