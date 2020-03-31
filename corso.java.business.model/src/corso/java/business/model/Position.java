package corso.java.business.model;

/**
 * Una posizione geografica.
 * 
 * @author nello
 *
 */
public class Position {
	/**
	 * Latitudine.
	 */
	public final float latitude;
	/**
	 * Longitudine.
	 */
	public final float longitude;

	/**
	 * Costruttore.
	 * 
	 * @param latitude  la latitudine.
	 * @param longitude la longitudine.
	 */
	public Position(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Ottiene la rappresentazione sotto forma di stringa di una posizione
	 * geografica.
	 * 
	 * @return la rappresentazione nel formato {@code latitudineN|S,longitudineE|W}.
	 */
	@Override
	public String toString() {
		float latSign = Math.signum(latitude);
		float lonSign = Math.signum(longitude);
		float lat = latSign * latitude; // latitudine in valore assoluto
		float lon = lonSign * longitude; // longitudine in valore assoluto
		StringBuilder sb = new StringBuilder();
		return sb.append(lat).append(latSign > 0 ? 'N' : 'S').append(',').append(lon).append(lonSign > 0 ? 'E' : 'W')
				.toString();
	}
}
