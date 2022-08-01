package cards;

import enums.Material;
import gui.Game;

@SuppressWarnings("serial")
public class GoldMine extends Card {
	
	private static final String name = "GoldMine";
	private static final Material material = Material.Gold;
	
	public GoldMine(Game window) {
		super(name, window, material);
	}

}
