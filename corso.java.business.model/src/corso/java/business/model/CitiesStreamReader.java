package corso.java.business.model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Lettore di città da stream di testo.
 * 
 * @author nello
 *
 */
public class CitiesStreamReader {
	/**
	 * Lo stream dal quale leggere.
	 */
	private InputStream stream;
	/**
	 * Indice del campo 'nome'.
	 */
	private int idxName;
	/**
	 * Indice del campo 'chiave'.
	 */
	private int idxId;
	/**
	 * Indice del campo 'provincia'.
	 */
	private int idxProvince;
	/**
	 * Indice del campo 'sigla'.
	 */
	private int idxAcronym;
	/**
	 * Indice del campo 'capoluogo'.
	 */
	private int idxCapital;
	/**
	 * Indice del campo 'regione'.
	 */
	private int idxRegion;
	/**
	 * Indice del campo 'circoscrizione'.
	 */
	private int idxArea;
	/**
	 * Indice del campo 'codice ISTAT'.
	 */
	private int idxIstat;
	/**
	 * Indice del campo 'popolazione'.
	 */
	private int idxPeople;
	/**
	 * Separatore dei campi.
	 */
	private String separator;
	/**
	 * Eventuali delimitatori delle stringhe.
	 */
	private String stringDelimiter;
	/**
	 * Eventuale sequenza di caratteri da saltare a inizio riga.
	 */
	private String lineStartSkip;
	/**
	 * Eventuale sequenza di caratteri da saltare a fine riga.
	 */
	private String lineEndSkip;
	/**
	 * Linee di intestazione da saltare.
	 */
	private int headerLines;

	/**
	 * Costruttore. Imposta i valori degli indici dei campi a quelli presenti nel
	 * file distribuito dal sito ISTAT all'indirizzo
	 * https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv.
	 * 
	 * @see https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv
	 * @since febbraio 2020
	 */
	public CitiesStreamReader(InputStream stream) {
		this.stream = stream;
		this.idxArea = 9;
		this.idxId = 3;
		this.idxIstat = 18;
		this.idxName = 6;
		this.idxPeople = 19;
		this.idxProvince = 11;
		this.idxCapital = 12;
		this.idxAcronym = 13;
		this.idxRegion = 10;
		this.separator = ";";
		this.headerLines = 3;
		this.stringDelimiter = null;
		this.lineEndSkip = null;
		this.lineStartSkip = null;
	}

	/**
	 * Costruttore.
	 * 
	 * @param idxId       Indice del campo 'chiave'.
	 * @param idxName     Indice del campo 'denominazione'.
	 * @param idxProvince Indice del campo 'provincia'.
	 * @param idxRegion   Indice del campo 'regione'.
	 * @param idxArea     Indice del campo 'circoscrizione'.
	 * @param idxPeople   Indice del campo 'popolazione'.
	 * @param idxIstat    Indice del campo 'codice ISTAT'.
	 * @param idxCapital  Indice del campo 'capoluogo'.
	 * @param idxAcronym  Indice del campo 'sigla'.
	 */
	public CitiesStreamReader(int idxId, int idxName, int idxProvince, int idxAcronym, int idxCapital, int idxRegion,
			int idxArea, int idxPeople, int idxIstat) {
		this(null);
		this.idxArea = idxArea;
		this.idxId = idxId;
		this.idxIstat = idxIstat;
		this.idxName = idxName;
		this.idxPeople = idxPeople;
		this.idxProvince = idxProvince;
		this.idxRegion = idxRegion;
	}

	/**
	 * Ottiene il separatore di campo.
	 * 
	 * @return il separatore di campo.
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * Ottiene il delimitatore di stringa.
	 * 
	 * @return il delimitatore di stringa.
	 */
	public String getStringDelimiter() {
		return stringDelimiter;
	}

	/**
	 * Ottiene la parte da saltare ad inizio riga.
	 * 
	 * @return la parte da saltare ad inizio riga.
	 */
	public String getLineStartSkip() {
		return lineStartSkip;
	}

	/**
	 * Ottiene la parte da saltare a fine riga.
	 * 
	 * @return la parte da saltare ad fine riga.
	 */
	public String getLineEndSkip() {
		return lineEndSkip;
	}

