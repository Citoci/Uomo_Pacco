package client.world;

import java.awt.Color;
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
	private boolean[][] visited;
	
	private EntityManager entities;
	
	public World(String path) {
		loadWorld(path);
		
		entities = new EntityManager();
		entities.add(new Player(xPlayerSpawn*Tile.TILE_SIZE, yPlayerSpawn*Tile.TILE_SIZE));
		for(int i=0; i<numGhosts; i++)
			entities.add(new Ghost(xGhostSpawn[i]*Tile.TILE_SIZE, yGhostSpawn[i]*Tile.TILE_SIZE,i+1));
	}
	
	
	public boolean checkWin() {
		boolean win = true;
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				if(!visited[x][y])
					win = false;
		return win;
	}
	
	public void tick() {
		entities.tick(visited);
		
		if(checkWin()) {
			JOptionPane.showMessageDialog(null, "Hai vinto!!");
			Game.game.stop();
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++) {
				getTileAt(x, y).render(g, x*Tile.TILE_SIZE, y*Tile.TILE_SIZE);
				if(!visited[x][y])
					g.fillOval(x*Tile.TILE_SIZE+Tile.TILE_SIZE/3, y*Tile.TILE_SIZE+Tile.TILE_SIZE/3, Tile.TILE_SIZE/3, Tile.TILE_SIZE/3);
			}
		g.setColor(Color.DARK_GRAY);
		
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
		visited = new boolean[width][height];
		
		for(int x=0; x<width; x++) 
			for(int y=0; y<height; y++) {
				map[x][y] = Integer.parseInt(tokens[y*width + x + 5 + 2*numGhosts]);
				if(map[x][y]==1)
					visited[x][y] = true;
			}
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
