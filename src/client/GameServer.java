package client;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import client.gfx.Assets;
import client.gfx.Display;
import client.input.KeyManager;
import client.net.Client;
import client.net.Server;
import client.world.Tile;
import client.world.Tiles;
import client.world.World;

public class GameServer extends Game {

	// Config
	private boolean running;
	public final int fps = 60;
	
	//Net
	private Server server;
	private Thread serverThread;
	
	public void init() {
		Assets.init();
		Tiles.init();
		world = new World(this, worldPath);
		try {
			server = new Server(this, 5656);
		} catch(IOException e) {
			e.printStackTrace();
		}
		serverThread = new Thread(server);
		serverThread.start();
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
				delta--;
			}

		}
	}

	public void tick() {
		server.tick();
		world.tick();
	}

}
