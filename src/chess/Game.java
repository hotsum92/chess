package chess;

class Game {
    Empty empty;
    Piece[][] board;
    Player white;
    Player black;
    Player[] players;

    Player currentPlayer;

    Game() {
        white = new Player("White");
        black = new Player("Black");

        players = new Player[]{ white, black };
        currentPlayer = white;
        empty = new Empty();

        board = new Piece[][] {
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
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty
                },
                {
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty
                },
                {
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty
                },
                {
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty,
                    empty
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

    void remove(Position position) {
        board[position.row][position.col] = empty;
    }

    void replace(Position position, Piece piece) {
        board[position.row][position.col] = piece;
    }

    void togglePlayer() {
        currentPlayer = players[0].equals(currentPlayer) ? players[1] : players[0];
    }

    Piece pieceAt(Position position) {
        return board[position.row][position.col];
    }

    boolean pieceExistsBetween(Position from, Position to) {

        if(to.isCross(from)) {

            if(to.isAbove(from)) {
                for(int i = from.row - 1; i > to.row; --i) {
                    if(!(board[i][from.col].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isBelow(from)) {
                for(int i = from.row + 1; i < to.row; ++i) {
                    if(!(board[i][from.col].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isRight(from)) {
                for(int i = from.col + 1; i < to.col; ++i) {
                    if(!(board[from.row][i].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isLeft(from)) {
                for(int i = from.col - 1; i > to.col; --i) {
                    if(!(board[from.row][i].isEmpty)) {
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
                    if(!(board[i][sum - i].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isLeft(from) && to.isBelow(from)) {
                for(int i = from.row + 1; i < to.row; ++i) {
                    if(!(board[i][sum - i].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isRight(from) && to.isBelow(from)) {
                for(int i = from.row + 1; i < to.row; ++i) {
                    if(!(board[i][Math.abs(gap - i)].isEmpty)) {
                        return true;
                    }
                }
            }

            if(to.isLeft(from) && to.isAbove(from)) {
                for(int i = from.row - 1; i > to.row; --i) {
                    if(!(board[i][Math.abs(gap - i)].isEmpty)) {
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

        for(int row = 0; row < board.length; ++row) {
            for(int col = 0; col < board[0].length; ++col) {
                str += board[row][col];
            }
            str += "\n";
        }

        return str;
    }
}
