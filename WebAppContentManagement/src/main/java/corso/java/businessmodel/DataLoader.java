package corso.java.businessmodel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * A simple data loader from stream.
 * 
 * @author nello
 *
 */
public class DataLoader {
	/**
	 * List of cities.
	 */
	private Set<City> cities;
	/**
	 * List of regions.
	 */
	private Set<Region> regions;
	/**
	 * List of provinces.
	 */
	private Set<Province> provinces;
	/**
	 * List of areas.
	 */
	private Set<Area> areas;
	/**
	 * Index of area in the line.
	 */
	private int idxAreaName;
	/**
	 * Index of city in the line.
	 */
	private int idxCityKey;
	/**
	 * Index of the fiscal code in the line.
	 */
	private int idxFiscalCode;
	/**
	 * Index of the city's name in the line.
	 */
	private int idxCityName;
	/**
	 * Index of the people in the line.
	 */
	private int idxPeople;
	/**
	 * Index of the province's name in the line.
	 */
	private int idxProvinceName;
	/**
	 * Index of captial field in the line.
	 */
	private int idxCapital;
	/**
	 * Index of the acronym in the line.
	 */
	private int idxAcronym;
	/**
	 * Index of region's name in the line.
	 */
	private int idxRegionName;
	/**
	 * Index of region's key in the line.
	 */
	private int idxRegionKey;
	/**
	 * Index of the area's key in the line.
	 */
	private int idxAreaKey;
	/**
	 * Index of the province's key in the line.
	 */
	private int idxProvinceKey;
	/**
	 * Index of the separator in the csv input stream.
	 */
	private String separator;
	/**
	 * Total of lines to skip from input stream.
	 */
	private int headerLines;
	/**
	 * String's delimiter in the csv.
	 */
	private String stringDelimiter;
	/**
	 * String to skip at begin of line.
	 */
	private String lineEndSkip;
	/**
	 * String to skip at end of line.
	 */
	private String lineStartSkip;
	/**
	 * The input stream.
	 */
	private InputStream inputStream;
	/**
	 * Encoding used in the stream.
	 */
	private String encoding;

	/**
	 * Costruttore.
	 */
	public DataLoader() {
		this.idxAreaName = 9;
		this.idxCityKey = 14;
		this.idxFiscalCode = 18;
		this.idxCityName = 6;
		this.idxPeople = 19;
		this.idxProvinceName = 11;
		this.idxCapital = 12;
		this.idxAcronym = 13;
		this.idxRegionName = 10;
		this.idxRegionKey = 0;
		this.idxAreaKey = 8;
		this.idxProvinceKey = 2;
		this.separator = ";";
		this.encoding = "utf-8";
		this.headerLines = 3;
		this.stringDelimiter = null;
		this.lineEndSkip = null;
		this.lineStartSkip = null;
		cities = new HashSet<>();
		regions = new HashSet<>();
		provinces = new HashSet<>();
		areas = new HashSet<>();
	}

	/**
	 * Clear the data.
	 */
	private void clearData() {
		areas.clear();
		regions.clear();
		provinces.clear();
		cities.clear();
	}

	/**
	 * 
	 * @return the areas.
	 */
	public Set<Area> getAreas() {
		return areas;
	}

	/**
	 * 
	 * @return the cities.
	 */
	public Set<City> getCities() {
		return cities;
	}

	/**
	 * 
	 * @return the encoding.
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * 
	 * @return the number of skipped lines.
	 */
	public int getHeaderLines() {
		return headerLines;
	}

	/**
	 * 
	 * @return the index of the acronym field.
	 */
	public int getIdxAcronym() {
		return idxAcronym;
	}

	/**
	 * 
	 * @return the index of the area field.
	 */
	public int getIdxArea() {
		return idxAreaName;
	}

	/**
	 * @return the index of area key field.
	 */
	public int getIdxAreaKey() {
		return idxAreaKey;
	}

	/**
	 * 
	 * @return the index of the capital field.
	 */
	public int getIdxCapital() {
		return idxCapital;
	}

