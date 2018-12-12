import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasse um CSV Datei einzulesen
 * 
 * @author jns
 *
 */
public class CSVReader {
	private String csvFile; 
	private String line;
	private String cvsSplitBy;
	private List<MeasurementPoint> measurementPoints;
	
	public CSVReader() {
		// Achtung: bei Trang wiederum anderer Pfad
		this.csvFile = "/Users/jns/Workspace/tempwerte/temp_werte.csv";
		this.line = "";
		this.cvsSplitBy = ";";
		measurementPoints = new ArrayList<MeasurementPoint>();
	}
	
	/**
	 * Methode um Wetterinformationen aus einer CSV Datei in eine Objekt Liste zu mappen
	 * @return Liste mit Wetterinformationen wenn parsen geklappt hat, ansonsten null
	 */
	public List<MeasurementPoint> parseCSV() {
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			System.out.println("Wetterinformationen:");
			String headerLine = reader.readLine();
			
            while ((line = reader.readLine()) != null) {
                // use semicolon as separator
            		String[] weatherInfo = line.split(cvsSplitBy);
                
            		LocalDate date = LocalDate.parse(weatherInfo[0]);
            		LocalTime time = LocalTime.parse(weatherInfo[1]);
            		Double temperature = Double.parseDouble(weatherInfo[2]);
            		
            		MeasurementPoint measurementPoint = new MeasurementPoint(date, time, temperature);
            		measurementPoints.add(measurementPoint);
            }

            // TEST:
//            for(MeasurementPoint point : measurementPoints) {
//            	System.out.println("Tag: " + point.getDate() 
//                		+ ", Uhrzeit: " + point.getTime() 
//                		+ ", Temperatur: " + point.getTemperature());
//            }
            
            return measurementPoints;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;	
	}

}
