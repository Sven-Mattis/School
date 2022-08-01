package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import enums.Material;
import gui.Game;
import pathMaker.CorrectPath;

public class Render extends JPanel {

	/**
	 * The Main rendering
	 */

	// Variables
	private static final long serialVersionUID = 1L;

	private int fields = 512;
	public int mapSize = 128 * fields, xOffset = -(mapSize / 2), yOffset = -(mapSize / 2), textureSize = 128;
	public float modifier = 1;

	String[] texturesString = new String[this.getLength()];
	Image[] textures = new Image[this.getLength()];
	int[] x = new int[mapSize / textureSize], y = new int[mapSize / textureSize];

	private Image[][] renderMap;
	private Material[][] material;

	private Game window;

	@SuppressWarnings("unused")
	private int i = 0;

	public boolean townhall = false;

	public int[] townhallPosition;

	public int townhallRange = 16;

	private Image grass;

	// Constructer
	public Render(Game window) {
		this.window = window;
		load();
	}

	// Get the amount of Models / Textures
	private int getLength() {
		int count = 0;
		File[] files = new File(new CorrectPath().checkPath("../Resources/Models/")).listFiles();

		// Catch NullPointer
		if (files == null)
			return 0;

		// Check if the File ends with .m2dl
		for (File file : files) {
			if (files != null)
				count += (file.getName().endsWith("m2dl")) ? 1 : 0;
		}
		return count;
	}

