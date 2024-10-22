package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

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
				List<Double> numbers = new ArrayList<Double>();
				do {
					numbers.add(flow.readDouble());
				} while (flow.available() > 0);
				System.out.println(numbers.size() + " numbers recieved.");
				double sum = 0;
				for (double num : numbers) {
					sum += num;
				}
				double avg = sum / numbers.size();
				System.out.println("AVG: " + avg);
				if (numbers.size() > 0) {
					OutputStream oAux = skClient.getOutputStream();
					DataOutputStream oFlow = new DataOutputStream(oAux);
					oFlow.writeDouble(avg);
				}
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