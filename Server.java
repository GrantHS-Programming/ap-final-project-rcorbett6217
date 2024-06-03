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


    public void setPiece(int row, int col){
        if (boardMatrix[row][col] != 2) {
            board[row][col].removeActionListener(this);
            board[row][col].setIcon(new ColorIconRound(40, Color.BLACK));
            boardMatrix[row][col] = 1;
        }
    }

    public void setOppPiece(int row, int col){
        if (row < 15 && col < 15) {
            if (boardMatrix[row][col] != 1) {
                board[row][col].setIcon(new ColorIconRound(40, Color.WHITE));
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
                    outCoords = getCoords();
                    out.writeUTF(outCoords + "@" + turn);
                    inputData = in.readUTF();
                    String[] inputDataArray = inputData.split("@", 0);
                    int coords = Integer.parseInt(inputDataArray[0]);
                    turn = Boolean.parseBoolean(inputDataArray[1]);
                    if(coords != -1) {
                        int row = coords / 100;
                        int col = coords % 100;
                        System.out.println(row + ", " + col);
                        setOppPiece(row, col);
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

