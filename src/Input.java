import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

// Input von Client bearbeiten
// Input: Datum
// Input: alles andere verboten, dann printen, dass nur Datum genommen wird

/**
 * Klasse um Input zu bearbeiten
 * @author jns
 *
 */
public class Input {
	private Scanner scanner;
	
	public Input() {
		scanner = new Scanner(System.in);
	}
	
	public LocalDate parse() {
		System.out.println("Welcome to the Berlin Weather Information Service");
		System.out.println("Enter the date in the format yyyy-mm-dd you wish to have weather information of!");
		System.out.println("Please consider that there is only information available for 2018-12-01 until 2018-12-07.");
		System.out.println("");

		String dateString = scanner.next();
		System.out.println("You demanded information about: " + dateString);
		System.out.println("");
		
		// convert to LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		try {
			LocalDate date = LocalDate.parse(dateString, formatter);
			return date;
		}
		catch(DateTimeParseException e) {
			System.out.println("Wrong input!");
			System.out.println("Enter the date in the correct format yyyy-mm-dd.");
			System.out.println("Please consider that there is only information available for 2018-12-01 until 2018-12-07.");

		}
		
		return null;
	}
}