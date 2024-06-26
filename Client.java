
// A Java program for a Client
import java.awt.*;
import java.io.*;
import java.net.*;

public class Client extends Gomoku{
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private boolean turn = true;


    public static final Color TealPiece = new Color(24,188,156);

    public void SrightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row][col + i].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
    }
    public void CrightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row][col + i].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
    }
    public void SdownWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row + i][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
    }
    public void CdownWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row + i][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
    }
    public void SdownRightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row + i][col + i].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
    }
    public void CdownRightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row + i][col + i].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
    }
    public void SdownLeftWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row + i][col - i].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
    }
    public void CdownLeftWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row + i][col - i].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
    }
    public void setPiece(int row, int col){
        if(!turn) {
            if (boardMatrix[row][col] != 1) {
                board[row][col].removeActionListener(this);
                board[row][col].setIcon(new ColorIconRound(40, TealPiece));
                boardMatrix[row][col] = 2;
                turn = true;
                try {
                    out.writeUTF(row * 100 + col + "@" + turn);
                    System.out.println("setPiece DATA SENT OUT: " + turn);
                } catch (IOException g){
                    System.out.println("fail");
                }
            }
        }
    }
    public void setOppPiece(int row, int col){
        System.out.println("OPPPIECE TEST");
        if (row < 15 && col < 15) {
            //System.out.println("------------> BOUNDS IF");
            if (boardMatrix[row][col] != 2) {
                board[row][col].setIcon(new ColorIconRound(40, Color.BLACK));
                boardMatrix[row][col] = 1;
                board[row][col].removeActionListener(this);
            }
        }
    }

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            // takes input from terminal
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            // sends output to the socket
            out = new DataOutputStream(
                    socket.getOutputStream());


        }
        catch (UnknownHostException u) {
            System.out.println(u);
            return;
        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

        // string to read message from input
        String newCoords = "-1";
        String inputData;

        // keep reading until "Over" is input

        while (!newCoords.equals("2000")) {

            try {

                if(turn) {
                    System.out.println("---------------> TURN TRUE START: " + turn);
                    inputData = in.readUTF();
                    System.out.println("Turn inputData READ");
                    String[] inputDataArray = inputData.split("@", 0);
                    int coords = Integer.parseInt(inputDataArray[0]);
                    turn = Boolean.parseBoolean(inputDataArray[1]);
                    int row = coords / 100;
                    int col = coords % 100;
                    System.out.println(row + ", " + col);
                    System.out.println(turn);
                    setOppPiece(row, col);
                    checkWin(1);
                    System.out.println("--------------> TURN TRUE END: " + turn);
                }
                if(!turn) {
                    //System.out.println("----------> NOT TURN");
                    //newCoords = getCoords();

                    //out.writeUTF(newCoords + "@" + turn);
                    System.out.print("");
                }


            }
            catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }


    public static void main(String args[])
    {
        Client client = new Client("10.176.56.23", 5000);
    }
}
