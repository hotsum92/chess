package chess;

class Empty extends Piece {

    Empty(Position position, Board board) {
        super(position, null, board, true);
    }

    @Override
    boolean canMoveTo(Position newPosition) {
        return false;
    }

    @Override
    public String toString() {
        if(board.hasSelectedPiece()) {
            var piece = board.selectedPiece;
            if(piece.canMoveTo(position)) {
                return "□";
            } else {
                return "・";
            }
        }
        return "・";
    }
}
