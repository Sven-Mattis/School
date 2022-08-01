package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	private Socket client;
	private static int ServerClient = (int) (Math.random() * 1000);
	private ServerSocket ServerClientServer;

	public Client(String hero, Scanner in) {
		try {
			this.client = new Socket("localhost", 1);
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			Thread.sleep(100);
			out.writeUTF(hero);
			out.flush();
			this.ServerClientServer = new ServerSocket(this.ServerClient);
			System.out.println("Server auf Port " + this.ServerClient + " erstellt!");
			out.writeInt(ServerClient);
			out.flush();
            java.net.Socket ServerClientServerSocket = ServerClientServer.accept();
            MiniClient mini = new MiniClient(ServerClientServerSocket, this);
            mini.start();
			System.out.println("Verbindung wurde hergestellt!");
			String nachricht = "";
			while (!nachricht.equals("stop")) {
				out.writeUTF(nachricht);
				out.flush();
				System.out.print(">>> ");
				nachricht = in.next();
				System.out.println(nachricht);
				Thread.sleep(10);
			}
			out.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	void cls() {
        try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
