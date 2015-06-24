import java.io.*;
import java.net.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Container;


public class Remote {
	

    public static void main(String[] args) {

    	// Initialisation :
        Socket remote = null;  
        DataOutputStream os = null;
        DataInputStream is = null;
        
        // Configuration : 
        int port = 4444;
        String serveurName = "localhost";
        
        // Opening the Socket :
        try {
            remote = new Socket(serveurName, port);
            os = new DataOutputStream(remote.getOutputStream());
            is = new DataInputStream(remote.getInputStream());
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }

        // Sending data :
    if (remote != null && os != null && is != null) {
            try {  	
            	
            JFrame frame = new JFrame("Key Listener");
          	Container contentPane = frame.getContentPane();


            KeyListener listener = new KeyListener() {
            		@Override
            		public void keyPressed(KeyEvent event) {
            			System.out.print("Key pressed \n");
            			int direction = event.getKeyCode();
            			setDirToServeur(direction);
            			//os.writeInt(direction);
            		}

            		@Override
            		public void keyReleased(KeyEvent event) {
            		}

            		@Override
            		public void keyTyped(KeyEvent event) {
            		}
            		
            		private final void setDirToServeur(int dir){
                    	os.write(dir);
                    }

             };

             JTextField textField = new JTextField();
             textField.addKeyListener(listener);
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
        }
    }           
}