	// Load all Models
	private void load() {
		File[] files = new File(new CorrectPath().checkPath("../Resources/Models/")).listFiles();

		// Ctach NullPointer
		if (files == null)
			return;

		// Loop throgh the Array of Files
		for (int i = 0; i < files.length; i++) {

			// Catch NullPointer
			if (files[i] == null)
				continue;

			// if file ends with .m2dl
			if (files[i].getName().endsWith("m2dl")) {
				if (files[i].getName().contains("grass"))
					try {
						this.grass = ImageIO.read(files[i]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				// Put this Path as String in array
				texturesString[i] = files[i].getPath();
				// and Load the Texture in
				try {
					textures[i] = ImageIO.read(new File(texturesString[i]));
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Set X|Y for fallback
				x[i] = 9999;
				y[i] = 9999;
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(mapSize, mapSize));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Loop for x and y coords
		for (int y = 0; y < mapSize / textureSize; y++) {
			for (int x = 0; x < mapSize / textureSize; x++) {

				// check if this tile is in FoV
				if (inView(x, y) && inRangeOfTownHall(x, y)) {
					if (this.x[y] == 9999 || this.y[y] == 9999) {
						System.out.println("Breaked");
						break;
					}

					// Draw the Img
					g.drawImage(this.renderMap[y][x], Math.round(this.x[x] / modifier) + xOffset,
							Math.round(this.y[y] / modifier) + yOffset, Math.round(textureSize / modifier + 1),
							Math.round(textureSize / modifier + 1), null);

					// Draw a slightly border
					g.setColor(new Color(0, 0, 0, 50));
					g.drawRect(Math.round(this.x[x] / modifier) + xOffset, Math.round(this.y[y] / modifier) + yOffset,
							this.textureSize, this.textureSize);

					// draw X|Y coords
					g.setColor(new Color(255, 0, 0, 100));
					g.drawString("" + this.x[x],
							Math.round(this.x[x] / modifier) + xOffset + (this.textureSize / 2) - 64,
							Math.round(this.y[y] / modifier) + yOffset + (this.textureSize / 2));
					g.drawString("" + this.y[y], Math.round(this.x[x] / modifier) + xOffset + (this.textureSize / 2),
							Math.round(this.y[y] / modifier) + yOffset + (this.textureSize / 2));
				} else if (inView(x, y)) {

					// Draw the Img
					g.drawImage(this.grass, Math.round(this.x[x] / modifier) + xOffset,
							Math.round(this.y[y] / modifier) + yOffset, Math.round(textureSize / modifier + 1),
							Math.round(textureSize / modifier + 1), null);

					// Draw a slightly border
					g.setColor(new Color(0, 0, 0, 50));
					g.drawRect(Math.round(this.x[x] / modifier) + xOffset, Math.round(this.y[y] / modifier) + yOffset,
							this.textureSize, this.textureSize);

					// draw X|Y coords
					g.setColor(new Color(255, 0, 0, 100));
					g.drawString("" + this.x[x],
							Math.round(this.x[x] / modifier) + xOffset + (this.textureSize / 2) - 64,
							Math.round(this.y[y] / modifier) + yOffset + (this.textureSize / 2));
					g.drawString("" + this.y[y], Math.round(this.x[x] / modifier) + xOffset + (this.textureSize / 2),
							Math.round(this.y[y] / modifier) + yOffset + (this.textureSize / 2));
				}
			}
		}
	}

	private boolean inRangeOfTownHall(int x, int y) {
		// break if there is no townhall
		if (!this.townhall)
			return true;

		// return true if in range of townhall
		if (this.townhallPosition[0] + this.townhallRange / 2 > x
				&& this.townhallPosition[0] - this.townhallRange / 2 < x
				&& this.townhallPosition[1] + this.townhallRange / 2 > y
				&& this.townhallPosition[1] - this.townhallRange / 2 < y) {
			return true;
		}
		return false;
	}

	private boolean inView(int x, int y) {

		// Its just pain! to Thing about

		// But it creates a Rectangle of the height and width of the Window
		// with a little buffer
		// if the fields coords are in this rectangle
		// then draw this
		if (
		// <
		this.x[x] < -this.xOffset + (window.getWidth())
				// >
				&& this.x[x] > -this.xOffset - 100
				// ^
				&& this.y[y] < -this.yOffset + (window.getHeight())
				// v
				&& this.y[y] > -this.yOffset - 100) {
			this.i++;
			return true;
		}
		return false;
	}

	public void newMap() {

		// create a new Map
		int mapSizeRow = mapSize / textureSize;
		this.renderMap = new Image[mapSizeRow][mapSizeRow];
		this.material = new Material[mapSizeRow][mapSizeRow];

		// loop through for X|Y coords
		for (int y = 0; y < mapSizeRow; y++) {
			for (int x = 0; x < mapSizeRow; x++) {

				// randm int for the texture
				int randInt = (int) Math.round(Math.random() * textures.length - 1);
				if (randInt < 0)
					randInt = 0;

				// Material depends on the texture
				if (texturesString[randInt].toString().contains("tree")) {
					material[y][x] = Material.Wood;
				} else if (texturesString[randInt].toString().contains("stone")) {
					material[y][x] = Material.Stone;
				} else if (texturesString[randInt].toString().contains("gold")) {
					material[y][x] = Material.Gold;
				} else if (texturesString[randInt].toString().contains("iron")) {
					material[y][x] = Material.Iron;
				} else if (texturesString[randInt].toString().contains("bronze")) {
					material[y][x] = Material.Bronze;
				} else {
					material[y][x] = Material.None;
				}
				
				// put all in the array(s)
				this.renderMap[y][x] = textures[randInt];
				this.x[x] = x * textureSize;
				this.y[y] = y * textureSize;
			}
		}
	}

	public boolean checkIndex(int indexX, int indexY, Material material2, String Model) {
		// Break if there is no Townhall
		if(!this.townhall && !Model.contains("Townhall"))
			return false;
		if(!this.inRangeOfTownHall(indexX, indexY) && !Model.contains("Townhall"))
			return false;
		
		// this is the methode that would be called if card is tryed to place down
		if (this.material[indexY][indexX] == material2) {
			try {
				this.renderMap[indexY][indexX] = ImageIO.read(new File(new CorrectPath().checkPath(Model)));
				this.repaint();
				if(Model.contains("Lumber")) {
					this.material[indexY][indexX] = Material.Lumber;
				} else if(Model.contains("Quarry")) {
					this.material[indexY][indexX] = Material.Quarry;
				} else if(Model.contains("Gold")) {
					this.material[indexY][indexX] = Material.GoldM;
				} else if(Model.contains("Bronze")) {
					this.material[indexY][indexX] = Material.BronzeM;
				} else if(Model.contains("Iron")) {
					this.material[indexY][indexX] = Material.IronM;
				}
				System.out.println(material[indexY][indexX]);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int getXOffset() {
		return this.xOffset;
	}

	public int getYOffset() {
		return this.yOffset;
	}

	public Material[][] getMaterial() {
		return this.material;
	}
}
