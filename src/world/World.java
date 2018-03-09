package world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

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
		
		loadWorld(path);

		entities = new EntityManager();
		for (int i = 0; i < numGhosts; i++)
			entities.add(new Ghost(game, xGhostSpawn[i] * Tile.TILE_SIZE, yGhostSpawn[i] * Tile.TILE_SIZE, i));
	}
	
	public Tile getTileAt(int x, int y) {
		if(x<0 || x>width || y<0 || y>height)
			return Tiles.allTiles[0];
		Tile t = Tiles.allTiles[map[x][y]];
		if (t == null)
			return Tiles.allTiles[0];
		return t;
	}
	
	public Player createPlayer(int id, String name) {
		Player p = new Player(game, id, name, xPlayerSpawn * Tile.TILE_SIZE, yPlayerSpawn * Tile.TILE_SIZE);
		entities.add(p);
		return p;
	}

	public boolean checkWin() {
		boolean win = true;
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				if (map[x][y] == 0)
					win = false;
		return win;
	}

	public void tick() {
		entities.tick(map);

		if (checkWin()) {
			JOptionPane.showMessageDialog(null, "VITTORIA!!");
			game.stop();
		}
		if (entities.getNumPlayers() == 0) {
//			JOptionPane.showMessageDialog(null, "SCONFITTA!! :(");
//			game.stop();
		}
	}

	public void render(Graphics g) {
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				getTileAt(x, y).render(g, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE);
		entities.render(g);
	}	

	private void loadWorld(String path) {
		String worldString = readFileAsString(path);
		String tokens[] = worldString.split("\\s+");

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

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				map[x][y] = Integer.parseInt(tokens[y * width + x + 6 + 2 * numGhosts]);
	}

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
