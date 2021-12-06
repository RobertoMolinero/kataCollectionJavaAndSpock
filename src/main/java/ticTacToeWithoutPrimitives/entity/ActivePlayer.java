package ticTacToeWithoutPrimitives.entity;

public enum ActivePlayer {
    PLAYER_ONE("Bud", 'x'),
    PLAYER_TWO("Terrence", '+');

    private String name;

    private Character piece;

    ActivePlayer(String name, Character piece) {
        this.name = name;
        this.piece = piece;
    }

    public Character getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }
}
