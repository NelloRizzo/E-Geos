package corso.java.businessmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Location implements Serializable {
	/**
	 * Serialization version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The longitude.
	 */
	private float longitude;
	/**
	 * The latitude.
	 */
	private float latitude;

	/**
	 * Constructor.
	 */
	public Location() {
		this(0, 0);
	}

	/**
	 * Constructor.
	 * 
	 * @param longitude the longitude.
	 * @param latitude  the latitude.
	 */
	public Location(float longitude, float latitude) {
		setLongitude(longitude);
		setLatitude(latitude);
	}

	/**
	 * @return the latitude
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public Location setLatitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public Location setLongitude(float longitude) {
		this.longitude = longitude;
		return this;
	}

	/**
	 * @return the string representation about this location.
	 */
	@Override
	public String toString() {
		float lat = latitude % 360;
		float lon = longitude % 360;
		String ns = lat >= 0 ? "N" : "S";
		String eo = lat >= 0 ? "O" : "E";
		return String.format("(%d%s, %d%s)", Math.abs(lon), ns, Math.abs(lat), eo);
	}

	/**
	 * The writeObject method is responsible for writing the state of theobject for
	 * its particular class so that the correspondingreadObject method can restore
	 * it. The default mechanism for savingthe Object's fields can be invoked by
	 * callingout.defaultWriteObject. The method does not need to concernitself with
	 * the state belonging to its superclasses or subclasses. State is saved by
	 * writing the individual fields to theObjectOutputStream using the writeObject
	 * method or by using themethods for primitive data types supported by
	 * DataOutput.
	 */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		out.writeLong(serialVersionUID);
		out.writeFloat(longitude);
		out.writeFloat(latitude);
	}

	/**
	 * The readObject method is responsible for reading from the stream andrestoring
	 * the classes fields. It may call in.defaultReadObject to invokethe default
	 * mechanism for restoring the object's non-static andnon-transient fields. The
	 * defaultReadObject method uses information inthe stream to assign the fields
	 * of the object saved in the stream with thecorrespondingly named fields in the
	 * current object. This handles the casewhen the class has evolved to add new
	 * fields. The method does not need toconcern itself with the state belonging to
	 * its superclasses or subclasses. State is restored by reading data from the
	 * ObjectInputStream forthe individual fields and making assignments to the
	 * appropriate fieldsof the object. Reading primitive data types is supported by
	 * DataInput.
	 */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		if (in.readLong() != serialVersionUID)
			throw new ClassNotFoundException("Version mismatch.");
		longitude = in.readFloat();
		latitude = in.readFloat();
	}

	public static Location forCity(String name) {
		try {
			URL url = new URL(
					"https://maps.google.it/?q=" + URLEncoder.encode(name, StandardCharsets.UTF_8).toString());
			URLConnection conn = url.openConnection();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				List<String> strings = br.lines().collect(Collectors.toList());
				String page = String.join(" ", strings);
				Pattern pattern = Pattern.compile(
						"window.APP_INITIALIZATION_STATE=\\[\\[\\[(?<z>\\d+\\.\\d+),(?<longitude>\\d+\\.\\d+),(?<latitude>\\d+\\.\\d+)\\]");
				Matcher m = pattern.matcher(page);
				if (m.find())
					return new Location(Float.parseFloat(m.group("longitude")), Float.parseFloat(m.group("latitude")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Location();
	}
}
