import java.time.LocalDate;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		// hier wird später nur noch der Server gestartet
		
		// das kommt später in den Server
		CSVReader reader = new CSVReader();
		List<MeasurementPoint> measurementPoints = reader.parseCSV();		
		
		// check Input des Clients
		Input input = new Input();
		LocalDate date = input.parse();
		
		// Berechnungen ausführen und ausgeben
		Measurement measurement = new Measurement(date, measurementPoints);
		List<MeasurementPoint> measurementPointsForGivenDate = measurement.getMeasurementPointsForGivenDate();
		List<Double> measurements = measurement.getMeasurements();

		// Ausgabe
		new Response(date, measurementPointsForGivenDate, measurements).sendResponse();
       
	}

}
