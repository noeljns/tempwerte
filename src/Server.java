import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	private List<Thread> activeInputHandler;

	public Server(int port) {

		reader = new CSVReader();
		measurementPoints = reader.parseCSV();
		
		// server is listenin on given port
		try {
			this.serverSocket = new ServerSocket(port);
			//this.serverSocket.setSoTimeout(5000);
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

	// TODO oder sollte InputHandler nur Input parsen und Server calulcates?
	// TODO Server muss kein Runnable mehr implementieren, oder??
	
	// stellt TCP Socket API bereit
	// er liest Temperaturwerte aus einer CSV Datei ein, welche zuvor von uns
	// angelegt wurde
	// Wetterdaten werde über die TCP Socket API (Stream Sockets) dieses Servers
	// abgefragt
	// wenn Server Anfrage für Datum bekommt, das nicht 1.12-7.12 ist, wird Fehler
	// an Client zurückgegeben
	// Server kann Anfragen von mehreren Clients gleichzeitig bearbeiten, ist also
	// mutli threaded implementiert

	// Connection
	// CMD


}
