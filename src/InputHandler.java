import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * 
 * Klasse um Input des Clients durch einen Server zu bearbeiten
 * 
 * @author Trang, jns
 *
 */
class InputHandler extends Thread {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private List<MeasurementPoint> measurementPoints;

	// Constructor
	public InputHandler(Socket socket, List<MeasurementPoint> measurementPoints) throws IOException {
		this.socket = socket;
		// obtaining input and out streams
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.measurementPoints = measurementPoints;
	}

	@Override
	public void run() {
		// Inform user about service and ask them what they want
		try {
			dos.writeUTF(informAboutService());
			dos.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// TODO mit boolean lösen
		boolean run = true;
		while (run) {
			try {
				// receive the answer from client
				String received = dis.readUTF();
				dos.writeUTF("You asked for weather information about: " + received);
				//dos.flush();

				run = !"Exit".equals(received);
				
				if (run) {
					// validate input of client
					LocalDate receivedDate = validateInput(received);
					
					if (receivedDate == null) {
						String informAboutBadInput = ("Wrong input! It is only information available for 2018-12-01 until 2018-12-07."
								+ System.lineSeparator() + "Enter the date in the correct format yyyy-mm-dd."
								+ System.lineSeparator() + "Try again or type exit to terminate connection."
								+ System.lineSeparator());

						dos.writeUTF(informAboutBadInput);
					}
					else {
						// calculate measurements for given date
						Measurement measurement = new Measurement(receivedDate, measurementPoints);
						List<MeasurementPoint> measurementPointsForGivenDate = measurement.getMeasurementPointsForGivenDate();
						List<Double> measurements = measurement.getMeasurements();

						// write on output stream based on the answer from the client
						Response response = new Response(measurementPointsForGivenDate, measurements);
						dos.writeUTF(response.getResponse() + System.lineSeparator() + System.lineSeparator()
								+ "Next request please:");
					}
				}
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// damit bei zweiter invalider Anfrage auch Validierung stimmt
			// receivedDate = null;
		}

		try {
			System.out.println("Client " + this.socket + " sends exit...");
			System.out.println("Closing this connection.");
			
			// closing resources
			this.dis.close();
			this.dos.close();
			this.socket.close();
			System.out.println("Connection closed");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode um ueber Input Prozess zu informieren
	 */
	private String informAboutService() {
		String info = ("Welcome to the Berlin Weather Information Service." + System.lineSeparator()
				+ "Enter the date in the format yyyy-mm-dd you wish to have weather information of!"
				+ System.lineSeparator() + "It is only information available for 2018-12-01 until 2018-12-07."
				+ System.lineSeparator() + "Type Exit to terminate connection." + System.lineSeparator());

		return info;
	}

	/**
	 * Methode um Input zu validieren
	 * 
	 * @return wenn valide dann geparstes Datum, ansonsten null
	 */
	private LocalDate validateInput(String received) {
		try {
			// parse received String to Local Date object
			LocalDate date = LocalDate.parse(received);

			if (date.isAfter(LocalDate.of(2018, 12, 07)) || date.isBefore(LocalDate.of(2018, 12, 01))) {
				// received user input is not in the given time span
				return null;
			}

			// date is valid
			return date;

		} catch (DateTimeParseException e) {
			// received user input is not correctly formatted
			return null;
		}
	}

}
