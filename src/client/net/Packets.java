package client.net;

import java.util.Scanner;

import client.entities.Ghost;
import client.entities.Player;
import client.world.World;

public class Packets {
	public static class ClientPacket {
		private boolean up, down, left, right;

		public ClientPacket(boolean up, boolean down, boolean left, boolean right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}
		
		public ClientPacket(byte[] bytes) {
			byte b = bytes[0];
			if(b >= 8) {
				up = true;
				b -= 8;
			}
			if(b >= 4) {
				down = true;
				b -= 4;
			}
			if(b >= 2) {
				left = true;
				b -= 2;
			}
			if(b >= 1) 
				right = true;
		}
		
		public byte[] getBytes() {
			byte b = 0;			
			if(up)
				b += 8;
			if(down)
				b += 4;
			if(left)
				b += 2;
			if(right)
				b += 1;		
			return new byte[] {b};
		}

		public boolean up() { return up; }
		public boolean down() { return down; }
		public boolean left() { return left; }
		public boolean right() { return right; }
		
	}
	
	public static class ServerPacket{
		
		private World world;
		
		public ServerPacket(World world) {
			this.world = world;
		}
		
		public ServerPacket(byte[]  bytes) {
			String s = new String(bytes);
			Scanner sc = new Scanner(s);

			int width = sc.nextInt();
			int height = sc.nextInt();
			byte[] byteMap = sc.next().getBytes();
			int[][] map = new int[width][height];
			for(int i=0; i<width; i++)
				for(int j=0; j<height; j++)
					map[i][j] = byteMap[i*height+j] - 0x30;
			
			world.setMap(map);
			
			int numPlayers = sc.nextInt();
			for(int i=0; i<numPlayers; i++) {
				String name = sc.next();
				int xPos = sc.nextInt();
				int yPos = sc.nextInt();
				int health = sc.nextInt();
				int points = sc.nextInt();
				world.getEntities().add(new Player(name, xPos, yPos, health, points));
			}
			
			int numGhosts = sc.nextInt();
			for (int i=0; i<numGhosts; i++) {
				int xPos = sc.nextInt();
				int yPos = sc.nextInt();
				world.getEntities().add(new Ghost(xPos, yPos, i));
			}
			
			sc.close();
		}
		
		public byte[] getBytes() {
			return world.toString().getBytes();
		}
		
		public World getWorld() {
			return world;
		}
		
	}
}
