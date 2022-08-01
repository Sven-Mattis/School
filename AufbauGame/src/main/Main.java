package main;

import debug.EclipsePath;
import enums.OptionsWindowSize;
import gui.Game;

public class Main {

	public static void main(String[] args) {
		
		// Activate the Debug Mode with arg -Eclipse or -eclipse
		for(String arg: args) {
			if(arg.toLowerCase().equals("-eclipse")) {
				EclipsePath.setEclipse();
				System.out.println("Eclipse detected!");
			}
		}
		
		// Start the game
		Game game = new Game(OptionsWindowSize.Fullscreen);
		game.start();
	}

}
