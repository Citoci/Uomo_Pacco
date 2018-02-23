package client.world;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import client.Game;
import client.entities.EntityManager;
import client.entities.Ghost;
import client.entities.Player;

public class World {
	
	private int width, height, xPlayerSpawn, yPlayerSpawn, numGhosts, xGhostSpawn[], yGhostSpawn[];
	private int[][] map;
	
	private EntityManager entities;
	
	public World(String path) {
		loadWorld(path);
		
		entities = new EntityManager();
		entities.add(new Player(xPlayerSpawn*Tile.TILE_SIZE, yPlayerSpawn*Tile.TILE_SIZE));
		for(int i=0; i<numGhosts; i++)
			entities.add(new Ghost(xGhostSpawn[i]*Tile.TILE_SIZE, yGhostSpawn[i]*Tile.TILE_SIZE,i));
	}
	
	
	public boolean checkWin() {
		boolean win = true;
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				if(map[x][y]==0)
					win = false;
		return win;
	}
	
	public void tick() {
		entities.tick(map);
		
		if(checkWin()) {
			JOptionPane.showMessageDialog(null, "Hai vinto!!");
			Game.game.stop();
		}
		
	}
	
	public void render(Graphics g) {
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				getTileAt(x, y).render(g, x*Tile.TILE_SIZE, y*Tile.TILE_SIZE);
				
		
		entities.render(g);
	}
	
	public Tile getTileAt(int x, int y) {
		int d = map[x][y];
		Tile t = Tiles.allTiles[d];
		if(t == null)
			return Tiles.allTiles[0];
		return t;
	}
	
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
		for(int i=0; i<numGhosts; i++) {
			xGhostSpawn[i] = Integer.parseInt(tokens[5+i*2]);
			yGhostSpawn[i] = Integer.parseInt(tokens[6+i*2]);
		}
		
		map = new int[width][height];
		
		for(int x=0; x<width; x++) 
			for(int y=0; y<height; y++) 
				map[x][y] = Integer.parseInt(tokens[y*width + x + 5 + 2*numGhosts]);
			
	}	

	private String readFileAsString(String path) {

		StringBuilder stringBuilder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				stringBuilder.append(line+"\n");
			br.close();
		} catch (IOException e) { e.printStackTrace(); }
		
		return stringBuilder.toString();
	}

	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public int getPlayerSpawnX() { return xPlayerSpawn; }
	public int getPlayerSpawnY() { return yPlayerSpawn; }
	
	public EntityManager getEntities() { return entities; }
	
	
}
