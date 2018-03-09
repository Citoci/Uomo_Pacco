package game.client;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import game.Game;
import gfx.Assets;
import gfx.Display;
import input.KeyManager;
import net.Client;

public class GameClient extends Game {
	// Input
	private KeyManager keyManager;
	
	// Net
	private Client client;
	
	public GameClient(String title) {
		super(title);
		display = new Display(title, 400, 200);		
		
		keyManager = new KeyManager();
		display.addKeyListener(keyManager);
		
		try {
			client = new Client(keyManager, "localhost", 5656);
		} catch (IOException e) { e.printStackTrace(); }
		
	}
	
	@Override
	protected void init() {
		
	}
	
	public void tick() {
		try {
			client.tick();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Clear Screen
		g.clearRect(0, 0, display.getWidth(), display.getHeight());

		// Rendering
		for(int i=0; i<client.getHealth(); i++) {
			g.drawImage(Assets.ball, i*64, 0, 64, 64, null);
			g.fillOval(i*64, 0, 64, 64);
			
		}

		// Showing
		bs.show();
		// Releasing Graphics
		g.dispose();

	}
	

}
