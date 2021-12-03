package ticTacToe;

import ticTacToe.entity.ActivePlayer;
import ticTacToe.entity.Board;
import ticTacToe.entity.GameState;

public class TicTacToe {

    ActivePlayer activePlayer;

    Board board;

    public TicTacToe() {
        this.activePlayer = ActivePlayer.PLAYER_ONE;
        this.board = new Board();
    }

    public String getOutput() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Active Player: " + activePlayer.getName() + "\n");

        for (char[] row : board.getFields()) {
            for (char col : row) {
                sb.append(col);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void enterMove(int x, int y) {
        if (board.isFieldCoordinateValid(x, y) && board.isCellEmpty(x, y)) {
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
