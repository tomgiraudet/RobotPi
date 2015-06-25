import java.io.*;
import java.net.*;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Serveur {
	
	private ServerSocket echoServer = null;
    private Socket clientSocket = null;
    private String line;
    private PrintStream out = null;
    private BufferedReader in = null;
    private GpioPinDigitalOutput left = null;
    private GpioPinDigitalOutput right = null;
    
    
    // Config
    private int port = 4444;
    
    
    // Constructor
    public Serveur(){}
    
    // Initialization of ServerSocket
    public void init(){
    	 try {
             echoServer = new ServerSocket(port);
             clientSocket = echoServer.accept();
             out = new PrintStream(clientSocket.getOutputStream());
             out.flush();
             in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          }
          catch (IOException e) {
             System.out.println(e);           
          }
    }
    
    
    // Initialization of Leds
    public void initGpio(){
    	final GpioController gpio = GpioFactory.getInstance();
    	left = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "PinLED", PinState.LOW);
    	right = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.LOW);
    }
    
    
    // Receiving data
    public String receivingData(){
    	String data;
    	try {
			while ((data = in.readLine()) != null) 
			{
			    return data;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
    }
    
    
    // MotorManagment
    public void MotorManagment(String _Data){
    	switch (_Data) {
		case "left" :
			left.toggle();
		break;
		
		case "right" :
			right.toggle();
		break;
		
		case "Stop" :
			left.low();
			right.low();
		break;
		
		default:
			//System.out.println("Stop");
		break;
	}
    }
    
    
    // Clean closing of socket
 	public void end(){
 		 try
         {
             in.close();
             clientSocket.close();
         }   
         catch(IOException ioException)
         {
             System.err.println("Unable to close. IOexception");
         }
 	} 
    
    
 	
 	// Main method
    public static void main(String args[]) throws InterruptedException {

    	// Initialisation : 
    	Serveur serv = new Serveur();
    	serv.init();
    	serv.initGpio();

    	// Temps de la simulation : #Gitan #AModifier
    	int i = 0;
    	String data;
    	
    	while(i<10000){
    		data = serv.receivingData();
    		System.out.println("Touche lue : "+data);
    		serv.MotorManagment(data);
    		i++;
    	}
    	
    	// Ending :
        serv.end();
              
    }
}


