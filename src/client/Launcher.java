package client;

public class Launcher {

	public static void main(String[] args) {
		Game.game = new Game("Pacman");
		Game.game.start();
	}

}
