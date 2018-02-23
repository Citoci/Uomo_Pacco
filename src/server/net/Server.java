package server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import server.Game;

public class Server implements Runnable {

//	private Game game;

	private boolean running;

	private ServerSocket server;
	private int port;

	private ArrayList<Connection> connections;

	public Server(Game game) {
//		this.game = game;

		port = 1445;
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Errore creazione Server");
			e.printStackTrace();
			System.exit(0);
		}

		connections = new ArrayList<>();
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				connections.add(new Connection(server.accept()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void tick() {
		for (Connection c : connections) {
			if (c.isClosed()) {
				connections.remove(c);
				continue;
			}
			c.tick();
		}
	}
	
	

}
