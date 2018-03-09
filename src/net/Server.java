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

		serverSocket = new ServerSocket(port);
		users = new User[maxNumPlayers];
	}


	@Override
	public void run() {
		while(game.isRunning()) {
			try {
				for(int i=0; i<users.length; i++)
					if(users[i] == null)
						users[i] = new User(game, i, serverSocket.accept());
				Thread.sleep(500);

			} catch (IOException | InterruptedException e) { e.printStackTrace(); }
		}
	}

	public void tick() {
		for(int i=0; i<users.length; i++) {
			if(users[i] != null)
				try {
					users[i].tick();
				} catch (IOException e) {
					e.printStackTrace();
					users[i].close();
					users[i] = null;
				}
		}
	}

}