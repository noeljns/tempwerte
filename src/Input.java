import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Klasse um Input zu bearbeiten
 * 
 * @author jns
 *
 */
public class Input {
	private Scanner scanner;

	public Input() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Methode um ueber Input Prozess zu informieren
	 */
	public void inform() {
		System.out.println("Welcome to the Berlin Weather Information Service.");
		System.out.println("Enter the date in the format yyyy-mm-dd you wish to have weather information of!");
		System.out.println("It is only information available for 2018-12-01 until 2018-12-07.");
		System.out.println("");		
	}
	
	/**
	 * Methode um ein Datum zu parsen
	 * @return geparstes Datum
	 */
	public LocalDate parse() {
		String dateString = scanner.next();
		System.out.println("You demanded information about: " + dateString);
		System.out.println("");

		try {
			LocalDate date = LocalDate.parse(dateString);	
			
			// überprüfen, ob Datum in Zeitraum liegt
			if (date.isAfter(LocalDate.of(2018, 12, 07)) || date.isBefore(LocalDate.of(2018, 12, 01))) {
				System.out.println("Wrong input! It is only information available for 2018-12-01 until 2018-12-07.");
				System.out.println("Try again.");
				return null;
			}
							
			return date;
		}
		catch (DateTimeParseException e){
			System.out.println("Wrong input! Enter the date in the correct format yyyy-mm-dd.");
			System.out.println("Try again.");
			return null;
		}
	}
}