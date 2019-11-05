package chess;

import javax.sql.PooledConnection;
import javax.swing.text.StyledEditorKit;

abstract class Piece {
    Game game;
    Player owner;
    Position position;
    boolean isEmpty;
    boolean hasMoved;

    public Piece(Position position, Player owner, Game game, boolean isEmpty) {
        this.position = position;
        this.game = game;
        this.owner = owner;
        this.isEmpty = isEmpty;
        hasMoved = false;
    }

    public Piece(Position position, Player owner, Game game) {
        this(position, owner, game,false);
    }

    boolean isValidMove(Position newPosition) {
        return newPosition.isOutOfBoard();
    }

    abstract boolean move(Position newPosition);
}
