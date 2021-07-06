import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class KeyControl implements KeyListener {
	
	private Map map;

	public KeyControl(Map theMap) {
		this.map = theMap;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){
					this.map.game.player.moveUp();
				}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			this.map.game.player.moveDown();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			this.map.game.player.moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			this.map.game.player.moveRight();
		}
		if (e.getKeyCode() == KeyEvent.VK_L) {

			this.map.setTiles(this.map.game.loadSave());
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			this.map.game.saveGame();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