	/**
	 * 
	 * @return the index of city key field.
	 */
	public int getIdxCityKey() {
		return idxCityKey;
	}

	/**
	 * 
	 * @return the index of city name field.
	 */
	public int getIdxCityName() {
		return idxCityName;
	}

	/**
	 * 
	 * @return the index of fiscal code field.
	 */
	public int getIdxFiscalCode() {
		return idxFiscalCode;
	}

	/**
	 * 
	 * @return the index of the people field.
	 */
	public int getIdxPeople() {
		return idxPeople;
	}

	/**
	 * 
	 * @return the index of province field.
	 */
	public int getIdxProvince() {
		return idxProvinceName;
	}

	/**
	 * @return the the index of province key field.
	 */
	public int getIdxProvinceKey() {
		return idxProvinceKey;
	}

	/**
	 * @return the the index of region key field.
	 */
	public int getIdxRegionKey() {
		return idxRegionKey;
	}

	/**
	 * 
	 * @return the index of region name field.
	 */
	public int getIdxRegionName() {
		return idxRegionName;
	}

	/**
	 * 
	 * @return the string to skip at end of line.
	 */
	public String getLineEndSkip() {
		return lineEndSkip;
	}

	/**
	 * 
	 * @return the string to skip at begin of line.
	 */
	public String getLineStartSkip() {
		return lineStartSkip;
	}

	/**
	 * 
	 * @return the list of provinces.
	 */
	public Set<Province> getProvinces() {
		return provinces;
	}

	/**
	 * 
	 * @return the list of regions.
	 */
	public Set<Region> getRegions() {
		return regions;
	}

	/**
	 * 
	 * @return the separator of fields.
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * 
	 * @return the delimiter for string.
	 */
	public String getStringDelimiter() {
		return stringDelimiter;
	}

