package chess;

import java.util.*;

import static chess.Game.*;

class Board {

    Piece[][] pieces;
    Player currentPlayer;
    Piece selectedPiece;

    Board() {
        currentPlayer = white;
        pieces = new Piece[][] {
                {
                    new Rook  (new Position(0, 0), black, this),
                    new Knight(new Position(0, 1), black, this),
                    new Bishop(new Position(0, 2), black, this),
                    new Queen (new Position(0, 3), black, this),
                    new King  (new Position(0, 4), black, this),
                    new Bishop(new Position(0, 5), black, this),
                    new Knight(new Position(0, 6), black, this),
                    new Rook  (new Position(0, 7), black, this)
                },
                {
                    new Pawn  (new Position(1, 0), black, this),
                    new Pawn  (new Position(1, 1), black, this),
                    new Pawn  (new Position(1, 2), black, this),
                    new Pawn  (new Position(1, 3), black, this),
                    new Pawn  (new Position(1, 4), black, this),
                    new Pawn  (new Position(1, 5), black, this),
                    new Pawn  (new Position(1, 6), black, this),
                    new Pawn  (new Position(1, 7), black, this)
                },
                {
                    new Empty(new Position(2, 0), this),
                    new Empty(new Position(2, 1), this),
                    new Empty(new Position(2, 2), this),
                    new Empty(new Position(2, 3), this),
                    new Empty(new Position(2, 4), this),
                    new Empty(new Position(2, 5), this),
                    new Empty(new Position(2, 6), this),
                    new Empty(new Position(2, 7), this)
                },
                {
                    new Empty(new Position(3, 0), this),
                    new Empty(new Position(3, 1), this),
                    new Empty(new Position(3, 2), this),
                    new Empty(new Position(3, 3), this),
                    new Empty(new Position(3, 4), this),
                    new Empty(new Position(3, 5), this),
                    new Empty(new Position(3, 6), this),
                    new Empty(new Position(3, 7), this)
                },
                {
                    new Empty(new Position(4, 0), this),
                    new Empty(new Position(4, 1), this),
                    new Empty(new Position(4, 2), this),
                    new Empty(new Position(4, 3), this),
                    new Empty(new Position(4, 4), this),
                    new Empty(new Position(4, 5), this),
                    new Empty(new Position(4, 6), this),
                    new Empty(new Position(4, 7), this)
                },
                {
                    new Empty(new Position(5, 0), this),
                    new Empty(new Position(5, 1), this),
                    new Empty(new Position(5, 2), this),
                    new Empty(new Position(5, 3), this),
                    new Empty(new Position(5, 4), this),
                    new Empty(new Position(5, 5), this),
                    new Empty(new Position(5, 6), this),
                    new Empty(new Position(5, 7), this)
                },
                {
                    new Pawn  (new Position(6, 0), white, this),
                    new Pawn  (new Position(6, 1), white, this),
                    new Pawn  (new Position(6, 2), white, this),
                    new Pawn  (new Position(6, 3), white, this),
                    new Pawn  (new Position(6, 4), white, this),
                    new Pawn  (new Position(6, 5), white, this),
                    new Pawn  (new Position(6, 6), white, this),
                    new Pawn  (new Position(6, 7), white, this)
                },
                {
                    new Rook  (new Position(7, 0), white, this),
                    new Knight(new Position(7, 1), white, this),
                    new Bishop(new Position(7, 2), white, this),
                    new King  (new Position(7, 3), white, this),
                    new Queen (new Position(7, 4), white, this),
                    new Bishop(new Position(7, 5), white, this),
                    new Knight(new Position(7, 6), white, this),
                    new Rook  (new Position(7, 7), white, this)
                }
        };
    }

    boolean currentPlayerHasPieceAt(Position position) {
        var piece = pieceAt(position);
        return piece.owner == currentPlayer;
    }

    boolean move(Position from, Position to) {
        var piece = pieceAt(from);
        if(!piece.canMoveTo(to)) return false;
        piece.move(to);
        piece.hasMoved = true;
        return true;
    }

    boolean hasSelectedPiece() {
        return selectedPiece != null;
    }

    void select(Position position) {
        selectedPiece = pieceAt(position);
    }

    Position[] allPossibleMovements() {
        ArrayList<Position> positions = new ArrayList<>();
        for(int row = 0; row < pieces.length; ++row) {
            for(int col = 0; col < pieces.length; ++col) {
                if(pieceAt(new Position(row, col)).allPossibleMovements().length > 0) positions.add(new Position(row, col));
            }
        }
        return positions.toArray(new Position[positions.size()]);
    }

    boolean move(Position to) {
        if(!selectedPiece.canMoveTo(to)) return false;
        selectedPiece.move(to);
        selectedPiece.hasMoved = true;
        return true;
    }

    void remove(Position position) {
        pieces[position.row][position.col] = new Empty(new Position(position.row, position.col), this);
    }

    void replace(Position position, Piece piece) {
        pieces[position.row][position.col] = piece;
    }

    Piece pieceAt(Position position) {
        return pieces[position.row][position.col];
    }

    boolean isForward(Position from, Position to) {
        if(currentPlayer == white) {
            return from.row < to.row;
        } else {
            return from.row > to.row;
        }
    }

    boolean isOutOfBoardAt(Position position) {
        return position.row >= 0 && position.col >= 0
                && position.row < pieces.length && position.col < pieces.length;
    }

    boolean pieceExistsBetween(Position from, Position to) {

        if(to.isCross(from)) {

            if(to.isAbove(from)) {
                for(int i = from.row - 1; i > to.row; --i) {
                    if(!(pieces[i][from.col].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isBelow(from)) {
                for(int i = from.row + 1; i < to.row; ++i) {
                    if(!(pieces[i][from.col].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isRight(from)) {
                for(int i = from.col + 1; i < to.col; ++i) {
                    if(!(pieces[from.row][i].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isLeft(from)) {
                for(int i = from.col - 1; i > to.col; --i) {
                    if(!(pieces[from.row][i].isEmpty)) {
                        return true;
                    }
                }
            }
        }

        if(to.isDiagonal(from)) {
            int gap = Math.abs(from.col - from.row);
            int sum = from.col + from.row;

            if(to.isRight(from) && to.isAbove(from)) {
                for(int i = from.row - 1; i > to.row; --i) {
                    if(!(pieces[i][sum - i].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isLeft(from) && to.isBelow(from)) {
                for(int i = from.row + 1; i < to.row; ++i) {
                    if(!(pieces[i][sum - i].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isRight(from) && to.isBelow(from)) {
                for(int i = from.row + 1; i < to.row; ++i) {
                    if(!(pieces[i][Math.abs(gap - i)].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isLeft(from) && to.isAbove(from)) {
                for(int i = from.row - 1; i > to.row; --i) {
                    if(!(pieces[i][Math.abs(gap - i)].isEmpty)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String str= "";

        for(int row = 0; row < pieces.length; ++row) {
            for(int col = 0; col < pieces[0].length; ++col) {
                str += pieces[row][col];
            }
            str += "\n";
        }

        return str;
    }
}
