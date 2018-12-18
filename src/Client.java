import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
	private Scanner scanner;

	public Client() {
		this.scanner = new Scanner(System.in);
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

			// Read and print info from server
			System.out.println(dis.readUTF());

			// the following loop performs the exchange of
			// information between client and client handler
			boolean isRunning = true;
			while (isRunning) {
				String input = scanner.nextLine();
				isRunning = !"Exit".equals(input);
				dos.writeUTF(input);

				if (isRunning) {
					// print what date server got
					String received = dis.readUTF();
					System.out.println(received);

					// print measurement response as requested by client
					received = dis.readUTF();
					System.out.println(received);
				}
			}

			// If client sends exit,close this connection and then break from the while loop
			System.out.println("Closing this connection : " + socket);

			// closing resources
			scanner.close();
			dis.close();
			dos.close();
			socket.close();
			System.out.println("Connection closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
