package client.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import client.world.Tile;

public class EntityManager {
	private ArrayList<Player> players;
	private ArrayList<Ghost> ghosts;
	
	public EntityManager() {
		players = new ArrayList<>();
		ghosts = new ArrayList<>();
	}
	
	
	public void add(Entity e) {
		if(e instanceof Player)
			players.add((Player) e);
		if(e instanceof Ghost)
			ghosts.add((Ghost) e);
	}
	
	private void resetPos() {
		for(Player p: players)
			p.resetPos();
//		for(Ghost g: ghosts)
//			g.resetPos();
	}

	public void tick(boolean[][] visited) {
		for(Player p: players) {
			p.tick();
			visited[(p.getxPos()+p.width/2)/Tile.TILE_SIZE][(p.getyPos()+p.height/2)/Tile.TILE_SIZE] = true;

			for(Ghost g: ghosts)
				if(p.getCollisionBounds().intersects(g.getCollisionBounds())) {
					p.hurt();
					resetPos();
				}
		}
		for(Ghost g: ghosts)
			g.tick();		
	}
	
	public void render(Graphics g) {
		for(Player p: players)
			p.render(g);
		for(Ghost gh: ghosts)
			gh.render(g);
			
	}
	
}
