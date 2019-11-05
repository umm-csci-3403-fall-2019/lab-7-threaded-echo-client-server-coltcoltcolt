package echoserver;

import java.net.*;
import java.io.*;
;
import java.net.Socket;

public class EchoClient implements Runnable {
	public static final int PORT_NUMBER = 6013;
	public static String server;

	public static void main(String[] args) throws IOException {
		// Use "127.0.0.1", i.e., localhost, if no server is specified.
		if (args.length == 0) {
			server = "127.0.0.1";
		} else {
			server = args[0];
		}
		EchoClient client = new EchoClient();
		client.start();
	}

	public void run(){
	    
    }


	private void start() throws IOException {

		Thread clientThreadIn = new Thread(new EchoServer() {
		    @Override
            public void run(){

            }
        });
		Thread clientThreadOut = new Thread(new EchoServer() {
        @Override
            public void run(){


            }
        });

		clientThreadIn.start();
		clientThreadOut.start();



		try {

			Socket socket = new Socket("localhost", PORT_NUMBER);
			int thebyte; //byte wasn't allowed ;(

			InputStream input = socket.getInputStream();
			OutputStream output = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			while ((thebyte = System.in.read()) != -1) {
				output.write(thebyte);
				System.out.write(input.read());
			}


			socket.close();


		}
		catch (
				ConnectException ce) {
			System.out.println("We were unable to connect to " + server);
			System.out.println("You should make sure the server is running.");
		} catch (
				IOException ioe) {
			System.out.println("We caught an unexpected exception");
			System.err.println(ioe);
		}


		// Put your code here.
	}
}