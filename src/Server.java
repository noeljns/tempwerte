import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Klasse die einen Server repräsentiert der eine TCP Socket Api bereitstellt
 * 
 * @author jns
 *
 */
public class Server implements Runnable {
	private ServerSocket serverSocket;
	private CSVReader reader;
	private List<MeasurementPoint> measurementPoints;

	public Server(int port) {

		reader = new CSVReader();
		measurementPoints = reader.parseCSV();
		
		// server is listenin on given port
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode um Server zu starten
	 */
	@Override
	public void run() {
		
		while (true) {
			Socket socket = null;

			try {
				// socket object to receive incoming client requests				
				socket = serverSocket.accept();

				System.out.println("A new client is connected : " + socket);
				System.out.println("Assigning new thread for this client");
				System.out.println("");

				// create a new thread object
				Thread t = new InputHandler(socket, measurementPoints);
				// Invoking the start() method
				t.start();

			} catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
}
