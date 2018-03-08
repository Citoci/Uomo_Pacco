package client;

public class Launcher {

	public static void main(String[] args) {
		GameServer gameServer = new GameServer();
		gameServer.start();
		
		GameClient gameClient = new GameClient("Client 1");
		while(gameServer.isRunning());
		gameClient.start();

				
		
				
	}

}
