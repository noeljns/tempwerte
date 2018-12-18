/**
 * Main Klasse um Server zu starten
 * @author jns
 *
 */
public class Main {

	public static void main(String[] args) {
		Thread client = new Thread(new Client());
		Thread server = new Thread(new Server(5056));

		server.start();
		client.start();
	}

}
