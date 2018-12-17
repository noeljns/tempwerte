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

	public String getResponse() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (MeasurementPoint point : measurementPointsForGivenDate) {
			stringBuilder.append("Time: " + point.getTime() + ", Temperature: "
					+ point.getTemperature());
			stringBuilder.append(System.lineSeparator());

		}
		
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Minimun: " + Double.toString(measurements.get(0)));
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Maximum: " + Double.toString(measurements.get(1)));
		stringBuilder.append(System.lineSeparator());
		stringBuilder.append("Average: " + Double.toString(measurements.get(2)));
		stringBuilder.append(System.lineSeparator());

		return stringBuilder.toString();
	}

}
