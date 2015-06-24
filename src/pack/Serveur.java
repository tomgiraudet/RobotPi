package pack;
import java.io.*;
import java.net.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Serveur {
    public static void main(String args[]) {

    	// Initialisation : 
        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
        
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput left = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "PinLED", PinState.LOW);
        final GpioPinDigitalOutput right = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.LOW);
        
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
             left.pulse(100, true);
             right.pulse(100, true);
             System.out.println(line);
             
           }
        }   
    catch (IOException e) {
           System.out.println(e);
        }
    }
}