package net;

public class Packets {

	// Pacchetto che contiene l'input da tastiera
	public static class KeysPacket {
		private boolean up, down, left, right;

		public KeysPacket(boolean up, boolean down, boolean left, boolean right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}

		// ricostruisce le boolean a partire dal byte
		public KeysPacket(byte b) {
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
		
		// Costruisce un byte (0000xxxx) usando le 4 boolean di input 
		public byte getByte() {
			byte b = 0;			
			if(up) b += 8;
			if(down) b += 4;
			if(left) b += 2;
			if(right) b += 1;		
			return b;
		}

		public boolean up() { return up; }
		public boolean down() { return down; }
		public boolean left() { return left; }
		public boolean right() { return right; }
	}
	
	public static class HPPacket{
		private byte health, points;

		public HPPacket(byte health, byte points) {
			this.health = health;
			this.points = points;
		}
		
		public HPPacket(byte b) {
			points = (byte) (b % 16);
			health = (byte) (b >> 4);
		}
		
		public byte getByte() {
			return (byte) (health*16 + points);
		}
		
		public byte getHealth() { return health; }		
		public byte getPoints() { return points; }	
		
	}
}
