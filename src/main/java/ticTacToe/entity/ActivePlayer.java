package ticTacToe.entity;

public enum ActivePlayer {
    PLAYER_ONE("Bud", 'x'),
    PLAYER_TWO("Terrence", '+');

    private String name;

    private char piece;

    ActivePlayer(String name, char piece) {
        this.name = name;
        this.piece = piece;
    }

    public char getPiece() {
        return piece;
    }

    public String getName() {
        return name;
    }
}
