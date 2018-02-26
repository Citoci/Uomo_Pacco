package client.net;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	
	
	public static void main(String[] args) throws Exception {
		System.out.println("Connecting... ");
		socket = new Socket("172.20.10.3", 5556);
		System.out.println("Connection successful.");
		in = new DataInputStream(socket.getInputStream());
		
		out = new DataOutputStream(socket.getOutputStream());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name and press enter: ");
		String name = sc.nextLine();
		System.out.println("같같같같같같같같같같같같같같같같같같같같같같같같같같같같�");
	
		Input input = new Input(in, name);
		Thread thread = new Thread(input);
		thread.start();
		
		out.writeUTF(name);
		while(true) {
			String sendMessage = sc.nextLine();
			out.writeUTF(sendMessage);
		}
			
	}
	
}

class Input implements Runnable{
	DataInputStream in;
	String name;
	
	public Input(DataInputStream in, String name) {
		this.in = in;
		this.name=name;
	}
	
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				String message;
				message = in.readUTF();
				String[] mess = message.split(":");
				
				if(!mess[0].equals(name)) 
					System.out.println(message);
				
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
//	public Client() {
//		host = "localhost";
//		port = 9090;
//		try {
//			client = new Socket(host, port);
//			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//			out = new PrintWriter(client.getOutputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.exit(0);
//		}
//	}
//
//	public void tick() {
//		// try {
//		// out.println(game.getKeyManager().up + " " + game.getKeyManager().down + " " +
//		// game.getKeyManager().left
//		// + " " + game.getKeyManager().right + " ");
//		// out.flush();
//		// String s = in.readLine();
//		// game.x = (int) Float.parseFloat(s.split(" ")[0]);
//		// game.y = (int) Float.parseFloat(s.split(" ")[1]);
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }
//	}
//
//	public void close() {
//		try {
//			client.close();
//			in.close();
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	}