	/**
	 * Loads the data from the input stream.
	 * 
	 * @param retrieveLocation if {@code true} the city location is retrieved from
	 *                         Google Maps.
	 * @param clearData        if {@code true} the data were cleared before load.
	 */
	public void load(boolean retrieveLocation, boolean clearData) {
		if (clearData)
			clearData();
		try (BufferedReader s = new BufferedReader(
				new InputStreamReader(new BufferedInputStream(inputStream), encoding))) {
			String line = null;
			for (int skip = 0; skip < headerLines; ++skip)
				line = s.readLine();
			while ((line = s.readLine()) != null) {
				if (lineStartSkip != null)
					line = line.substring(lineStartSkip.length());
				if (lineEndSkip != null)
					line = line.substring(0, line.length() - lineEndSkip.length());
				String[] parts = line.split(separator);
				long areaKey = Long.parseLong(parts[idxAreaKey].trim());
				String areaName = parseString(parts[idxAreaName]);
				Area area = new Area(areaKey, areaName);
				long regionKey = Long.parseLong(parts[idxRegionKey].trim());
				String regionName = parseString(parts[idxRegionName]);
				Region region = new Region(regionKey, regionName, area);
				long provinceKey = Long.parseLong(parts[idxProvinceKey].trim());
				String provinceName = parseString(parts[idxProvinceName]);
				String acronym = parseString(parts[idxAcronym]);
				Province province = new Province(provinceKey, provinceName, acronym, region);
				long cityKey = Long.parseLong(parts[idxCityKey].trim());
				String cityName = parseString(parts[idxCityName]);
				String fiscalCode = parseString(parts[idxFiscalCode]);
				boolean capital = parts[idxCapital].charAt(0) == '1';
				long people = Long.parseLong(parts[idxPeople].trim().replace(",", "").replace(".", ""));
				Location location = null;
				if (retrieveLocation)
					location = Location.forCity(String.format("%s, %s, %s", cityName, acronym, region));
				City city = new City(cityKey, cityName, province, capital, fiscalCode, people, location);
				areas.add(area);
				regions.add(region);
				provinces.add(province);
				cities.add(city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	/**
	 * Parse a string before use it for field.
	 * 
	 * @param string string to parse.
	 * @return the parsed string.
	 */
	private String parseString(String string) {
		int len = 0;
		if (stringDelimiter == null || (len = stringDelimiter.length()) == 0)
			return string.trim();

		return string.trim().substring(0, string.length() - stringDelimiter.length()).substring(len);
	}

	/**
	 * 
	 * @param encoding the encoding.
	 */
	public DataLoader setEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	/**
	 * @param headerLines the header lines to skip.
	 */
	public DataLoader setHeaderLines(int headerLines) {
		this.headerLines = headerLines;
		return this;
	}

	/**
	 * @param idxAcronym the acronym index.
	 */
	public DataLoader setIdxAcronym(int idxAcronym) {
		this.idxAcronym = idxAcronym;
		return this;
	}

	/**
	 * @param idxAreaKey the idxAreaKey to set
	 */
	public void setIdxAreaKey(int idxAreaKey) {
		this.idxAreaKey = idxAreaKey;
	}

	/**
	 * @param idxAreaName the area name index.
	 */
	public DataLoader setIdxAreaName(int idxAreaName) {
		this.idxAreaName = idxAreaName;
		return this;
	}

	/**
	 * @param idxCapital the capital index.
	 */
	public DataLoader setIdxCapital(int idxCapital) {
		this.idxCapital = idxCapital;
		return this;
	}

	/**
	 * @param idxCityKey the city key index.
	 */
	public DataLoader setIdxCityKey(int idxCityKey) {
		this.idxCityKey = idxCityKey;
		return this;
	}

	/**
	 * 
	 * @param idxCityName the city name index.
	 */
	public DataLoader setIdxCityName(int idxCityName) {
		this.idxCityName = idxCityName;
		return this;
	}

	/**
	 * @param idxFiscalCode the fiscal code index.
	 */
	public DataLoader setIdxFiscalCode(int idxFiscalCode) {
		this.idxFiscalCode = idxFiscalCode;
		return this;
	}

	/**
	 * 
	 * @param idxPeople The people index.
	 */
	public DataLoader setIdxPeople(int idxPeople) {
		this.idxPeople = idxPeople;
		return this;
	}

	/**
	 * @param idxProvinceKey the idxProvinceKey to set.
	 */
	public void setIdxProvinceKey(int idxProvinceKey) {
		this.idxProvinceKey = idxProvinceKey;
	}

	/**
	 * 
	 * @param idxProvinceName the province name index.
	 */
	public DataLoader setIdxProvinceName(int idxProvinceName) {
		this.idxProvinceName = idxProvinceName;
		return this;
	}

	/**
	 * @param idxRegionKey the idxRegionKey to set.
	 */
	public void setIdxRegionKey(int idxRegionKey) {
		this.idxRegionKey = idxRegionKey;
	}

	/**
	 * 
	 * @param idxRegionName the region name index.
	 */
	public DataLoader setIdxRegionName(int idxRegionName) {
		this.idxRegionName = idxRegionName;
		return this;
	}

	/**
	 * 
	 * @param lineEndSkip the string to skip at end of line.
	 */
	public DataLoader setLineEndSkip(String lineEndSkip) {
		this.lineEndSkip = lineEndSkip;
		return this;
	}

	/**
	 * 
	 * @param lineStartSkip the string to skip at begin of line.
	 */
	public DataLoader setLineStartSkip(String lineStartSkip) {
		this.lineStartSkip = lineStartSkip;
		return this;
	}

	/**
	 * 
	 * @param separator the field's separator.
	 */
	public DataLoader setSeparator(String separator) {
		this.separator = separator;
		return this;
	}

	/**
	 * 
	 * @param inputStream the source stream.
	 */
	public DataLoader setSourceStream(InputStream inputStream) {
		this.inputStream = inputStream;
		return this;
	}

	/**
	 * 
	 * @param stringDelimiter the string delimiter.
	 */
	public DataLoader setStringDelimiter(String stringDelimiter) {
		this.stringDelimiter = stringDelimiter;
		return this;
	}
}
