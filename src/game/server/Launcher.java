package game.server;

public class Launcher {

	public static void main(String[] args) {
		GameServer game = new GameServer("Pacman Server");
		game.start();
	}

}
