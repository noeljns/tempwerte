import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Klasse die einen Messpunkt bestehend aus Uhrzeit und Temperatur in Grad Celcius repräsentiert
 * @author jns
 *
 */
public class MeasurementPoint {
	private LocalDate date;
	private LocalTime time;
	private Double temperature;
	
	public MeasurementPoint(LocalDate date, LocalTime time, Double temperature) {
		this.date = date;
		this.time = time;
		this.temperature = temperature;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public LocalTime getTime() {
		return time;
	}
	
	public Double getTemperature() {
		return temperature;
	}
}
