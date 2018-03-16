package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import world.Tile;

public class EntityManager {
	private ArrayList<Player> players;
	private ArrayList<Ghost> ghosts;

	public EntityManager() {
		players = new ArrayList<>();
		ghosts = new ArrayList<>();
		
	}

	public void add(Entity e) {
		if (e instanceof Player) 
			players.add((Player) e);
		if (e instanceof Ghost)
			ghosts.add((Ghost) e);
	}
	


	public void tick(int[][] map) {
		
		for (Player p : players) {
			p.tick();
			if(map[(p.getxPos() + p.width / 2) / Tile.TILE_SIZE][(p.getyPos() + p.height / 2) / Tile.TILE_SIZE] == 0)
					p.makePoint();
			map[(p.getxPos() + p.width / 2) / Tile.TILE_SIZE][(p.getyPos() + p.height / 2) / Tile.TILE_SIZE] = 2;

			for (Ghost g : ghosts)
				if (p.getCollisionBounds().intersects(g.getCollisionBounds())) 
					p.hurt();
				
		}
		for (Ghost g : ghosts)
			g.tick();
	}

	public void render(Graphics g) {
		for (Player p : players) {
			if(p.getHealth()>=0) {
				p.render(g);
			}
			
		}
		for (Ghost gh : ghosts)
			gh.render(g);
	}

	public int getNumPlayers() { return players.size(); }
	
}
