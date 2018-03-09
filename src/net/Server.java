package net;

import java.io.IOException;
import java.net.ServerSocket;

import game.server.GameServer;

public class Server implements Runnable{

	private GameServer game;

	private ServerSocket serverSocket;
	private int port = 5656;

	private User[] users;

	public Server(GameServer game, int maxNumPlayers) throws IOException {
		this.game = game;

		serverSocket = new ServerSocket(port); // Apre un nuovo serversocket
		users = new User[maxNumPlayers]; // Istanza l'array di users
	}


	@Override
	public void run() {
		while(game.isRunning()) {
			try {
				for(int i=0; i<users.length; i++) // cicla tutti gli users
					if(users[i] == null) // non nulli
						users[i] = new User(game, i, serverSocket.accept()); // aggiunge all'array eventuali nuovi users
 				Thread.sleep(500);

			} catch (IOException | InterruptedException e) { e.printStackTrace(); }
		}
	}

	public void tick() {
		for(int i=0; i<users.length; i++) { // cicla tutti gli users
			if(users[i] != null) // non nulli
				try {
					users[i].tick(); // li ticka
				} catch (IOException e) { // se c'è eccezione chiude l'user e lo dimentica
					e.printStackTrace();
					users[i].close();
					users[i] = null;
				}
		}
	}

}