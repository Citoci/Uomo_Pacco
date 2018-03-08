package client.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import client.net.Packets.ClientPacket;

public class User {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	public User(Socket socket) throws IOException {
		this.socket = socket;
		
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}

	public void tick() throws IOException {
		byte byteIn = in.readByte();
		ClientPacket cp = new ClientPacket(new byte[] {byteIn});
		
		if(cp.up());
		
	}
	
	
}
