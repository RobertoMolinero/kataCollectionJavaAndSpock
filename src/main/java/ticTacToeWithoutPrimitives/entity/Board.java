package ticTacToeWithoutPrimitives.entity;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final Character EMPTY = 'o';

    private Map<Koordinate, Character> fields = new HashMap<>(Map.of(
            new Koordinate(0, 0), EMPTY,
            new Koordinate(1, 0), EMPTY,
            new Koordinate(2, 0), EMPTY,
            new Koordinate(0, 1), EMPTY,
            new Koordinate(1, 1), EMPTY,
            new Koordinate(2, 1), EMPTY,
            new Koordinate(0, 2), EMPTY,
            new Koordinate(1, 2), EMPTY,
            new Koordinate(2, 2), EMPTY
    ));

    public Board() {
    }

    public Map<Koordinate, Character> getFields() {
        return fields;
    }

    public void setCell(ActivePlayer activePlayer, int x, int y) {
        fields.replace(new Koordinate(x, y), activePlayer.getPiece());
    }

    public boolean isCellEmpty(int x, int y) {
        Character character = fields.get(new Koordinate(x, y));
        return EMPTY.equals(character);
    }

    public boolean isActivePlayerWinning(ActivePlayer activePlayer) {
        for (int i = 0; i < 3; i++) {
            if (isRowComplete(activePlayer, i) || isColumnComplete(activePlayer, i))
                return true;
        }

        return isDiagonalComplete(activePlayer);
    }

    private boolean isRowComplete(ActivePlayer activePlayer, int y) {
        return fields.get(new Koordinate(0, y)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(1, y)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(2, y)) == activePlayer.getPiece();
    }

    private boolean isColumnComplete(ActivePlayer activePlayer, int x) {
        return fields.get(new Koordinate(x, 0)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(x, 1)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(x, 2)) == activePlayer.getPiece();
    }

    private boolean isDiagonalComplete(ActivePlayer activePlayer) {
        boolean down = fields.get(new Koordinate(0, 0)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(1, 1)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(2, 2)) == activePlayer.getPiece();
        boolean up = fields.get(new Koordinate(2, 0)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(1, 1)) == activePlayer.getPiece() &&
                fields.get(new Koordinate(0, 2)) == activePlayer.getPiece();
        return down || up;
    }

    public boolean isBoardFull() {
        for (Character value : fields.values()) {
            if (value == 'o') {
                return false;
            }
        }
        return true;
    }
}
