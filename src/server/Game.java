package server;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import server.gfx.Assets;
import server.gfx.Display;
import server.net.Server;
import server.world.Tile;
import server.world.Tiles;
import server.world.World;

public class Game implements Runnable {
	// Configs
	private boolean running;
	private final int FPS = 60;
	private final double ZOOM = 1;
	
	// Threads
	private Thread gameThread, serverThread;

	// GFX
	private String title;
	private Display display;

	// Net
	private Server server;

	// World
	private World world;
	private final String worldPath = "res/worlds/world2.txt";

	public Game(String title) {
		this.title = title;
	}

	public void init() {
		// Initializing resources
		Assets.init();
		Tiles.init(ZOOM);

		// Creating the world
		world = new World(this, worldPath);

		// Creating the frame
		display = new Display(title, world.getWidth() * Tile.TILE_SIZE, world.getHeight() * Tile.TILE_SIZE);

		// Creating and starting the server
		try {
			server = new Server(this, world.getMaxNumPlayers());
		} catch (IOException e) {
			System.out.println("Errore creazione server");
			e.printStackTrace(); 
			System.exit(-1);
		}
		serverThread = new Thread(server, "serverThread");
		serverThread.start();
	}

	@Override
	public void run() {
		// Initialize the game first of all
		init();
		
		// Starting the controlled loop
		double delta = 0;
		long now, lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now-lastTime) * FPS / 10e8;
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
		server.tick();
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

	public synchronized void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this, "gameThread");
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) { e.printStackTrace(); }
	}

	// Getters and Setters
	public boolean isRunning() { return running; }
	
	public World getWorld() { return world; }

}
