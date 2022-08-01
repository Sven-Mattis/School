package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Resources.ResourceManager;
import cards.BronzeMine;
import cards.Card;
import cards.GoldMine;
import cards.IronMine;
import cards.Lumber;
import cards.Quarry;
import cards.Townhall;
import enums.Material;
import enums.OptionsWindowSize;
import render.BuildMenu;
import render.Hud;
import render.Render;

public class Game extends JFrame {

	/**
	 * Author: Sven-Mattis Rackow
	 * 
	 * School Project
	 */
	
	
	// variables
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> cardStrings = new ArrayList<String>();

	private Game window;

	private Render render;

	private OptionsWindowSize windowSize;

	public int cardNumbers = -1;

	@SuppressWarnings("unused")
	private ResourceManager ResourceManager = new ResourceManager();

	public ArrayList<Card> cards = new ArrayList<Card>();

	
	// Constructer
	public Game(OptionsWindowSize window) {
		
		// Start the Thread with the Resource Manager
		this.ResourceManager.start();
		// set the Enum
		this.windowSize = window;
		
		// fetch every Card Img, DO NOT PUT NEW FILES INSIDE THE DIRECTORY, MAY CAUSE ERRORS
		File[] cardsPackage = new File("bin/cards").listFiles();
		for (File file : cardsPackage) {
			
			// Add to the Array List
			// Put with a lot String Manipulation and Bug fixes
			// replace(\\, \\\\) <--- Makes it work correctly in every case
			cardStrings.add(file.toString().replace("\\", "\\\\").split("\\\\")[file.toString().replace("\\", "\\\\").split("\\\\").length - 1].replace(".class",""));
		}
	}

