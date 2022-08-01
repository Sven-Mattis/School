package cards;

import enums.Material;
import gui.Game;

@SuppressWarnings("serial")
public class Quarry extends Card {
	
	private static final String name = "Quarry";
	private static final Material material = Material.Stone;
	
	public Quarry(Game window) {
		super(name, window, material);
	}

}
