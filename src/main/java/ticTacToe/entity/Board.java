package ticTacToe.entity;

public class Board {

    private static final char EMPTY = 'o';

    private char[][] fields;

    public Board() {
        this.fields = new char[][]{
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };
    }

    public char[][] getFields() {
        return fields;
    }

    public void setCell(ActivePlayer activePlayer, int x, int y) {
        fields[y][x] = activePlayer.getPiece();
    }

    public boolean isFieldCoordinateValid(int x, int y) {
        return x >= 0 && x <= 2 && y >= 0 && y <= 2;
    }

    public boolean isCellEmpty(int x, int y) {
        return fields[y][x] == EMPTY;
    }

    public boolean isActivePlayerWinning(ActivePlayer activePlayer) {
        for (int i = 0; i < 3; i++) {
            if (isRowComplete(activePlayer, i) || isColumnComplete(activePlayer, i))
                return true;
        }

        return isDiagonalComplete(activePlayer);
    }

    private boolean isRowComplete(ActivePlayer activePlayer, int y) {
        return fields[0][y] == activePlayer.getPiece() && fields[1][y] == activePlayer.getPiece() && fields[2][y] == activePlayer.getPiece();
    }

    private boolean isColumnComplete(ActivePlayer activePlayer, int x) {
        return fields[x][0] == activePlayer.getPiece() && fields[x][1] == activePlayer.getPiece() && fields[x][2] == activePlayer.getPiece();
    }

    private boolean isDiagonalComplete(ActivePlayer activePlayer) {
        return (fields[0][0] == activePlayer.getPiece() && fields[1][1] == activePlayer.getPiece() && fields[2][2] == activePlayer.getPiece())
                || (fields[2][0] == activePlayer.getPiece() && fields[1][1] == activePlayer.getPiece() && fields[0][2] == activePlayer.getPiece());
    }

    public boolean isBoardFull() {
        for (char[] y : fields) {
            for (char x : y) {
                if (x == 'o') {
                    return false;
                }
            }
        }
        return true;
    }
}
