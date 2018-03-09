package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import game.client.GameClient;
import input.KeyManager;
import net.Packets.KeysPacket;

public class Client {
	
	private KeyManager keyManager;

	private Socket socket;
	private String host = "localhost";
	private int port = 5656;

	private DataInputStream in;
	private DataOutputStream out;
	
	private byte health, points;

	public Client(KeyManager keyManager, String host, int port) throws UnknownHostException, IOException {
		this.keyManager = keyManager;
		this.host = host;
		this.port = port;
		socket = new Socket(host, port);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}

	public void tick() throws IOException {
		KeysPacket kP = new KeysPacket(keyManager.up, keyManager.down, keyManager.left, keyManager.right);
		out.write(kP.getByte());
		out.flush();

		health = in.readByte();
		points = in.readByte();
		
	}

	public void close() throws IOException {
		socket.close();
		in.close();
		out.close();
	}
	
	public byte getHealth() { return health; }
	public byte getPoints() { return points; }

}
