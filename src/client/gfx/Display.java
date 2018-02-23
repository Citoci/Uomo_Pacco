package client.gfx;

import java.awt.Canvas;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Display extends JFrame {
	
	private Canvas canvas;
	
	public Display(String title, int wRes, int hRes) {
		super(title);
		setSize(wRes, hRes);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
					
		canvas = new Canvas();
		canvas.setFocusable(false);
		
		add(canvas);
	}
	
	public Canvas getCanvas() { return canvas; }

}
