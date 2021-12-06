package ticTacToe;

import ticTacToe.entity.GameState;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.switchActivePlayer();

        do {
            int x, y;

            t.switchActivePlayer();
            System.out.println(t.getOutput());

            Scanner input = new Scanner(System.in);
            System.out.print("x: ");
            x = input.nextInt();
            System.out.print("y: ");
            y = input.nextInt();

            t.enterMove(x, y);
        } while (t.evaluateGame() == GameState.UNDECIDED);

        System.out.println(t.getOutput());
        System.out.println(t.evaluateGame().getResult());
    }
}
