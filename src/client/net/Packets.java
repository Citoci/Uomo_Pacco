package client.net;

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
			if(b/8 != 0) {
				up = true;
				b-=8;
			}
			if(b/4 != 0) {
				down = true;
				b-=4;
			}
			if(b/2 != 0) {
				left = true;
				b-=2;
			}
			if(b != 0) 
				right = true;
				
		}
		
		public byte[] getBytes() {
			byte[] bytes = new byte[1];
			if(up)
				bytes[0]+=8;
			if(down)
				bytes[0]+=4;
			if(left)
				bytes[0]+=2;
			if(right)
				bytes[0]+=1;
			return bytes;
		}

		public boolean up() {
			return up;
		}

		public boolean down() {
			return down;
		}

		public boolean left() {
			return left;
		}

		public boolean right() {
			return right;
		}	
		
		
	}
	
	public static class ServerPacket {
		
	}
	

}
