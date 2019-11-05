package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable {

	// REPLACE WITH PORT PROVIDED BY THE INSTRUCTOR
	public static final int PORT_NUMBER = 6013;
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	public void run(){}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket client = serverSocket.accept();
			OutputStream output = client.getOutputStream();
			Thread serverThread = new Thread(new EchoServer(){
				@Override
				public void run() {
					try {
						while (1 == 1) { //do always, this is a server
							int request = client.getInputStream().read(); //get the request from client's input
							if (request != -1) { //if the request is readable, print it to user
								output.write(request);
							} else { //if it isn't, don't, and get rid of buffer
								output.flush();
								break;
							}
						}
						client.close();
					} catch (
							IOException ioe) {
						System.out.println("We caught an unexpected exception");
						System.err.println(ioe);
					}
				}
			});
			serverThread.start(); // Close the client socket since we're done.
		}
	}
}