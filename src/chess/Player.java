package chess;

public class Player {
    String color;

    public Player(String color) {
        this.color = color;
    }

    public boolean has(Piece p) {
        return p.owner == this;
    }
}
