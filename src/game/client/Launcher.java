package game.client;

public class Launcher {
	public static void main(String[] args) {
		GameClient game = new GameClient("Pacman Client");
		game.start();
	}
}
