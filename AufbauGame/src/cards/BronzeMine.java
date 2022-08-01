package cards;

import enums.Material;
import gui.Game;

@SuppressWarnings("serial")
public class BronzeMine extends Card {
	
	private static final String name = "BronzeMine";
	private static final Material material = Material.Bronze;
	
	public BronzeMine(Game window) {
		super(name, window, material);
	}

}
