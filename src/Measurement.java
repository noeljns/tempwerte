import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;

/**
 * Klasse, um für ein bestimmtes Datum bestimmte Temperaturberechnungen
 * durchzuführen
 * 
 * @author jns
 *
 */
public class Measurement {
	private List<MeasurementPoint> measurementPointsForGivenDate;

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
		Iterator<MeasurementPoint> iterator = measurementPoints.iterator();
		while(iterator.hasNext()) {
			// falls gefordertes Datum nicht dem Datum in der Liste entspricht, entferne das MeasurePoint Objekt
			if(! (iterator.next().getDate().equals(date)) ) {
				iterator.remove();
			}
		
		}
				
		// Liste die nur noch MeasurePoint des geforderten Datums entspricht
		return measurementPoints;
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

		measurement.add(getMin());
		measurement.add(getMax());
		measurement.add(getAverage());

		return measurement;
	}

	private Double getMin() {
		OptionalDouble minOptional = measurementPointsForGivenDate.stream().mapToDouble(mp -> mp.getTemperature())
				.min();
		Double min = minOptional.getAsDouble();
		return min;
	}

	private Double getMax() {
		OptionalDouble maxOptional = measurementPointsForGivenDate.stream().mapToDouble(mp -> mp.getTemperature())
				.max();
		Double max = maxOptional.getAsDouble();
		return max;
	}

	private Double getAverage() {
		OptionalDouble averageOptional = measurementPointsForGivenDate.stream().mapToDouble(mp -> mp.getTemperature())
				.average();
		Double average = averageOptional.getAsDouble();
		return average;
	}

}
