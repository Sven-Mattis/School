package Resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import enums.Material;
import gui.Game;
import render.Render;

public class ResourceManager extends Thread {

	/*
	 * Multi Threading not Possible in normal way with Swing, but for better
	 * understanding
	 */
	
	private Render render;
	private Game window;
	public int stone = 0, gold = 0, coins = 0, wood = 0, iron = 0, bronze = 0;
	public int[] resources = new int[] {10, 20, 30, 40, 50, 60};

	private Timer Updater = new Timer(2000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Update();
		}
	});

	@Override
	public void start() {
		System.out.println("ResourceManager has Started");
	}

	public void newGame(Render render, Game window) {
		this.render = render;
		this.window = window;
		Updater.start();
	}

	public void Update() {
		this.stone = 0;
		this.gold = 0;
		this.iron = 0;
		this.bronze = 0;
		this.wood = 0;
		this.coins = 0;

		Material[][] material = this.render.getMaterial();

		if (render.townhallPosition == null)
			return;

		for (int x = 0; x < material.length; x++) {
			for (int y = 0; y < material.length; y++) {
				if (render.townhallPosition[0] + (render.townhallRange / 2) > x
						&& render.townhallPosition[0] - (render.townhallRange / 2) < x
						&& render.townhallPosition[1] + (render.townhallRange / 2) > y
						&& render.townhallPosition[1] - (render.townhallRange / 2) < y) {
					     if (material[y][x] == Material.Quarry)
						this.stone++;
					else if (material[y][x] == Material.Lumber)
						this.wood++;
					else if (material[y][x] == Material.IronM)
						this.iron++;
					else if (material[y][x] == Material.GoldM)
						this.gold++;
					else if (material[y][x] == Material.BronzeM)
						this.bronze++;
				}
			}
		}
		this.resources[1] += this.gold;
		this.resources[2] += this.iron;
		this.resources[3] += this.bronze;
		this.resources[4] += this.stone;
		this.resources[5] += this.wood;
		
		window.repaint();
	}
}
