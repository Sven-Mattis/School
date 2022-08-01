package main;

import java.util.Scanner;

import client.Client;

public class Main {

	private static final String[] heros = {"Mundo", "Ashe"};
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String champ = "";
		while(!heroExists(champ)) {
			System.out.println("Wählen sie ihren Champion");
			printHeros();
			System.out.println("");
			champ = in.nextLine().toLowerCase();
		}
		System.out.println("Verbindung zum Server wird hergestellt!");
		new Client(champ ,in);
	}

	private static boolean heroExists(String champ) {
		for(String hero : heros) {
			if(champ.equals(hero.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private static void printHeros() {
		for(String hero : heros) {
			System.out.print(hero + " ");
		}
	}
}
