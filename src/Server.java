/**
 * Klasse die einen Server repräsentiert der eine TCP Socket Api bereitstellt
 * @author jns
 *
 */
public class Server implements Runnable {

	// stellt TCP Socket API bereit
	// er liest Temperaturwerte aus einer CSV Datei ein, welche zuvor von uns angelegt wurde
	// Wetterdaten werde über die TCP Socket API (Stream Sockets) dieses Servers abgefragt 
	// wenn Server Anfrage für Datum bekommt, das nicht 1.12-7.12 ist, wird Fehler an Client zurückgegeben
	// Server kann Anfragen von mehreren Clients gleichzeitig bearbeiten, ist also mutli threaded implementiert
	
	// Connection
	// CMD
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
