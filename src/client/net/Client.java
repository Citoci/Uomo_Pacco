package client.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private Socket client;
	private String host;
	private int port;

	BufferedReader in;
	PrintWriter out;

	public Client() {
		host = "localhost";
		port = 9090;
		try {
			client = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void tick() {
//		try {
//			out.println(game.getKeyManager().up + " " + game.getKeyManager().down + " " + game.getKeyManager().left
//					+ " " + game.getKeyManager().right + " ");
//			out.flush();
//			String s = in.readLine();
//			game.x = (int) Float.parseFloat(s.split(" ")[0]);
//			game.y = (int) Float.parseFloat(s.split(" ")[1]);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void close() {
		try {
			client.close();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
