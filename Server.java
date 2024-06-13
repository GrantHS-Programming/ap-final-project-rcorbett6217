// A Java program for a Server
import java.awt.*;
import java.net.*;
import java.io.*;

public class Server extends Gomoku
{
    //initialize socket and input stream
    private Socket		 socket = null;
    private ServerSocket server = null;
    private DataInputStream in	 = null;

    private DataOutputStream out = null;

    private boolean turn = true;

    private boolean SWin = false;

    private boolean CWin = false;

    public static final Color TealPiece = new Color(24,188,156);

    public void SrightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row][col + i].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
        SWin = true;
    }
    public void CrightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row][col + i].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
        CWin = true;
    }
    public void SdownWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row + i][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
        SWin = true;
    }
    public void CdownWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row + i][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
        CWin = true;
    }
    public void SdownRightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row + i][col + i].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
        SWin = true;
    }
    public void CdownRightWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row + i][col + i].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
        CWin = true;
    }
    public void SdownLeftWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        for (int i = 1; i < 5; i++) {
            board[row + i][col - i].setIcon(new ColorIconRoundStar(40, Color.BLACK, TealPiece));
        }
        SWin = true;
    }
    public void CdownLeftWinSetPiece(int row, int col){
        board[row][col].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        for (int i = 1; i < 5; i++) {
            board[row + i][col - i].setIcon(new ColorIconRoundStar(40, TealPiece, Color.BLACK));
        }
        CWin = true;
    }

    public void setPiece(int row, int col){
        //System.out.println("^^^^^^^^^^^^^^^^> TURN: " + turn + "    BOARD: " + boardMatrix[row][col]);
        if(turn) {
            if (boardMatrix[row][col] != 2) {
                board[row][col].removeActionListener(this);
                board[row][col].setIcon(new ColorIconRound(40, Color.BLACK));
                boardMatrix[row][col] = 1;
                turn = false;
                // System.out.println("----------------------------------------> NOTICE ME: " + turn);
                try {
                    out.writeUTF(row * 100 + col + "@" + turn + "@" + SWin);
                    System.out.println("setPiece DATA SENT OUT: " + turn);
                    System.out.println("--------------------------");
                } catch (IOException g){
                    System.out.println("fail");
                }
            }
        }
    }

    public void setOppPiece(int row, int col){
        if (row < 15 && col < 15) {
            if (boardMatrix[row][col] != 1) {
                board[row][col].setIcon(new ColorIconRound(40, TealPiece));
                boardMatrix[row][col] = 2;
                board[row][col].removeActionListener(this);
            }
        }

    }

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(socket.getOutputStream());

            String outCoords = "-1";
            String inputData;

            // reads message from client until "Over" is sent
            while (!outCoords.equals("2000"))
            {
                try
                {

                    if(turn) {
                        outCoords = getCoords();
                        if(turn) {
                            //System.out.println("-----> COORDS: " + outCoords);
                            //out.writeUTF(outCoords + "@" + turn);
                            System.out.print("");
                        }
                    }
                    if(!turn) {
                        inputData = in.readUTF();
                        System.out.println("Client turn DATA READ  ");
                        String[] inputDataArray = inputData.split("@", 0);
                        int coords = Integer.parseInt(inputDataArray[0]);
                        turn = Boolean.parseBoolean(inputDataArray[1]);
                        CWin = Boolean.parseBoolean(inputDataArray[2]);
                        if (coords != -1) {
                            int row = coords / 100;
                            int col = coords % 100;
                            if (turn) {
                                System.out.println(row + ", " + col);
                                System.out.println(turn);
                            }
                            setOppPiece(row, col);
                            checkWin(2);
                        }
                    }



                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}

