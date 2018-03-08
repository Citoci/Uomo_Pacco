package client.world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import client.Game;
import client.GameServer;
import client.entities.EntityManager;
import client.entities.Ghost;
import client.entities.Player;

public class World {
	
	private Game game;

	private int width, height, xPlayerSpawn, yPlayerSpawn, numGhosts, xGhostSpawn[], yGhostSpawn[];
	private int[][] map;

	private EntityManager entities;

	/**
	 * Costruttore del world, viene chiamato il metodo loadWorld e vengono aggiunte all'arrayList delle entity tutte le entità che servono:
	 * - 1 player
	 * - n ghost, dipendono dalla mappa scelta possono essere 2, 3 o 4
	 * 
	 * @param path la stringa che specifica che mappa caricare
	 */
	public World(Game game, String path) {
		this.game = game;
		loadWorld(path);

		entities = new EntityManager();
//		entities.add(new Player(game, xPlayerSpawn * Tile.TILE_SIZE, yPlayerSpawn * Tile.TILE_SIZE));
		for (int i = 0; i < numGhosts; i++)
			entities.add(new Ghost(game, xGhostSpawn[i] * Tile.TILE_SIZE, yGhostSpawn[i] * Tile.TILE_SIZE, i));
	}

	/**
	 * Il metodo serve per verificare se il giocatore ha vinto o meno, per fare ciò cicla la matrice
	 * contenente il world e verifica che in ogni casella sia stata presa la pallina, se la casella ha 
	 * id 0 vuol dire che vi è ancora presente la pallina e il gioco prosegue. 
	 * In caso non vi siano più palline il gioco termina e il giocatore vince.  
	 * 
	 * @return win valore boolean che indica se il giocatore ha vinto o meno
	 */
	public boolean checkWin() {
		boolean win = true;
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (map[x][y] == 0)
					win = false;
		return win;
	}

	/**
	 * E' il metodo che si fa carico di aggiornare tutte le variabili della classe:
	 * - aggiorna la posizione delle entities
	 * - verifica che il giocatore  abbia vinto e in tal caso ferma il gioco 
	 */
	public void tick() {
		entities.tick(map);

		if (checkWin()) {
			JOptionPane.showMessageDialog(null, "Hai vinto!!");
//			game.stop();
		}
	}

	/**
	 * E' il metodo che si occupa della renderizzazione degli elementi richiamati dalla classe:
	 * - le caselle 
	 * - le entities
	 * 
	 * @param g
	 */
	public void render(Graphics g) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				getTileAt(x, y).render(g, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);

		entities.render(g);
	}


	public Tile getTileAt(int x, int y) {
		Tile t = Tiles.allTiles[map[x][y]];
		if (t == null)
			return Tiles.allTiles[0];
		return t;
	}

	/**
	 * Il metodo ha il compito di caricare il mondo scelto leggendo da file txt tutti i valori necessari
	 * alla sua creazione:
	 * - grandezza matrice
	 * - dove viene creato il player
	 * - numero di fantasmi
	 * - dove vengono creati i fantasmi
	 * 
	 * Crea la matrice della mappa
	 * 
	 * @param path stringa del file txt da caricare
	 */
	private void loadWorld(String path) {
		String worldString = readFileAsString(path);
		String tokens[] = worldString.split("\\s+");

		width = Integer.parseInt(tokens[0]);
		height = Integer.parseInt(tokens[1]);
		xPlayerSpawn = Integer.parseInt(tokens[2]);
		yPlayerSpawn = Integer.parseInt(tokens[3]);
		numGhosts = Integer.parseInt(tokens[4]);
		xGhostSpawn = new int[numGhosts];
		yGhostSpawn = new int[numGhosts];
		for (int i = 0; i < numGhosts; i++) {
			xGhostSpawn[i] = Integer.parseInt(tokens[5 + i * 2]);
			yGhostSpawn[i] = Integer.parseInt(tokens[6 + i * 2]);
		}

		map = new int[width][height];

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				map[x][y] = Integer.parseInt(tokens[y * width + x + 5 + 2 * numGhosts]);
		

	}

	/**
	 * Metodo per la lettura da file txt come fossa una stringa
	 * 
	 * @param path stringa del txt da leggere
	 * @return stringBuilder come stringa 
	 */
	private String readFileAsString(String path) {

		StringBuilder stringBuilder = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null)
				stringBuilder.append(line + "\n");
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}
	
	public String toString() {
		String s = width+" "+height+" ";
		for(int y=0; y<height; y++) 
			for(int x=0; x<width; x++)
				s += map[x][y];
		return s + " " + entities.toString();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPlayerSpawnX() {
		return xPlayerSpawn;
	}

	public int getPlayerSpawnY() {
		return yPlayerSpawn;
	}

	public EntityManager getEntities() {
		return entities;
	}
	
	public void setMap(int[][] map) {
		this.map = map;
		this.width = map.length;
		this.height = map[0].length;
	}
	
	public Game getGame() {
		return game;
	}

}
