import java.util.List;

public class Response {
	// Exception
	// String
	// enthält Measurement und MeasurementPoint des Tages

	private List<MeasurementPoint> measurementPointsForGivenDate;
	private List<Double> measurements;

	public Response(List<MeasurementPoint> measurementPointsForGivenDate, List<Double> measurements) {
		this.measurementPointsForGivenDate = measurementPointsForGivenDate;
		this.measurements = measurements;
	}

	public void sendResponse() {
		for (MeasurementPoint point : measurementPointsForGivenDate) {
			System.out.println("Time: " + point.getTime() + ", Temperature: "
					+ point.getTemperature());
		}

		System.out.println("");
		System.out.println("Minimun: " + Double.toString(measurements.get(0)));
		System.out.println("Maximum: " + Double.toString(measurements.get(1)));
		System.out.println("Average: " + Double.toString(measurements.get(2)));
	}

}
