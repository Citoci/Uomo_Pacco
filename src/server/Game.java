package server;

import server.net.Server;

public class Game implements Runnable {

	private boolean running;
	public final int fps = 30;

	// Thread
	private Thread gameThread;

	// Net
	private Thread serverThread;
	private Server server;

	public Game() {

	}

	public void init() {
		server = new Server(this);
		serverThread = new Thread(server);
	}

	@Override
	public void run() {
		init();
		serverThread.start();

		double delta = 0;
		long now, lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) * fps / 10e8;
			lastTime = now;

			if (delta >= 1) {
				tick();
				delta--;
			}
		}
	}

	public void tick() {
		server.tick();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();

	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		gameThread.interrupt();
	}

}
