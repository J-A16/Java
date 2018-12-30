import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DailyAdviceClient {
	public void go() {
		try {
			Socket s = new Socket("127.0.0.1", 4242);
			
			Scanner cin = new Scanner(System.in);

			PrintWriter writer = new PrintWriter(s.getOutputStream());
			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			
			
			while (true) {
				
				while(!reader.ready()){
					;
				}
				
				String inputMessage = reader.readLine();
				System.out.println("Server says: " + inputMessage);
				
				String outputMessage = cin.nextLine();
				
				writer.println(outputMessage);
				writer.flush();
				
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DailyAdviceClient client = new DailyAdviceClient();
		client.go();
	}
}