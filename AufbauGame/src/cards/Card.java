package cards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import enums.Material;
import gui.Game;
import pathMaker.CorrectPath;

public class Card extends JPanel {

	/**
	 * 	Buidling Cards
	 */
	
	
	// Variables
	private static final long serialVersionUID = -2696614446850117064L;
	
	protected final Card card = this;

	private Image cardImg;

	public String name;
	private Game window;
	private int marginLeft = 10;
	private int X = 0, Y = 0;

	protected int fallbackX;

	protected int fallbackY;

	protected Material material;

	
	// Constructer
	public Card(String name, Game window, Material material) {
		super();
		
		// Set the Counter for the Cards 1+
		window.cardNumbers++;
		
		// Add this Card to the ArrayList
		window.cards.add(this);
		
		// Add this Card to the Frame
		window.add(this);
		
		
		// Initialize Variables
		this.material = material;
		this.name = name;
		this.window = window;
		
		// The Fallback if Card tryed to placed on a Wrong field
		this.fallbackX = window.cardNumbers * 100 + marginLeft * window.cardNumbers - marginLeft;
		this.fallbackY = window.getHeight() - 100;
		
		// apply the Fallback
		this.setBounds(fallbackX, fallbackY, 100, 100);
		
		// Make the Background invisible
		this.setBackground(new Color(0, 0, 0, 0));
		
		
		// Add the Mouse Listener to this Card
		// used for the Drag and Drop
		this.addMouseListener(new MouseListener() {
			
			// Create a timer to render in 60FPS
			// and to make a Event that is multiple callable
			private Timer dragging = new Timer(1000 / 60, new ActionListener() {
				
				@Override
				public void actionPerformed(
						ActionEvent DasEventSelberistsowasvonEgalAlsoechtDadieMausPositionnichtgeupdatedwirddahereinunfassbarlangernameummöglichedopplungenzuentgehenbtwwennsiedasgelesenhabenfrageichmichwiesoundvorallemwielangesiedafürgebrauchthaben) {
					// Get the Mouse Position
					Point mouse = window.getMousePosition();
					int x, y;
					// Catch if mouse move out of Screen while dragging
					try {
						x = (int) ((int) mouse.x - 50);
						y = (int) ((int) mouse.y - 50);
					} catch (NullPointerException ne) {
						x = fallbackX;
						y = fallbackY;
					}
					// Apply the new Values to the Variable for the rePos
					X = x;
					Y = y;
					
					setLocation(X, Y);
				}
			});

			@Override
			public void mousePressed(MouseEvent e) {
				// Repainting to prevent Tearing
				repaint();
				
				// Make sure that it repeats
				this.dragging.setRepeats(true);
				// Fire the Dragging event
				this.dragging.start();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// Stop the Dragging immediately
				this.dragging.stop();
				
				// then repaint again to make sure every thing is fine
				repaint();
				
				// get the Index of the field of which is the Card Hovering
				int indexX = (-window.getXOffset() + X);
				int indexY = (-window.getYOffset() + Y);
				
				// get the Correct Index from the Middle of the Card
				// by checking if > 50% is on the next index
				if (indexX % 128 > 64)
					indexX += 128;
				if (indexY % 128 > 64)
					indexY += 128;
				
				// now get the Index
				indexX = indexX / 128;
				indexY = indexY / 128;
				
				// Set the Location to the Fallback
				setLocation(fallbackX, fallbackY);
				
				// Check if this Card is Placeable
				window.checkPlaceable(indexX, indexY, material, "../Resources/Textures/" + name + "Model.png", card);
				
				// and rePos every card
				window.rePosCard();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// NOT USED
				System.out.println("Hover over " + name + "!");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// NOT USED
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// NOT USED
			}

		});
		
		// Load the Texture, depends on the Name, DONT CHANGE NAMES OF MODELS
		this.load("../Resources/Textures/" + this.name + ".png");
	}

	private void load(String path) {
		
		// get the CorrectPath, depending on the Eclispe Debug Variable
		path = new CorrectPath().checkPath(path);
		
		// Load it
		try {
			this.cardImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.cardImg, 0, 0, 128, 128, this);
	}

	public void updatePos() {
		// Recalc the Fallback
		this.fallbackX = window.cardNumbers * 100 + marginLeft * window.cardNumbers - marginLeft;
		this.fallbackY = window.getHeight() - 100;
		
		// and apply the Fallback
		this.setLocation(this.fallbackX, this.fallbackY);
	}
}
