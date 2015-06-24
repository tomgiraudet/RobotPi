import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String args[]) {

    	// Initialisation : 
        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
        
        // Configuration : 
        int port = 4444;
        
        // Opening the Socket
        try {
           echoServer = new ServerSocket(port);
        }
        catch (IOException e) {
           System.out.println(e);
        }   
        
        // Open input and output streams
        try {
           clientSocket = echoServer.accept();
           is = new DataInputStream(clientSocket.getInputStream());
           os = new PrintStream(clientSocket.getOutputStream());
           
           
           // As long as we receive data, echo that data back to the client.
           while (true) {
             line = is.readLine();
             //os.println("line "+i+" recieved");
             //System.out.println(line);
           }
        }   
    catch (IOException e) {
           System.out.println(e);
        }
    }
}