package cards;

import enums.Material;
import gui.Game;

@SuppressWarnings("serial")
public class Townhall extends Card {

	private static final String name = "Townhall";
	private static final Material material = Material.None;
	
	public Townhall(Game window) {
		super(name, window, material);
	}

}
