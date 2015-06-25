import java.io.*;
import java.net.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Remote extends JPanel implements ActionListener, KeyListener {
	
	// attributes
	KeyListener listener;
	private Socket remote = null;
	protected PrintWriter s_out = null;
	
	// config
	private int port = 4444;
	private String serveurName = "192.168.1.54";

	
	// Constructor
	public Remote(){
		//listener = new RemoteClavierListener();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false); // pas de tab/shift
	}
	
	
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
	
	// Initialization of the KeyboardListener
	public void initKeyboardListerner(){
		JFrame frame = new JFrame("RobotPi - Remote");
		frame.add(this);
		frame.setSize(200, 75);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	
	public void actionPerformed(ActionEvent e){
		s_out.println("Test");
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_UP:
			System.out.println("go straight sent");
			s_out.println("up");
		break;
		
		case KeyEvent.VK_LEFT:
			System.out.println("go left sent");
			s_out.println("left");
		break;
		
		case KeyEvent.VK_RIGHT:
			System.out.println("go right");
			s_out.println("right");
		break;
		
		default: 
			System.out.println("Stop sent");
			s_out.println("Stop");
		break;
		}
	}

	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){
		System.out.println("Stop sent");
		s_out.println("Stop");
	}
	
	
	// Main method
    public static void main(String[] args) throws InterruptedException {

    	// initialisation
    	Remote rem = new Remote();
    	rem.connectToServeur();
    	rem.initPrintWriter();
    	rem.initKeyboardListerner();
    	
    	
    	// sending message
    	
    	
    	/*String test;
    	test = "ledGauche";
    	rem.sendInfo(test);
    	Thread.sleep(2000);
    	
    	test = "azerty";
    	rem.sendInfo(test);
    	Thread.sleep(2000);*/
    	
    	Thread.sleep(100000);
   	
    	// ending
    	rem.end();
    	
    	
    }
  

		
	  /*
    public class RemoteClavierListener implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}*/
}




