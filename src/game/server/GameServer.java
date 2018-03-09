package game.server;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import game.Game;
import gfx.Assets;
import gfx.Display;
import net.Server;
import world.Tile;
import world.Tiles;
import world.World;

public class GameServer extends Game {
	// Threads
	private Thread serverThread;

	// Net
	private Server server;

	// World
	private World world;
	private final String worldPath = "res/worlds/world2.txt";

	public GameServer(String title) {
		super(title);
	}

	@Override
	public void init() {
		// Initializing resources
		Assets.init();
		Tiles.init(ZOOM);

		// Creating the world
		world = new World(this, worldPath);

		// Creating the frame
		display = new Display(getTitle(), world.getWidth() * Tile.TILE_SIZE, world.getHeight() * Tile.TILE_SIZE);

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

	// Getters and Setters	
	public World getWorld() { return world; }

}
