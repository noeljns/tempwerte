import java.time.LocalDate;
import java.util.List;

/**
 * Main Klasse um Server zu starten
 * @author jns
 *
 */
public class Main {

	public static void main(String[] args) {
		// hier wird später nur noch der Client und der Server gestartet
		
		// das kommt später in den Server
		CSVReader reader = new CSVReader();
		List<MeasurementPoint> measurementPoints = reader.parseCSV();		
		
		// Kommunikation mit Client
		Input input = new Input();
		input.inform();

		// prüfe Input von Client im Rahmen der Kommunikation mit Client
		LocalDate date = null;
		boolean success = false;
		while(!success) {
			date = input.parse();
			
			// parsen hat funktioniert, loop kann beendet werden
			// TODO: date.equals(null) wirft NullPointerException
			if(! (date == null) ) {
				success = true;
			}	
		}
			
		// Berechnungen ausführen
		Measurement measurement = new Measurement(date, measurementPoints);
		List<MeasurementPoint> measurementPointsForGivenDate = measurement.getMeasurementPointsForGivenDate();		
		List<Double> measurements = measurement.getMeasurements();

		// Ausgabe
		Response response = new Response(measurementPointsForGivenDate, measurements);
		response.getResponse();
       
	}

}
