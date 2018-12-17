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
	public InputHandler(Socket socket, DataInputStream dis, DataOutputStream dos,
			List<MeasurementPoint> measurementPoints) {
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		this.measurementPoints = measurementPoints;
	}

	@Override
	public void run() {
		String received;
		LocalDate receivedDate;

		// Inform user about service and ask them what they want
		try {
			dos.writeUTF(informAboutService());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// TODO mit boolean lösen
		while (true) {
			try {
				// receive the answer from client
				received = dis.readUTF();
				dos.writeUTF("You asked for weather information about: " + received);

				// validate input of client
				receivedDate = validateInput(received);

				// TODO equals(null) wird eine NullPointerException
				if (receivedDate == null) {
					String informAboutBadInput = ("Wrong input! It is only information available for 2018-12-01 until 2018-12-07."
							+ System.lineSeparator() + "Enter the date in the correct format yyyy-mm-dd."
							+ System.lineSeparator() + "Try again or type exit to terminate connection."
							+ System.lineSeparator());

					dos.writeUTF(informAboutBadInput);
				}

				// TODO löst Socket Exception aus
				else if (received.equals("Exit")) {
					System.out.println("Client " + this.socket + " sends exit...");
					System.out.println("Closing this connection.");
					this.socket.close();
					System.out.println("Connection closed");
					break;
				}

				else {
					// calculate measurements for given date

					// TEST
					int i = 0;
					i++;
					System.out.println(i + ". test Mal in else Block");

					Measurement measurement = null;
					measurement = new Measurement(receivedDate, measurementPoints);
					List<MeasurementPoint> measurementPointsForGivenDate = measurement
							.getMeasurementPointsForGivenDate();
					List<Double> measurements = measurement.getMeasurements();

					// write on output stream based on the answer from the client
					Response response = new Response(measurementPointsForGivenDate, measurements);
					dos.writeUTF(response.getResponse() + System.lineSeparator() + System.lineSeparator()
							+ "Next request please:");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			// damit bei zweiter invalider Anfrage auch Validierung stimmt
			// receivedDate = null;
		}

		try {
			// closing resources
			this.dis.close();
			this.dos.close();

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
