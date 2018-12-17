import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
	// gibt ein Datum an mit Hilfe von Input Klasse
	// Datum wird an Server geschickt
	// bekommt Response zurück
	// oder Fehler, dass Datum nicht existiert in Datenbank

	private Scanner scanner;

	public Client(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Methode um Client zu starten
	 */
	@Override
	public void run() {		
		try {
			// getting localhost ip
			InetAddress ip = InetAddress.getByName("localhost");

			// establish the connection with server port 5056
			Socket socket = new Socket(ip, 5056);			
			
			// obtaining input and out streams
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			// the following loop performs the exchange of
			// information between client and client handler
			while (true) {
				System.out.println(dis.readUTF());
				String tosend = scanner.nextLine();
				dos.writeUTF(tosend);

				// If client sends exit,close this connection and then break from the while loop
				if (tosend.equals("Exit")) {
					System.out.println("Closing this connection : " + socket);
					socket.close();
					System.out.println("Connection closed");
					break;
				}

				// printing measurement response as requested by client
				String received = dis.readUTF();
				System.out.println(received);
			}

			// closing resources
			scanner.close();
			dis.close();
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
