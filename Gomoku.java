import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;



public class Gomoku implements ActionListener {
    JFrame window = new JFrame("Gomoku Board");
    DrawGridOnJPanel gridPanel = new DrawGridOnJPanel();

    JButton[][] board = new JButton[15][15];
    int[][] boardMatrix = new int[15][15];

    JFrame winScreen = new JFrame("Congratulations!");

    private String coords = "9999";



    public static void main(String[] args) {
        new Gomoku();
    }

    public String getCoords(){
        //System.out.println(coords);
        return coords;
    }

    public void setPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRound(40, Color.BLUE));
        boardMatrix[row][col] = 1;
    }


    public Gomoku(){


        winScreen.setLayout(new BorderLayout());
        winScreen.setSize(200,10);
        winScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setLayout(new BorderLayout(0,0));
        window.setSize(900, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container boardContainer = new Container();
        boardContainer.setLayout(new GridLayout(15,15));
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new JButton();
                board[row][col].addActionListener(this);
                boardContainer.add(board[row][col]);
                board[row][col].setBackground(Color.lightGray);
                board[row][col].setOpaque(true);
                board[row][col].setBorderPainted(false);
                board[row][col].setContentAreaFilled(false);
                board[row][col].setBorder(null);


            }

        }

        window.add(boardContainer, BorderLayout.CENTER);
        window.setVisible(true);
        window.add(gridPanel);

    }
    public void SrightWinSetPiece(int row, int col){

    }
    public void CrightWinSetPiece(int row, int col){

    }
    public void SdownWinSetPiece(int row, int col){

    }
    public void CdownWinSetPiece(int row, int col){

    }

    public void SdownRightWinSetPiece(int row, int col){

    }
    public void CdownRightWinSetPiece(int row, int col){

    }

    public void SdownLeftWinSetPiece(int row, int col){

    }
    public void CdownLeftWinSetPiece(int row, int col){

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
                            if(player == 1){
                                SrightWinSetPiece(row, col);
                            }
                            else{
                                CrightWinSetPiece(row, col);
                            }
                            for (JButton[] jButtons : board) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    jButtons[col1].setEnabled(false);
                                }
                            }

                        }
                    }
                    //check down
                    if(row <= 10){
                        if (boardMatrix[row + 1][col] == player && boardMatrix[row + 2][col] == player && boardMatrix[row + 3][col] == player && boardMatrix[row + 4][col] == player) {
                            System.out.println("win");
                            winScreen.setVisible(true);
                            if(player == 1){
                                SdownWinSetPiece(row, col);
                            }
                            else{
                                CdownWinSetPiece(row, col);
                            }
                            for (JButton[] jButtons : board) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    jButtons[col1].setEnabled(false);
                                }
                            }
                        }
                    }
                    //check down and to the right
                    if(col <= 10 && row <= 10){
                        if (boardMatrix[row + 1][col + 1] == player && boardMatrix[row + 2][col + 2] == player && boardMatrix[row + 3][col + 3] == player && boardMatrix[row + 4][col + 4] == player) {
                            System.out.println("win");
                            winScreen.setVisible(true);
                            if(player == 1){
                                SdownRightWinSetPiece(row, col);
                            }
                            else{
                                CdownRightWinSetPiece(row,col);
                            }

                            for (JButton[] jButtons : board) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    jButtons[col1].setEnabled(false);
                                }
                            }
                        }
                    }
                    //check down and to the left
                    if(col >= 10 && row <= 10){
                        if (boardMatrix[row + 1][col - 1] == player && boardMatrix[row + 2][col - 2] == player && boardMatrix[row + 3][col - 3] == player && boardMatrix[row + 4][col - 4] == player) {
                            System.out.println("win");
                            winScreen.setVisible(true);
                            if(player == 1){
                                SdownLeftWinSetPiece(row, col);
                            }
                            else{
                                CdownLeftWinSetPiece(row, col);
                            }
                            for (JButton[] jButtons : board) {
                                for (int col1 = 0; col1 < board[0].length; col1++) {
                                    jButtons[col1].setEnabled(false);
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
                    coords = row * 100 + col + "";
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException f) {
                        f.printStackTrace();
                    }
                    setPiece(row, col);

                    checkWin(1);
                    checkWin(2);

                }
            }
        }

    }
}
