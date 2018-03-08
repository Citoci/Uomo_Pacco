package client.net;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import client.GameServer;
import client.input.KeyManager;
import client.net.Packets.ClientPacket;

public class Client {
	
//	private Socket socket;
//	private String host = "localhost";
//	private int port = 5656;
//	
//	private DataInputStream in;
//	private DataOutputStream out;
		
//	public Client(String host, int port) throws UnknownHostException, IOException {
//		this.host = host;
//		this.port = port;
//		socket = new Socket(host, port);
//		in = new DataInputStream(socket.getInputStream());
//		out = new DataOutputStream(socket.getOutputStream());
//	}
//	
//	public void tick() throws IOException {
//		KeyManager keyManager = Game.game.getKeyManager();
//		ClientPacket cP = new ClientPacket(keyManager.up, keyManager.down, keyManager.left, keyManager.right);
//		out.write(cP.getBytes());
//		out.flush();
//		
//	}
//
//	public void close() throws IOException {
//		socket.close();
//		in.close();
//		out.close();
//	}
//	
//}

	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Connecting... ");
		socket = new Socket("localhost", 5656);
		System.out.println("Connection successful.");
		in = new DataInputStream(socket.getInputStream());
		
		out = new DataOutputStream(socket.getOutputStream());
		
		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter your name and press enter: ");
//		String name = sc.nextLine();
//		System.out.println("같같같같같같같같같같같같같같같같같같같같같같같같같같같같�");
//	
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		
	
//		while(true) {
//			String sendMessage = sc.nextLine();
//			out.writeUTF(sendMessage);
//		}
			
	}
	
}

class Input implements Runnable{
	DataInputStream in;
	
	
	public Input(DataInputStream in) {
		this.in = in;
	}
	
	
	
	@Override
	public void run() {
		
		byte[] b = new byte[500];
		while(true) {
			try {
				in.read(b);
				
				
				System.out.println(new String(b));
					
			} catch (IOException e) {
				//e.printStackTrace();
				
			}
			
		}
	}
	
	
}
	
//	private Socket client;
//	private String host;
//	private int port;
//
//	BufferedReader in;
//	PrintWriter out;
//
	
//
