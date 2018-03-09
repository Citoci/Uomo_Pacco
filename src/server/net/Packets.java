package server.net;

public class Packets {

	public static class KeysPacket {
		private boolean up, down, left, right;

		public KeysPacket(boolean up, boolean down, boolean left, boolean right) {
			this.up = up;
			this.down = down;
			this.left = left;
			this.right = right;
		}

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
	
	public static class PlayerPacket {
		int health;
		
		
	}

}
