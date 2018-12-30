import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DailyAdviceServer {

	public void go() {
		try {
			ServerSocket serverSock = new ServerSocket(4242);
			
			Scanner cin = new Scanner(System.in);
			
			while (true) {
				Socket sock = serverSock.accept();
				
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
				BufferedReader reader = new BufferedReader(streamReader);
				
				if (sock.isConnected()) {
					writer.println("What do you want?");
					writer.flush();
				}
				
				while (sock.isConnected()) {
					
					while(!reader.ready()){
						;
					}
					
					String inputMessage = reader.readLine();
					System.out.println("Client says: " + inputMessage);
					
					String outputMessage = cin.nextLine();
					
					writer.println(outputMessage);
					writer.flush();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // close go

	public static void main(String[] args) {
		DailyAdviceServer server = new DailyAdviceServer();
		server.go();
	}
}