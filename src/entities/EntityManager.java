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
			
			if(!p.isAlive())
				continue;
			
			switch(map[(p.getxPos() + p.width / 2) / Tile.TILE_SIZE][(p.getyPos() + p.height / 2) / Tile.TILE_SIZE]) {
			case 0:
				break;
			case 3:
			case 4:
			case 5:
			case 6:
				p.makePoint(1);
				break;
			case 7:
				for (Ghost ghost : ghosts)
					ghost.setEatableTime(300);
				break;				
			}
			map[(p.getxPos() + p.width / 2) / Tile.TILE_SIZE][(p.getyPos() + p.height / 2) / Tile.TILE_SIZE] = 2;

			for (Ghost g : ghosts)
				if (p.getCollisionBounds().intersects(g.getCollisionBounds())) 
					if(!g.isEatable() && !g.isEaten())
						p.hurt();
					else {
						g.eat();
						p.makePoint(1);
					}
				
		}
		for (Ghost g : ghosts)
			g.tick();
	}

	public void render(Graphics g) {
		for (Player p : players) 
			if(p.getHealth()>=0) 
				p.render(g);
		
		for (Ghost ghost : ghosts)
			ghost.render(g);
	}

	public int getNumPlayers() { return players.size(); }
	public ArrayList<Player> getPlayers() { return players; }
}
