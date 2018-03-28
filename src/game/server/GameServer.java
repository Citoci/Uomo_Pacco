package game.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;

import entities.Player;
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

	// Rete
	private Server server;

	// Mondo
	private World world;
	private final String worldPath = "res/worlds/world3.txt";

	public GameServer(String title) {
		super(title);
	}

	@Override
	public void init() {
		// Inizializza risorse e tiles
		Assets.init();
		Tiles.init(ZOOM);

		// Crea il mondo
		world = new World(this, worldPath);

		// Crea il Display
		display = new Display(getTitle(), world.getWidth() * Tile.TILE_SIZE, world.getHeight() * Tile.TILE_SIZE);

		// Crea e avvia il server
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
		if(!isRunning()) 
			;//return;
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Pulisce lo schermo
		g.fillRect(0, 0, display.getWidth(), display.getHeight());

		// Renderizza 
		world.render(g);

		// Visualizza
		bs.show();
		// Rilascia il Graphics
		g.dispose();
		
	}
	
	public void printStatus(String status, int Nplayer, ArrayList<Player> players) {
		BufferStrategy bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		// Pulisce lo schermo
		g.fillRect(0, 0, display.getWidth(), display.getHeight());
		
		g.setFont(new Font ("Game Over", 1, 50)); 
		g.setColor(Color.WHITE);
		g.drawString(status, display.getWidth()/2-80, display.getHeight()/3);
		
		for(int i=0; i<Nplayer; i++) {
			
			int point = players.get(i).getPoints()*100;
			g.drawString(players.get(i).getName()+"................"+point, display.getWidth()/2-100, display.getHeight()/2+i*40);
			
		}
		
		bs.show();
		g.dispose();
		
	}
	
	
	// Getters and Setters	
	public World getWorld() { return world; }

}
