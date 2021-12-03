package ticTacToe;

import ticTacToe.entity.GameState;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.switchActivePlayer();

        do {
            int row, column;

            t.switchActivePlayer();
            System.out.println(t.getOutput());

            Scanner input = new Scanner(System.in);
            System.out.print("row: ");
            row = input.nextInt();
            System.out.print("column: ");
            column = input.nextInt();

            t.enterMove(row, column);
        } while (t.evaluateGame() == GameState.UNDECIDED);

        System.out.println(t.getOutput());
        System.out.println(t.evaluateGame().getResult());
    }
}
