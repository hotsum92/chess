package chess;

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
        this(position, owner, board,false);
    }

    boolean canMoveTo(Position newPosition) {
        return newPosition.isOutOfBoard();
    }

    void move(Position newPosition) {
        if (!canMoveTo(newPosition)) {
            return;
        }

        board.remove(position);
        board.replace(newPosition, this);

        position = newPosition;
    }
}
