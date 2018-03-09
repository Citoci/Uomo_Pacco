package client;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import client.input.KeyManager;
import client.net.Client;

public class Game implements Runnable {

	// Config
	private boolean running;
	public final int fps = 60;
	
	// Thread
	private Thread gameThread;
	
	// GFX
	private JFrame frame;
	
	// Input
	private KeyManager keyManager;
	
	// Net
	private Client client;
	
	public Game() {
		frame = new JFrame("Pacman Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(400, 200));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		keyManager = new KeyManager();
		frame.addKeyListener(keyManager);
		
		try {
			client = new Client(keyManager, "localhost", 5656);
		} catch (IOException e) { e.printStackTrace(); }
		
	}

	@Override
	public void run() {
		double delta = 0;
		long now, lastTime = System.nanoTime();
		while (true) {
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
		try {
			client.tick();
		} catch (IOException e) { e.printStackTrace(); }

	}
	
	public synchronized void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this, "gameThread");
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		gameThread.interrupt();
	}

}
