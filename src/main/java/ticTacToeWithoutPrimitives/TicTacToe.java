package ticTacToeWithoutPrimitives;

import ticTacToeWithoutPrimitives.entity.ActivePlayer;
import ticTacToeWithoutPrimitives.entity.Board;
import ticTacToeWithoutPrimitives.entity.GameState;
import ticTacToeWithoutPrimitives.entity.Koordinate;

public class TicTacToe {

    ActivePlayer activePlayer;

    Board board;

    public TicTacToe() {
        this.activePlayer = ActivePlayer.PLAYER_ONE;
        this.board = new Board();
    }

    public String getOutput() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Active Player: ").append(activePlayer.getName()).append("\n");

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                sb.append(board.getFields().get(new Koordinate(x, y)));
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void enterMove(int x, int y) {
        if (board.isCellEmpty(x, y)) {
            board.setCell(activePlayer, x, y);
        }
    }

    public void switchActivePlayer() {
        if (activePlayer.equals(ActivePlayer.PLAYER_ONE))
            activePlayer = ActivePlayer.PLAYER_TWO;
        else
            activePlayer = ActivePlayer.PLAYER_ONE;
    }

    public GameState evaluateGame() {
        if(board.isActivePlayerWinning(activePlayer)) {
            if(ActivePlayer.PLAYER_ONE.equals(activePlayer)) {
                return GameState.PLAYER_ONE_WIN;
            } else {
                return GameState.PLAYER_TWO_WIN;
            }
        }
        if (board.isBoardFull()) return GameState.DRAW;
        return GameState.UNDECIDED;
    }
}
