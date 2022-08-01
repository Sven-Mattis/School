package cards;

import enums.Material;
import gui.Game;

@SuppressWarnings("serial")
public class Lumber extends Card {
	
	private static final String name = "Lumber";
	private static final Material material = Material.Wood;
	
	public Lumber(Game window) {
		super(name, window, material);
	}

}
