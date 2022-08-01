package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniClient extends Thread {

	private Socket socket;
	private Client server;

	public MiniClient(Socket serverClientServerSocket, Client server) {
		super("MiniClient");
		this.socket = serverClientServerSocket;
		this.server = server;
	}
	
	
	public void run() {
		DataInputStream inMes;
		while(true) {
			try {
				inMes = new DataInputStream( this.socket.getInputStream());
				String str = inMes.readUTF();
				System.out.print(str);
				System.out.print("\n>>> ");
				Thread.sleep(500);
				server.cls();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
