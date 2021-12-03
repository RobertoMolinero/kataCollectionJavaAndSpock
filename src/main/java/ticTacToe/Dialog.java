package ticTacToe;

import ticTacToe.entity.GameState;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class Dialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonExit;
    private JButton buttonX0Y0;
    private JButton buttonX0Y2;
    private JButton buttonX0Y1;
    private JButton buttonX1Y0;
    private JButton buttonX1Y1;
    private JButton buttonX1Y2;
    private JButton buttonX2Y0;
    private JButton buttonX2Y1;
    private JButton buttonX2Y2;
    private JLabel labelActualPlayer;
    private JLabel labelActualPlayerValue;
    private JLabel labelResult;
    private JLabel labelResultValue;

    TicTacToe t = new TicTacToe();

    List<JButton> allButtons = Arrays.asList(
            buttonX0Y0, buttonX0Y2, buttonX0Y1,
            buttonX1Y0, buttonX1Y1, buttonX1Y2,
            buttonX2Y0, buttonX2Y1, buttonX2Y2
    );

    public Dialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonExit);

        setTitle("Tic Tac Toe");

        GameState gameState = t.evaluateGame();
        labelResultValue.setText(gameState.getResult());
        labelActualPlayerValue.setText(t.activePlayer.getName());

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });

        buttonX0Y0.addActionListener(e -> enterMove(0, 0, buttonX0Y0));
        buttonX1Y0.addActionListener(e -> enterMove(1, 0, buttonX1Y0));
        buttonX2Y0.addActionListener(e -> enterMove(2, 0, buttonX2Y0));
        buttonX0Y1.addActionListener(e -> enterMove(0, 1, buttonX0Y1));
        buttonX1Y1.addActionListener(e -> enterMove(1, 1, buttonX1Y1));
        buttonX2Y1.addActionListener(e -> enterMove(2, 1, buttonX2Y1));
        buttonX0Y2.addActionListener(e -> enterMove(0, 2, buttonX0Y2));
        buttonX1Y2.addActionListener(e -> enterMove(1, 2, buttonX1Y2));
        buttonX2Y2.addActionListener(e -> enterMove(2, 2, buttonX2Y2));

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onExit() {
        dispose();
    }

    private void enterMove(int x, int y, JButton button) {
        t.enterMove(x, y);
        button.setText("" + t.activePlayer.getPiece());

        GameState gameState = t.evaluateGame();
        labelResultValue.setText(gameState.getResult());

        if (!GameState.UNDECIDED.equals(gameState)) {
            for (JButton jb : allButtons) {
                jb.setEnabled(false);
            }
            return;
        }

        t.switchActivePlayer();
        labelActualPlayerValue.setText(t.activePlayer.getName());
    }

    public static void main(String[] args) {
        Dialog dialog = new Dialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
