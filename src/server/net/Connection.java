package server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
	public static int NUM_CONNECTIONS = 0;

	private int ID;

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	float x, y;

	public Connection(Socket client) {
		this.ID = ++NUM_CONNECTIONS;
		this.client = client;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Client " + ID + " connesso");
		x = y = 0;
	}

	public void tick() {
		int xOff = 0, yOff = 0;

		try {
			String[] moves = in.readLine().split(" ");
			if ("true".equals(moves[0]))
				yOff--;
			if ("true".equals(moves[1]))
				yOff++;
			if ("true".equals(moves[2]))
				xOff--;
			if ("true".equals(moves[3]))
				xOff++;
		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
		out.println((x += xOff) + " " + (y += yOff));
		out.flush();
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

	public boolean isClosed() {
		return client.isClosed();
	}
}
