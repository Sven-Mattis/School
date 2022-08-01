package render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Resources.ResourceManager;
import gui.Game;
import pathMaker.CorrectPath;

public class Hud extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320262459857272044L;

	@SuppressWarnings("unused")
	private Game window;
	
	private final String[] texturesString = new String[] {"GoldCoin", "GoldOre", "IronOre", "BronzeOre", "Stone", "Wood"};
	
	private Image[] textures = new Image[texturesString.length];

	private ResourceManager ResourceManager;
	
	public Hud(Game window, ResourceManager ResourceManager) {
		super();
		this.ResourceManager = ResourceManager;
		this.window = window;
		this.load();
		this.setBounds(0, 0, window.getWidth(), 32+20);
		this.setBackground(new Color(255, 255, 255,100));
		window.add(this);
		this.repaint();
	}

	private void load() {
		System.out.println("Load textures");
		for(int i = 0; i < texturesString.length; i++) {
			System.out.println(texturesString[i]);
			try {
				textures[i] = ImageIO.read(new File(new CorrectPath().checkPath("../Resources/Textures/" + texturesString[i] + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		int AbstandIcon = (64 + 32);
		super.paintComponent(g);
		g.setColor(new Color(0,0,0,255));
		for(int i = 0; i < textures.length; i++) {
			g.drawImage(textures[i], i * AbstandIcon + 10, ((50-32)/2), 32, 32, null);
			g.drawString("" + ResourceManager.resources[i], i * AbstandIcon + 32 + 10, ((50+g.getFont().getSize())/2)-2);
		}
	}
}
