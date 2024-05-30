
// A Java program for a Client
import java.awt.*;
import java.io.*;
import java.net.*;

public class Client extends Gomoku{
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private boolean turn = false;

    public void setPiece(int row, int col){
        if(turn) {
            if (boardMatrix[row][col] != 1) {
                turn = false;
                board[row][col].setIcon(new ColorIconRound(40, Color.WHITE));
                boardMatrix[row][col] = 2;

            }
        }
    }
    public void setOppPiece(int row, int col){
        if(row < 15 && col < 15) {
            if(boardMatrix[row][col] != 2) {
                board[row][col].setIcon(new ColorIconRound(40, Color.BLACK));
                boardMatrix[row][col] = 1;
                turn = true;
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
        String coordsString;

        // keep reading until "Over" is input

        while (!newCoords.equals("2000")) {

            try {
                newCoords = getCoords();
                out.writeUTF(newCoords);
                coordsString = in.readUTF();
                int coords = Integer.parseInt(coordsString);
                int row = coords / 100;
                int col = coords % 100;
                System.out.println(row + ", " + col);
                setOppPiece(row, col);


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