	/**
	 * Ottiene il numero di righe di intestazione.
	 * 
	 * @return il numero di righe di intestazione.
	 */
	public int getHeaderLines() {
		return headerLines;
	}

	/**
	 * Imposta il separatore di campo.
	 * 
	 * @param separator separatore di campo.
	 */
	public CitiesStreamReader setSeparator(String separator) {
		this.separator = separator;
		return this;
	}

	/**
	 * Imposta il delimitatore di stringa.
	 * 
	 * @param stringDelimiter il delimitatore di stringa.
	 */
	public CitiesStreamReader setStringDelimiter(String stringDelimiter) {
		this.stringDelimiter = stringDelimiter;
		return this;
	}

	/**
	 * Imposta la stringa da saltare a inizio riga.
	 * 
	 * @param lineStartSkip la stringa da saltare a inizio riga.
	 */
	public CitiesStreamReader setLineStartSkip(String lineStartSkip) {
		this.lineStartSkip = lineStartSkip;
		return this;
	}

	/**
	 * Imposta la stringa da saltare a fine riga.
	 * 
	 * @param lineEndSkip la stringa da saltare a fine riga.
	 */
	public CitiesStreamReader setLineEndSkip(String lineEndSkip) {
		this.lineEndSkip = lineEndSkip;
		return this;
	}

	/**
	 * Imposta il numero di righe di intestazione.
	 * 
	 * @param headerLines il numero di righe di intestazione.
	 */
	public CitiesStreamReader setHeaderLines(int headerLines) {
		this.headerLines = headerLines;
		return this;
	}

	/**
	 * Imposta lo stream da cui leggere.
	 * 
	 * @param stream stream di lettura.
	 */
	public CitiesStreamReader setStream(InputStream stream) {
		this.stream = stream;
		return this;
	}

	/**
	 * Elabora lo stream per leggere le città.
	 * 
	 * @return la lista di città lette dallo stream di input.
	 */
	public List<City> process() {
		// processo asincrono
		FutureTask<List<City>> result = new FutureTask<List<City>>(new Callable<List<City>>() {
			public java.util.List<City> call() throws Exception {
				List<City> list = new ArrayList<City>();
				try (Scanner s = new Scanner(new BufferedInputStream(stream))) {
					String line = null;
					for (int skip = 0; skip < headerLines; ++skip)
						line = s.nextLine();
					while (s.hasNextLine()) {
						line = s.nextLine();
						if (lineStartSkip != null)
							line = line.substring(lineStartSkip.length());
						if (lineEndSkip != null)
							line = line.substring(0, line.length() - lineEndSkip.length());
						String[] parts = line.split(separator);
						City city = new City();
						if (idxArea >= 0)
							city.setArea(parseString(parts[idxArea].trim()));
						if (idxId >= 0)
							city.setId(Integer.parseInt(parts[idxId].trim()));
						if (idxIstat >= 0)
							city.setFiscalCode(parseString(parts[idxIstat].trim()));
						if (idxName >= 0)
							city.setName(parseString(parts[idxName].trim()));
						if (idxPeople >= 0)
							city.setPeople(Long.parseLong(parts[idxPeople].trim().replace(".", "").replace(",", "")));
						if (idxProvince >= 0)
							city.setProvince(parseString(parts[idxProvince].trim()));
						if (idxRegion >= 0)
							city.setRegion(parseString(parts[idxRegion].trim()));
						if (idxCapital >= 0)
							city.setCapital(parts[idxCapital].trim().charAt(0) != '0');
						if (idxAcronym >= 0)
							city.setAcronym(parts[idxAcronym].trim());
						list.add(city);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

			/**
			 * Estrae dalla stringa la parte da recuperare.
			 * 
			 * @param string la stringa da processare.
			 * @return la stringa processata.
			 */
			private String parseString(String string) {
				int len = 0;
				if (stringDelimiter == null || (len = stringDelimiter.length()) == 0)
					return string;

				return string.substring(0, string.length() - stringDelimiter.length()).substring(len);
			};
		});
		result.run();
		try {
			return result.get();
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}
	}
}
