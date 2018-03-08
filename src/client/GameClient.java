package client;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import client.gfx.Assets;
import client.gfx.Display;
import client.input.KeyManager;
import client.net.Client;
import client.world.Tile;
import client.world.Tiles;
import client.world.World;

public class GameClient extends Game {

	// Config
	private boolean running;
	public final int fps = 60;
	public static final double zoom = 0.5;

	// GFX
	private String title;
	private Display display;

	// Input
	private KeyManager keyManager;

	// Net
	private Client client;
	
	public GameClient(String title) {
		this.title = title;
	}

	public void init() {
		Assets.init();
		Tiles.init();

		world = new World(this, worldPath);

		display = new Display(title, world.getWidth() * Tile.TILE_SIZE, world.getHeight() * Tile.TILE_SIZE);

		keyManager = new KeyManager();
		display.addKeyListener(keyManager);

		// client = new Client();
	}

	@Override
	public void run() {
		init();
		
		running = true;	

		double delta = 0;
		long now, lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) * fps / 10e8;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
	}

	public void tick() {
		world.tick();
	}

	public void render() {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Clear Screen
		g.fillRect(0, 0, display.getWidth(), display.getHeight());

		// Rendering
		world.render(g);

		// Showing
		bs.show();
		// Releasing Graphics
		g.dispose();

	}

	// Getters and Setters
	public KeyManager getKeyManager() {
		return keyManager;
	}

	
}
