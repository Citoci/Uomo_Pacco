package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import input.KeyManager;
import net.Packets.KeysPacket;

public class Client {
	
	private KeyManager keyManager;

	private Socket socket;
	private String host = "localhost";
	private int port = 5656;

	private DataInputStream in;
	private DataOutputStream out;
	
	private String name;
	private byte health, points;

	public Client(KeyManager keyManager) throws UnknownHostException, IOException {
		this.keyManager = keyManager;
		socket = new Socket(host, port);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		name = JOptionPane.showInputDialog("Inserisci il tuo nome");
		out.writeUTF(name);
	}

	public void tick() throws IOException {
		KeysPacket kP = new KeysPacket(keyManager.up, keyManager.down, keyManager.left, keyManager.right);
		out.write(kP.getByte());

		health = in.readByte();
		points = in.readByte();
		
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
