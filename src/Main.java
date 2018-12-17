import java.util.Scanner;

/**
 * Main Klasse um Server zu starten
 * @author jns
 *
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Thread client = new Thread(new Client(scanner));
		Thread server = new Thread(new Server(5056));

		server.start();
		client.start();
	}

}
