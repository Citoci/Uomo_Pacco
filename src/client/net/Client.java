package client.net;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.Game;
import client.input.KeyManager;
import client.net.Packets.ClientPacket;

public class Client {
	
	private Socket socket;
	private String host = "localhost";
	private int port = 5656;
	
	private DataInputStream in;
	private DataOutputStream out;
		
	public Client(String host, int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;
		socket = new Socket(host, port);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	public void tick() throws IOException {
		KeyManager keyManager = Game.game.getKeyManager();
		ClientPacket cP = new ClientPacket(keyManager.up, keyManager.down, keyManager.left, keyManager.right);
		out.write(cP.getBytes());
		
	}

	public void close() throws IOException {
		socket.close();
		in.close();
		out.close();
	}
	
}

//	static Socket socket;
//	static DataInputStream in;
//	static DataOutputStream out;
//	
//	
//	public static void main(String[] args) throws Exception {
//		System.out.println("Connecting... ");
//		socket = new Socket("localhost", 5556);
//		System.out.println("Connection successful.");
//		in = new DataInputStream(socket.getInputStream());
//		
//		out = new DataOutputStream(socket.getOutputStream());
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter your name and press enter: ");
//		String name = sc.nextLine();
//		System.out.println("같같같같같같같같같같같같같같같같같같같같같같같같같같같같�");
//	
//		Input input = new Input(in, name);
//		Thread thread = new Thread(input);
//		thread.start();
//		
//		out.writeUTF(name);
//		while(true) {
//			String sendMessage = sc.nextLine();
//			out.writeUTF(sendMessage);
//		}
//			
//	}
//	
//}
//
//class Input implements Runnable{
//	DataInputStream in;
//	String name;
//	
//	public Input(DataInputStream in, String name) {
//		this.in = in;
//		this.name=name;
//	}
//	
//	
//	
//	@Override
//	public void run() {
//		
//		while(true) {
//			try {
//				String message;
//				message = in.readUTF();
//				String[] mess = message.split(":");
//				
//				if(!mess[0].equals(name)) 
//					System.out.println(message);
//					Launcher launcher = new Launcher();
//				
//			} catch (IOException e) {
//				//e.printStackTrace();
//				
//			}
//			
//		}
//	}
//	
//	
//}
	
//	private Socket client;
//	private String host;
//	private int port;
//
//	BufferedReader in;
//	PrintWriter out;
//
	
//
