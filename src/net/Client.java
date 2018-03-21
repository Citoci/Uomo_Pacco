package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import input.KeyManager;
import net.Packets.KeysPacket;


// Classe usata dal client per la comunicazione 
public class Client {

	private Socket socket; 
	private String host = "localhost";
	private int port = 5656;

	private DataInputStream in;
	private DataOutputStream out;
	
	private String name;
	private byte health, points;
	
	private KeyManager keyManager; // riferimento al keyManager da cui leggerà l'input da tastiera

	public Client(KeyManager keyManager) throws UnknownHostException, IOException {
		this.keyManager = keyManager;
		socket = new Socket(host, port);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		name = JOptionPane.showInputDialog("Inserisci il tuo nome"); // Legge il nome da JOptionPane
		out.writeUTF(name); // Invia subito il nome
	}

	public void tick() throws IOException {
		KeysPacket kP = new KeysPacket(keyManager.up, keyManager.down, keyManager.left, keyManager.right); // Cotruisce il pacchetto da inviare in base all'input da tastiera
		out.write(kP.getByte()); // invia il pacchetto

//		// riceve la risposta
//		health = in.readByte();
//		points = in.readByte();
		
		// Se siamo morti dobbiamo essere cancellati
		if(health == 0)
			throw new IOException();
	}

	public void close() throws IOException {
		socket.close();
		in.close();
		out.close();
	}
	
	public byte getHealth() { return health; }
	public byte getPoints() { return points; }

}