	// Start the Window
	public void start() {
		
		// for a better Readability
		this.window = this;
		
		// Standart Swing Sh*t
		window.setTitle("2d Aufbau Spiel");
		window.repaint();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set the Size of the Screen based on the Enum, given in the Constructer
		if (this.windowSize == OptionsWindowSize.Fullscreen) {
			window.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().width,
					(int) Toolkit.getDefaultToolkit().getScreenSize().height);
			window.setUndecorated(true);
		} else if (this.windowSize == OptionsWindowSize.Normal)
			window.setBounds(0, 0, 1000 / 9 * 15, 1000 / 16 * 15);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Go to Main Menu
		this.toMainMenu();
	}

	private void toMainMenu() {
		

		cardNumbers = -1;
		cards = new ArrayList<Card>();
		
		
		// Remove all content from the Frame
		window.getContentPane().removeAll();
		window.setBackground(Color.BLACK);
		window.setLayout(new GridBagLayout());
		
		// For centering the Content
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		// Normal Swing things
		JLabel Name = new JLabel("<html><h1 style=\" font-size: 20px; \">Aufbauspiel</h1></html>",
				SwingConstants.CENTER);
		Name.setBorder(new EmptyBorder(0, 0, Toolkit.getDefaultToolkit().getScreenSize().height / 10 - 10, 0));
		JButton startBtn = new JButton("Start Game");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartGame();
			}
		});
		JButton options = new JButton("Options");
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options();
			}
		});
		JButton EXIT = new JButton("Exit");
		EXIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(window, " Are you sure you want to exit? ", "Sure?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.NO_OPTION, null, null, null);
				if (n == 0) {
					window.dispose();
				}
			}
		});
		JPanel menu = new JPanel(new GridBagLayout());
		menu.add(Name, gbc);
		menu.add(startBtn, gbc);
		menu.add(options, gbc);
		menu.add(EXIT, gbc);

		gbc.weighty = 1;
		window.add(menu, gbc);
		window.repaint();
		window.setVisible(true);
	}

	protected void StartGame() {
		// Normal Swing things
		window.getContentPane().removeAll();
		window.setBackground(Color.BLACK);
		window.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		JLabel Name = new JLabel("<html><h1 style=\" font-size: 20px; \">Start Game</h1></html>",
				SwingConstants.CENTER);
		Name.setBorder(new EmptyBorder(0, 0, Toolkit.getDefaultToolkit().getScreenSize().height / 20 - 10, 0));
		JButton newGame = new JButton("New");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		JButton loadGame = new JButton("Load");
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadGame();
			}
		});
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toMainMenu();
			}
		});
		JPanel menu = new JPanel(new GridBagLayout());
		menu.add(Name, gbc);
		menu.add(newGame, gbc);
		menu.add(loadGame, gbc);
		menu.add(Back, gbc);

		gbc.weighty = 1;
		window.add(menu, gbc);
		window.repaint();
		window.setVisible(true);
	}

	protected void loadGame() {
		
		// Not implemented yet
		
		// TODO Auto-generated method stub

	}

	protected void newGame() {
		window.getContentPane().removeAll();
		
		// Make the playgame renderer
		render = new Render(window);
		
		// Activate the Resource handling
		this.ResourceManager.newGame(this.render, this);
		render.setBounds(0, 0, ((int) window.getBounds().getWidth()), ((int) window.getBounds().getHeight()));
		render.newMap();
		
		// Create the Hud
		Hud hud = new Hud(window, ResourceManager);
		hud.repaint();
		
		// Create the Buildmenu
		BuildMenu buildMenu = new BuildMenu(window);
		buildMenu.setBounds(0, ((int) window.getBounds().getHeight() - 100), ((int) window.getBounds().getWidth()),
				100);
		buildMenu.setBackground(new Color(0, 0, 0, 0));
		buildMenu.repaint();
		
		// Get a Townhall
		new Townhall(window);
		
		// Add random Cards, the Number is Buggy because there are some Cards files that cannot be rendered
		window.randCards(30);
		window.add(buildMenu);

		// Event Handling
		window.addMouseWheelListener(new MouseWheelListener() {

			// private float modifier = 1;

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// this.modifier += (this.modifier+e.getWheelRotation() >= 1 &&
				// this.modifier+e.getWheelRotation() <= 4 )?e.getWheelRotation() :0;
				// render.modifier = modifier;
				window.repaint();
			}

		});
		
		// Resize Listener
		window.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				render.setBounds(0, 0, ((int) window.getBounds().getWidth()), ((int) window.getBounds().getHeight()));
				buildMenu.setBounds(0, ((int) window.getBounds().getHeight() - 130),
						((int) window.getBounds().getWidth()), 150);
				window.setAutoRequestFocus(true);
			}
		});
		
		// Key Listener
		window.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 'w')
					render.yOffset += (render.yOffset + render.textureSize < 0) ? render.textureSize / 2
							: -render.textureSize / 2;
				if (e.getKeyChar() == 'a')
					render.xOffset += (render.xOffset + render.textureSize < 0) ? render.textureSize / 2
							: -render.textureSize / 2;
				if (e.getKeyChar() == 's')
					render.yOffset -= (render.yOffset - render.textureSize - window.getHeight() > -1 * render.mapSize)
							? render.textureSize / 2
							: -render.textureSize / 2;
				if (e.getKeyChar() == 'd')
					render.xOffset -= (render.xOffset - render.textureSize - window.getWidth() > -1 * render.mapSize)
							? render.textureSize / 2
							: -render.textureSize / 2;
				if(e.getKeyChar() == 27) {
					toMainMenu();
				}
					
				window.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'w')
					render.yOffset += (render.yOffset + render.textureSize < 0) ? render.textureSize / 2
							: -render.textureSize / 2;
				if (e.getKeyChar() == 'a')
					render.xOffset += (render.xOffset + render.textureSize < 0) ? render.textureSize / 2
							: -render.textureSize / 2;
				if (e.getKeyChar() == 's')
					render.yOffset -= (render.yOffset - render.textureSize - window.getHeight() > -1 * render.mapSize)
							? render.textureSize / 2
							: -render.textureSize / 2;
				if (e.getKeyChar() == 'd')
					render.xOffset -= (render.xOffset - render.textureSize - window.getWidth() > -1 * render.mapSize)
							? render.textureSize / 2
							: -render.textureSize / 2;
				window.repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

		});
		// Finish the Frame
		window.add(render);
		window.repaint();
		window.setAutoRequestFocus(true);
		window.requestFocus();
		window.setLayout(null);
		window.setFocusable(true);
		window.setVisible(true);
	}

	private void randCards(int inInt) {
		// Create a Random Card
		
		for (int i = inInt; i > 0; i--) {
			int randInt = (int) Math.round(Math.random() * this.cardStrings.size());
			if (randInt < 0)
				randInt++;
			else if (randInt >= this.cardStrings.size())
				randInt = this.cardStrings.size() - 1;
			String cardString = this.cardStrings.get(randInt);
			switch (cardString) {
			case "Lumber":
				new Lumber(window);
				break;
			case "Quarry":
				new Quarry(window);
				break;
			case "IronMine":
				new IronMine(window);
				break;
			case "GoldMine":
				new GoldMine(window);
				break;
			case "BronzeMine":
				new BronzeMine(window);
				break;
			}
		}
	}

	private void Options() {
		
		// Normal Swing things
		window.getContentPane().removeAll();
		window.setBackground(Color.BLACK);
		window.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		JLabel Name = new JLabel("<html><h1 style=\" font-size: 20px; \">Options</h1></html>", SwingConstants.CENTER);
		Name.setBorder(new EmptyBorder(0, 0, Toolkit.getDefaultToolkit().getScreenSize().height / 20 - 10, 0));
		JButton Window = new JButton("Window");
		Window.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowOptions();
			}
		});
		JButton Sound = new JButton("Sound");
		Sound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options();
			}
		});
		JButton Back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toMainMenu();
			}
		});
		JPanel menu = new JPanel(new GridBagLayout());
		menu.add(Name, gbc);
		menu.add(Window, gbc);
		menu.add(Sound, gbc);
		menu.add(Back, gbc);

		gbc.weighty = 1;
		window.add(menu, gbc);
		window.repaint();
		window.setVisible(true);
	}

	public void rePosCard() {
		// Repositioning every Card
		window.cardNumbers = 0;
		for (Card card : window.cards) {
			card.updatePos();
			window.cardNumbers++;
		}
	}

	public void checkPlaceable(int indexX, int indexY, Material material, String path, Card card) {
		
		// check if the Card is Placeable right there
		if (render.checkIndex(indexX, indexY, material, path)) {
			if (card.name == "Townhall" || card.name.equals("Townhall")) {
				this.render.townhall = true;
				this.render.townhallPosition = new int[] { indexX, indexY };
			}
			window.remove(card);
			window.cards.remove(card);
		}
	}

	protected void WindowOptions() {
		
		// Normal Swing things
		window.getContentPane().removeAll();
		window.setBackground(Color.BLACK);
		window.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 10, 10, 10);
		JLabel Name = new JLabel("<html><h1 style=\" font-size: 20px; \">Window Options</h1></html>",
				SwingConstants.CENTER);
		Name.setBorder(new EmptyBorder(0, 0, Toolkit.getDefaultToolkit().getScreenSize().height / 15 - 10, 0));

		JLabel windowMode = new JLabel("<html><h2 style=\" font-size: 20px; \">Window Mode</h2></html>");
		JRadioButton modeBorderless = new JRadioButton("Borderless",
				(this.windowSize == OptionsWindowSize.Fullscreen) ? true : false);
		JRadioButton modeWindowed = new JRadioButton("Windowed",
				(this.windowSize == OptionsWindowSize.Normal) ? true : false);

		ButtonGroup group = new ButtonGroup();

		group.add(modeBorderless);
		group.add(modeWindowed);

		JPanel menu = new JPanel(new GridBagLayout());
		menu.add(Name, gbc);
		menu.add(windowMode, gbc);
		menu.add(modeBorderless);
		menu.add(modeWindowed, gbc);

		JButton Back = new JButton("Back");
		
		// On going back
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modeWindowed.isSelected()) {
					if (window.isUndecorated()) {
						windowSize = OptionsWindowSize.Normal;
						window.setVisible(false);
						window.dispose();
						window.setUndecorated(false);
						window.setVisible(true);
					}
					window.setBounds(0, 0, 1000 / 9 * 16, 1000 / 16 * 16);
					window.setMinimumSize(new Dimension(640, 480));
					window.setLocationRelativeTo(null);
				} else if (modeBorderless.isSelected()) {
					if (!window.isUndecorated()) {
						windowSize = OptionsWindowSize.Fullscreen;
						window.setVisible(false);
						window.dispose();
						window.setUndecorated(true);
						window.setVisible(true);
					}
					window.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
							Toolkit.getDefaultToolkit().getScreenSize().height);

				}
				Options();
			}
		});

		menu.add(Back, gbc);
		gbc.weighty = 1;
		window.add(menu, gbc);
		window.repaint();
		window.setVisible(true);
	}

	public int getXOffset() {
		return render.getXOffset();
	}

	public int getYOffset() {
		return render.getYOffset();
	}
}
