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
        fields[x][y] = activePlayer.getPiece();
    }

    public boolean isFieldCoordinateValid(int x, int y) {
        return x >= 0 && x <= 2 && y >= 0 && y <= 2;
    }

    public boolean isCellEmpty(int x, int y) {
        return fields[x][y] == EMPTY;
    }

    public boolean isActivePlayerWinning(ActivePlayer activePlayer) {
        for (int i = 0; i < 3; i++) {
            if (isRowComplete(activePlayer, i) || isColumnComplete(activePlayer, i))
                return true;
        }

        if (isDiagonalComplete(activePlayer))
            return true;

        return false;
    }

    private boolean isRowComplete(ActivePlayer activePlayer, int x) {
        if (fields[x][0] == activePlayer.getPiece() && fields[x][1] == activePlayer.getPiece() && fields[x][2] == activePlayer.getPiece())
            return true;
        return false;
    }

    private boolean isColumnComplete(ActivePlayer activePlayer, int y) {
        if (fields[0][y] == activePlayer.getPiece() && fields[1][y] == activePlayer.getPiece() && fields[2][y] == activePlayer.getPiece())
            return true;
        return false;
    }

    private boolean isDiagonalComplete(ActivePlayer activePlayer) {
        return (fields[0][0] == activePlayer.getPiece() && fields[1][1] == activePlayer.getPiece() && fields[2][2] == activePlayer.getPiece())
                || (fields[2][0] == activePlayer.getPiece() && fields[1][1] == activePlayer.getPiece() && fields[0][2] == activePlayer.getPiece());
    }

    public boolean isBoardFull() {
        for (char[] row : fields) {
            for (char column : row) {
                if (column == 'o') {
                    return false;
                }
            }
        }
        return true;
    }
}
