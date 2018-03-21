package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import entities.Player;
import game.server.GameServer;
import net.Packets.KeysPacket;

// Classe usata dal server per comunicare con un client
public class User {
	
	private GameServer game;
	
	private int id;
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	private Player player; // riferimento al suo player 

	public User(GameServer game, int id, Socket socket) throws IOException {
		this.game = game;
		this.id = id;
		
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		String name = in.readUTF(); // Legge subito il nome del giocatore
		player = game.getWorld().createPlayer(id, name); // Crea un giocatore nel mondo e ne salva il riferimento
	}
	
	public void tick() throws IOException {
		KeysPacket kP = new KeysPacket(in.readByte()); // legge il pacchetto del giocatore
		player.getInput(kP.up(), kP.down(), kP.left(), kP.right()); // da l'input al player
		
		// invia risposta con vita e punti del player
		out.writeByte(player.getHealth()); 
		//out.writeByte(player.getPoints());
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
