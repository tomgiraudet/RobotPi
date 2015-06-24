package pack; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class RemoteClavier implements KeyListener {

	
	public void keyPressed(KeyEvent event) {
		System.out.print("Key pressed \n");
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

}
