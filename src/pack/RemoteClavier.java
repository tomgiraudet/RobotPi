package pack; 
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class RemoteClavier implements KeyListener {
	
	private Remote rm;
	
	public RemoteClavier(Remote _rm){
		this.rm = _rm;
	}
	
	public void keyPressed(KeyEvent event) {
		System.out.print("Key pressed \n");
		int direction = event.getKeyCode();
		try {
			rm.getOs().write(direction);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

}
