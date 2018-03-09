package gfx;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Display extends JFrame {

	private Canvas canvas;

	public Display(String title, int wRes, int hRes) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		canvas = new Canvas();
		canvas.setFocusable(false);
		canvas.setSize(new Dimension(wRes, hRes));

		add(canvas);
		pack();

		setVisible(true);
		setLocationRelativeTo(null);
	}

	public Canvas getCanvas() {
		return canvas;
	}

}
