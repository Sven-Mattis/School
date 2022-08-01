package render;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import pathMaker.CorrectPath;

public class BuildMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image background;
	private JFrame window;

	public BuildMenu(JFrame window) {
		this.window = window;
		this.load();
		this.setLayout(null);
	}

	private void load() {
		try {
			background = ImageIO.read(new File(new CorrectPath().checkPath("../Resources/Textures/BuildMenu.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.background, 0, 0, ((int) window.getBounds().getWidth()), 100, null);
	}
}
