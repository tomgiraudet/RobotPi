import java.io.*;
import java.net.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Container;



public class Remote {
	
	// attributes
	private DataOutputStream os;
	//private RemoteClavier rc = new RemoteClavier();
	private Socket remote = null;
	private PrintWriter s_out = null;
	
	// config
	private int port = 4444;
	private String serveurName = "192.168.2.10";

	
	// Constructor
	public Remote(){}
	
	
	// Creation and connection of the Socket
	public void connectToServeur(){
		try {
			remote = new Socket(serveurName, port);
			System.out.println("ConnectToServeur : Ok");
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
	}
	
	
	// Initialization of the PrintWriter
	public void initPrintWriter(){
			try {
				s_out = new PrintWriter(remote.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	// Send the string info using s_out
	public void sendInfo(String info){
		s_out.println(info);
	}
	
	
	// Clean closing of socket
	public void end(){
		s_out.close();
		try {
			remote.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	// Main method
    public static void main(String[] args) throws InterruptedException {

    	// initialisation
    	Remote rem = new Remote();
    	rem.connectToServeur();
    	rem.initPrintWriter();
    	
    	// sending message
    	String test = "";
    	test = "ledDroite";
    	rem.sendInfo(test);
    	Thread.sleep(500);
    	
    	test = "ledDroite";
    	rem.sendInfo(test);
    	Thread.sleep(500);
    	
    	test = "ledGauche";
    	rem.sendInfo(test);
    	Thread.sleep(500);
    	
    	test = "azerty";
    	rem.sendInfo(test);
    	Thread.sleep(500);
    	
   	
    	// ending
    	rem.end();
    	
    	
    }
}




/*
        // Sending data :
    if (remote != null && os != null && is != null) {
            try {  	
                     	
            JFrame frame = new JFrame("Key Listener");
          	Container contentPane = frame.getContentPane();


          	 
             JTextField textField = new JTextField();
             textField.addKeyListener(rc);
             contentPane.add(textField, BorderLayout.NORTH);
             frame.pack();
             frame.setVisible(true);	
            	     	
            

                
// keep on reading from/to the socket till we receive the "Ok" from SMTP,
// once we received that then we want to break.
               String responseLine;
                while ((responseLine = is.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                      break;
                    }
                }

           // Close and clean :
                os.close();
                is.close();
                remote.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }*/
