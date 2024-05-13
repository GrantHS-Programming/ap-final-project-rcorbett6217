import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.*;

import java.util.ArrayList;

public class Gomoku implements ActionListener {
    JFrame window = new JFrame("Gomoku Board");
    JButton[][] board = new JButton[15][15];
    int[][] boardMatrix = new int[15][15];

    JFrame winScreen = new JFrame("Congratulations!");

    private boolean currentPlayer = true;

    public static void main(String[] args) {
        new Gomoku();
    }





    public Gomoku(){

        winScreen.setLayout(new BorderLayout());
        winScreen.setSize(200,10);
        winScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setLayout(new BorderLayout());
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container boardContainer = new Container();
        boardContainer.setLayout(new GridLayout(15,15));
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new JButton();
                board[row][col].addActionListener(this);
                boardContainer.add(board[row][col]);
                board[row][col].setBackground(Color.LIGHT_GRAY);
                board[row][col].setOpaque(true);
                board[row][col].setBorderPainted(false);

            }

        }

        window.add(boardContainer, BorderLayout.CENTER);
        window.setVisible(true);
    }
    public void checkWin(int player){
        for (int row = 0; row < boardMatrix.length; row++) {
            for (int col = 0; col < boardMatrix[0].length; col++) {
                if(boardMatrix[row][col] == player){
                    //check right
                    if(col <= 10) {
                        if (boardMatrix[row][col + 1] == player && boardMatrix[row][col + 2] == player && boardMatrix[row][col + 3] == player && boardMatrix[row][col + 4] == player) {
                            System.out.println("win");
                            winScreen.setVisible(true);
                            for (int row1 = 0; row1 < board.length; row1++) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    board[row1][col1].setEnabled(false);
                                }
                            }

                        }
                    }
                    //check down
                    if(row <= 10){
                        if (boardMatrix[row + 1][col] == player && boardMatrix[row + 2][col] == player && boardMatrix[row + 3][col] == player && boardMatrix[row + 4][col] == player) {
                            System.out.println("win");
                            winScreen.setVisible(true);
                            for (int row1 = 0; row1 < board.length; row1++) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    board[row1][col1].setEnabled(false);
                                }
                            }
                        }
                    }
                    //check down and to the right
                    if(col <= 10 && row <= 10){
                        if (boardMatrix[row + 1][col + 1] == player && boardMatrix[row + 2][col + 2] == player && boardMatrix[row + 3][col + 3] == player && boardMatrix[row + 4][col + 4] == player) {
                            System.out.println("win");
                            winScreen.setVisible(true);
                            for (int row1 = 0; row1 < board.length; row1++) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    board[row1][col1].setEnabled(false);
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if(e.getSource().equals(board[row][col])){
                    board[row][col].setEnabled(false);
                    if(currentPlayer){
                        board[row][col].setIcon(new ColorIconRound(35, Color.BLACK));
                        boardMatrix[row][col] = 1;
                        checkWin(1);
                        currentPlayer = false;
                    }
                    else{
                        board[row][col].setIcon(new ColorIconRound(35, Color.WHITE));
                        boardMatrix[row][col] = 2;
                        checkWin(2);
                        currentPlayer = true;
                    }
                }
            }
        }

    }
}