package world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.EntityManager;
import entities.Ghost;
import entities.Player;
import game.server.GameServer;

public class World {	
	private GameServer game;

	private int width, height, maxNumPlayers, xPlayerSpawn, yPlayerSpawn, numGhosts, xGhostSpawn[], yGhostSpawn[];
	private int[][] map;

	private EntityManager entities;

	public World(GameServer game, String path) {
		this.game = game;
		
		loadWorld(path); // Carica il mondo da file

		entities = new EntityManager();
		for (int i = 0; i < numGhosts; i++) // Crea subito i fantasmini
			entities.add(new Ghost(game, xGhostSpawn[i] * Tile.TILE_SIZE, yGhostSpawn[i] * Tile.TILE_SIZE, i));
	}
	
	public Tile getTileAt(int x, int y) {
		if(x<0 || x>width || y<0 || y>height) // se le coordinate sforano la mappa, ritorna una casella bianca
			return Tiles.allTiles[0];
		Tile t = Tiles.allTiles[map[x][y]]; // prova a prendere la casella giusta
		if (t == null)
			return Tiles.allTiles[0]; // se non esiste ritorna una casella bianca
		return t;
	}
	
	/**
	 * Crea un nuovo giocatore nel mondo e lo aggiunge al manager di entity
	 * @param id id del giocatore
	 * @param name nome del giocatore
	 * @return riferimento al giocatore creato
	 */
	public Player createPlayer(int id, String name) {
		Player p = new Player(game, id, name, xPlayerSpawn * Tile.TILE_SIZE, yPlayerSpawn * Tile.TILE_SIZE);
		entities.add(p);
		return p;
	}

	/**
	 * Controlla se in tutta la mappa ci sono delle tile con la moneta
	 * @return true se non ce ne sono, false se ce n'è almeno una
	 */
	public boolean checkWin() {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (map[x][y] == 0)
					return false;
		return true;
	}

	public void tick() {
		if(entities.getNumPlayers()<1)
			return;		
		entities.tick(map);

		if (checkWin()) {
//			JOptionPane.showMessageDialog(null, "VITTORIA!!");
			game.printStatus("WIN!", entities.getNumPlayers(), entities.getPlayers());
			game.stop();
		}
		
		boolean lost = true;
		for(Player p: entities.getPlayers()) 
			if(p.isAlive())
				lost = false;
		if(lost) {
//			JOptionPane.showMessageDialog(null, "SCONFITTA!! :(");
			game.printStatus("GAME OVER", entities.getNumPlayers(), entities.getPlayers());
			game.stop();
		}
	}

	public void render(Graphics g) {
		
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				getTileAt(x, y).render(g, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE); // prima renderizzo le tiles
		entities.render(g); // poi le entities
	}	

	/**
	 * Carica e istanzia il mondo da un file
	 * @param path il percorso del file
	 */
	private void loadWorld(String path) {
		String worldString = readFileAsString(path); // legge il file come una stringa
		String tokens[] = worldString.split("\\s+"); // splitta in tanti tokens

		width = Integer.parseInt(tokens[0]);
		height = Integer.parseInt(tokens[1]);
		maxNumPlayers = Integer.parseInt(tokens[2]);
		xPlayerSpawn = Integer.parseInt(tokens[3]);
		yPlayerSpawn = Integer.parseInt(tokens[4]);
		numGhosts = Integer.parseInt(tokens[5]);
		xGhostSpawn = new int[numGhosts];
		yGhostSpawn = new int[numGhosts];
		for (int i = 0; i < numGhosts; i++) { 
			xGhostSpawn[i] = Integer.parseInt(tokens[6 + i * 2]);
			yGhostSpawn[i] = Integer.parseInt(tokens[7 + i * 2]);
		}

		map = new int[width][height];

		// legge la mappa
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				map[x][y] = Integer.parseInt(tokens[y * width + x + 6 + 2 * numGhosts]); 
	}

	/**
	 * Legge un file e lo trasforma in una stringa di testo
	 * @param path il percorso del file
	 * @return la stringa creata
	 */
	private String readFileAsString(String path) {
		StringBuilder stringBuilder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null)
				stringBuilder.append(line + "\n");
			br.close();
		} catch (IOException e) { e.printStackTrace(); }

		return stringBuilder.toString();
	}

	// Getters & Setters
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getMaxNumPlayers() { return maxNumPlayers; }
	public int getPlayerSpawnX() { return xPlayerSpawn; }
	public int getPlayerSpawnY() { return yPlayerSpawn; }

	public EntityManager getEntities() { return entities; }

}
