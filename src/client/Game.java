package client;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import client.gfx.Assets;
import client.gfx.Display;
import client.input.KeyManager;
import client.world.Tile;
import client.world.Tiles;
import client.world.World;

public class Game implements Runnable {	

	// Global Game
	public static Game game;	
	
	// Config
	private boolean running;
	public final int fps = 60;
	public final double zoom = 1.5;
	
	// Thread
	private Thread gameThread;	
	
	// GFX
	private String title;
	private Display display;
	
	// Input
	private KeyManager keyManager;
	
	// Net
//	private Client client;
	
	// World
	private World world;
	private final String worldPath = "res/worlds/world2.txt";
	
	
	public Game(String title) {
		this.title = title;
	}

	public void init() {
		Assets.init();
		Tiles.init();

		world = new World(worldPath);	
		
		display = new Display(title, world.getWidth()*Tile.TILE_SIZE, world.getHeight()*Tile.TILE_SIZE+28);
		
		keyManager = new KeyManager();
		display.addKeyListener(keyManager);
	}
	
	@Override
	public void run() {
		init();		
		
		double delta=0;
		long now, lastTime = System.nanoTime();
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime)*fps/10e8;
			lastTime = now;
			
			if(delta >= 1) {
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
		if(bs == null) {
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
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		gameThread = new Thread(this, title);
		gameThread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		gameThread.interrupt();
	}
	
	// Getters and Setters
	public KeyManager getKeyManager() { return keyManager; }
	
	public World getWorld() { return world; }
	
	// Launcher
	public static void main(String[] args) {
		game = new Game("Pacman");
		game.start();
	}
}
