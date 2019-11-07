package chess;

abstract class Piece {
    Game game;
    Player owner;
    Position position;
    boolean isEmpty;
    boolean hasMoved;

    Piece(Position position, Player owner, Game game, boolean isEmpty) {
        this.position = position;
        this.game = game;
        this.owner = owner;
        this.isEmpty = isEmpty;
        hasMoved = false;
    }

    Piece(Position position, Player owner, Game game) {
        this(position, owner, game,false);
    }

    boolean isValidMove(Position newPosition) {
        return newPosition.isOutOfBoard();
    }

    boolean move(Position newPosition) {
        if (!isValidMove(newPosition)) {
            return false;
        }

        game.remove(position);
        game.replace(newPosition, this);

        position = newPosition;
        return true;
    }
}
