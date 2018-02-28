package client.net;

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
			// 
		}
		
		public byte[] getBytes() {
			return world.toString().getBytes();
		}
		
		
		
		
	}
}
