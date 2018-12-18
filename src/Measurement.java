import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Klasse, um für ein bestimmtes Datum bestimmte Temperaturberechnungen
 * durchzuführen
 * 
 * @author jns
 *
 */
public class Measurement {
	private List<MeasurementPoint> measurementPointsForGivenDate;
	private Double minimum;
	private Double maximum;
	private Double average;

	/**
	 * Konstruktor
	 * 
	 * @param date
	 *            Datum für welches die Messungen berechnet werden sollen
	 * @param measurementPoints
	 */
	public Measurement(LocalDate date, List<MeasurementPoint> measurementPoints) {
		// reduziere die gespeicherten Messpunkte auf die des geforderten Datum
		measurementPointsForGivenDate = getMeasurePointsOfDate(date, measurementPoints);
		minimum = getMin();
		maximum = getMax();
		average = getAverage();
	}

	/**
	 * Hilfsmethode um aus eingelesenen Messpunkten, die für das geforderte Datum zu
	 * extrahieren
	 * 
	 * @param date
	 * @param measurementPoints
	 * @return Liste mit Messpunkten des geforderten Datums
	 */
	private List<MeasurementPoint> getMeasurePointsOfDate(LocalDate date, List<MeasurementPoint> measurementPoints) {
		// Liste die nur noch MeasurePoint des geforderten Datums entspricht
		return measurementPoints.stream()
			.filter(mp -> mp.getDate().equals(date))
			.collect(Collectors.toList());
	}

	/**
	 * Methode um Messpunkte des Tages herauszugeben
	 * 
	 * @return Messpunkte des Tages
	 */
	public List<MeasurementPoint> getMeasurementPointsForGivenDate() {
		return this.measurementPointsForGivenDate;
	}
	
	/**
	 * Methode um Minimum-, Maximum- und Durchschnittstemparatur eines Datums zu
	 * berechnen
	 * 
	 * @return Liste mit Minimum-, Maximum- und Durchschnittstemparatur eines Datum
	 */
	public List<Double> getMeasurements() {
		List<Double> measurement = new ArrayList<Double>();

		measurement.add(minimum);
		measurement.add(maximum);
		measurement.add(average);

		return measurement;
	}

	private Double getMin() {
		return measurementPointsForGivenDate.stream()
				.mapToDouble(mp -> mp.getTemperature())
				.min()
				.getAsDouble();
	}

	private Double getMax() {
		return measurementPointsForGivenDate.stream()
				.mapToDouble(mp -> mp.getTemperature())
				.max()
				.getAsDouble();
	}

	private Double getAverage() {
		return measurementPointsForGivenDate.stream()
				.mapToDouble(mp -> mp.getTemperature())
				.average()
				.getAsDouble();
	}

}
