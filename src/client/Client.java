package client;

import java.io.*;
import java.net.*;

public class Client {
	static final String HOST = "localhost";
	static final int PORT = 5000;
	
	public Client() {
		try {
			Socket skClient = new Socket(HOST, PORT);
			OutputStream aux = skClient.getOutputStream();
			DataOutputStream flow = new DataOutputStream(aux);
			flow.writeUTF("Eneko");
			InputStream iAux = skClient.getInputStream();
			DataInputStream iFlow = new DataInputStream(iAux);
			System.out.println(iFlow.readUTF());
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
