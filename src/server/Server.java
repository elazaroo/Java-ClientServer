package server;

import java.io.*;
import java.net.*;

public class Server {
	static final int PORT = 5000, maxClients = 3;
	
	public Server() {
		try {
			ServerSocket skServer = new ServerSocket(PORT);
			System.out.println("Listening port: " + PORT);
			for (int clientNum = 0; clientNum < maxClients; clientNum++) {
				Socket skClient = skServer.accept(); // Create the object
				System.out.println("Serving client " + clientNum);
				InputStream aux = skClient.getInputStream();
				DataInputStream flow = new DataInputStream(aux);
				do {
					System.out.println(flow.readDouble());
				} while (flow.available() > 0);
				flow.close();
				skClient.close();
			}
			System.out.println("Max client reached");
			skServer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		new Server();
	}
}