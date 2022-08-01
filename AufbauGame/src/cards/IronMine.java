package cards;

import enums.Material;
import gui.Game;

@SuppressWarnings("serial")
public class IronMine extends Card {
	
	private static final String name = "IronMine";
	private static final Material material = Material.Iron;
	
	public IronMine(Game window) {
		super(name, window, material);
	}

}
