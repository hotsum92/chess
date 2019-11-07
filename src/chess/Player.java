package chess;

public class Player {
    String color;

    Player(String color) {
        this.color = color;
    }

    boolean has(Piece p) {
        return p.owner == this;
    }

    @Override
    public String toString() {
        return color;
    }
}
