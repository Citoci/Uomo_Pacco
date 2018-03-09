package server.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import server.Game;
import server.entities.Player;

public class User {
	
	private Game game;
	
	private int id;
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private Player player;

	public User(Game game, int id, Socket socket) throws IOException {
		this.game = game;
		this.id = id;
		
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		player = game.getWorld().createPlayer(id);		
	}
	
	public void tick() throws IOException {
		KeysPacket p = new KeysPacket(in.readByte());
		player.getInput(p.up(), p.down(), p.left(), p.right());
	}
	
	public void close() {
		try {
			socket.close();
			in.close();
			out.close();
		} catch (IOException e) { e.printStackTrace(); }	
		player.setHealth(0);
	}

}
