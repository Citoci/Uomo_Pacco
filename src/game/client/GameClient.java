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
	
	// Rete
	private Client client;
	
	public GameClient(String title) {
		super(title);
	}
	
	@Override
	protected void init() {
		Assets.init();
		
		display = new Display(getTitle(), 400, 200);		
		
		keyManager = new KeyManager();
		display.addKeyListener(keyManager);
		
		try {
			client = new Client(keyManager);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void tick() {
		try {
			client.tick();
		} catch (IOException e) { 
			e.printStackTrace();
			stop();
		}
	}
	
	public void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Pulisce lo schermo
		g.fillRect(0, 0, display.getWidth(), display.getHeight());

		// Renderizza 
		for(int i=0; i<client.getHealth(); i++) 
			g.drawImage(Assets.heart, display.getWidth()-(i+1)*74, 10, 64, 64, null);
		g.drawString("Punti Totali: "+client.getPoints(), 10, 50);

		// Visualizza
		bs.show();
		// Rilascia il Graphics
		g.dispose();
	}
	

}
