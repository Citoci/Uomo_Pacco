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
		if (e instanceof Player)
			players.add((Player) e);
		if (e instanceof Ghost)
			ghosts.add((Ghost) e);
	}

	private void resetPos() {
		for (Player p : players)
			p.resetPos();
	}

	public void tick(int[][] map) {
		for (Player p : players) {
			p.tick();
			map[(p.getxPos() + p.width / 2) / Tile.TILE_SIZE][(p.getyPos() + p.height / 2) / Tile.TILE_SIZE] = 2;

			for (Ghost g : ghosts)
				if (p.getCollisionBounds().intersects(g.getCollisionBounds())) {
					p.hurt();
					resetPos();
				}
		}
		for (Ghost g : ghosts)
			g.tick();
	}

	public void render(Graphics g) {
		for (Player p : players)
			p.render(g);
		for (Ghost gh : ghosts)
			gh.render(g);
	}

	public String toString() {
		String s = "";
		for(Player p: players)
			s += p.getName() + " " + p.getxPos() + " " + p.getyPos() + " " + p.getHealth() + " " + p.getPoints() + " ";
		s += ghosts.size() + " ";
		for(Ghost g: ghosts)
			s += g.getxPos() + " " + g.getyPos() + " ";		
		return s;
	}
	
}
