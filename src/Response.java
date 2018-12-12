import java.time.LocalDate;
import java.util.List;

public class Response {
	// Exception
	// String
	// enthält Measurement und MeasurementPoint des Tages

	private LocalDate date;
	private List<MeasurementPoint> measurementPointsForGivenDate;
	private List<Double> measurements;

	public Response(LocalDate date, List<MeasurementPoint> measurementPointsForGivenDate, List<Double> measurements) {
		this.date = date;
		this.measurementPointsForGivenDate = measurementPointsForGivenDate;
		this.measurements = measurements;
	}

	public void sendResponse() {
		System.out.println("Messungen für den Tag: " + date);
		
		System.out.println("Messungen pro Stunde:");
		for (MeasurementPoint point : measurementPointsForGivenDate) {
			System.out.println("Uhrzeit: " + point.getTime() + ", Temperatur: " + point.getTemperature());
			System.out.println("");
		}

		System.out.println("Minimun: " + Double.toString(measurements.get(0)));
		System.out.println("Maximum: " + Double.toString(measurements.get(1)));
		System.out.println("Durchschnit: " + Double.toString(measurements.get(2)));
	}

}
