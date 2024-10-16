package client;

import java.io.*;
import java.net.*;

public class Client {
	static final String HOST = "localhost";
	static final int PORT = 5000;
	
	public Client() {
		try {
			Socket skClient = new Socket(HOST, PORT);
			InputStream aux = skClient.getInputStream();
			DataInputStream flow = new DataInputStream(aux);
			do {
				System.out.println(flow.readUTF());
			} while (flow.available() > 0);
			flow.close();
			skClient.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}
}
