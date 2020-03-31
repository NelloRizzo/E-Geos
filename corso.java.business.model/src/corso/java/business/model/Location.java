package corso.java.business.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Location {
	private float longitude;
	private float latitude;

	public Location() {
		this(0, 0);
	}

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
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Location forCity(String name) {
		try {
			URL url = new URL("https://maps.google.it/?q=" + name);
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
