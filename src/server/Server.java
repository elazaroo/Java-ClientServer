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
			
			// Abort when maxClients is arrived
			for (int clientNum = 0; clientNum < maxClients; clientNum++) {
				System.out.println("Serving client " + clientNum);
				
				Socket skClient = skServer.accept();				
				InputStream iAux = skClient.getInputStream();
				DataInputStream iFlow = new DataInputStream(iAux);
				
				// Declare ArrayList numbers where doubles will be stored
				List<Double> numbers = new ArrayList<Double>();
				do {
					numbers.add(iFlow.readDouble());
				} while (iFlow.available() > 0);
				System.out.println(numbers.size() + " numbers recieved.");
				
				// Use for (each number of ArrayList) and sum in a variable
				double sum = 0;
				for (double num : numbers) {
					sum += num;
				}
				
				double avg = sum / numbers.size();
				System.out.println("AVG: " + avg);
				
				// At least one number in ArrayList
				if (numbers.size() > 0) {
					
					// Send response to client
					OutputStream oAux = skClient.getOutputStream();
					DataOutputStream oFlow = new DataOutputStream(oAux);
					oFlow.writeDouble(avg);
					oFlow.close();
				}
				iFlow.close();
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