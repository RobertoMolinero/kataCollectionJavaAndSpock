package ticTacToeWithoutPrimitives.entity;

public enum GameState {
    PLAYER_ONE_WIN(ActivePlayer.PLAYER_ONE.getName() + " gewinnt."),
    PLAYER_TWO_WIN(ActivePlayer.PLAYER_TWO.getName() + " gewinnt."),
    UNDECIDED("Weiterkämpfen!"),
    DRAW("Draw.");

    String result;

    GameState(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
