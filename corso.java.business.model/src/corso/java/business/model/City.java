package corso.java.business.model;

/**
 * Definizione di una città.
 * 
 * @author nello
 *
 */
public class City extends Place {
	/**
	 * Provincia di appartenenza.
	 */
	private String province;
	/**
	 * Sigla della provincia.
	 */
	private String acronym;
	/**
	 * Regione di appartenenza.
	 */
	private String region;
	/**
	 * Circoscrizione di appartenenza.
	 */
	private String area;
	/**
	 * Codice catastale.
	 */
	private String fiscalCode;
	/**
	 * Popolazione.
	 */
	private long people;
	/**
	 * Capoluogo di provincia.
	 */
	private boolean capital;

	/**
	 * Costruttore.
	 */
	public City() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param id         la chiave.
	 * @param name       la denominazione.
	 * @param province   la provincia.
	 * @param region     la regione.
	 * @param area       la circoscrizione.
	 * @param fiscalCode il codice catastale.
	 * @param people     la popolazione.
	 */
	public City(long id, String name, String province, String acronym, boolean capital, String region, String area,
			String fiscalCode, long people) {
		super(id, name);
		setProvince(province);
		setRegion(region);
		setArea(area);
		setFiscalCode(fiscalCode);
		setPeople(people);
		setCapital(capital);
		setAcronym(acronym);
	}

	/**
	 * Indica se si tratta di un capoluogo di provincia.
	 * 
	 * @return
	 */
	public boolean isCapital() {
		return capital;
	}

	/**
	 * Imposta la condizione di capoluogo di provincia.
	 * 
	 * @param capital indica se la città è capoluogo di provincia.
	 */
	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	/**
	 * Ottiene la sigla della provincia.
	 * 
	 * @return la sigla della provincia.
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * Imposta la sigla della provincia.
	 * 
	 * @param acronym la sigla della provincia.
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym.trim().toUpperCase();
	}

	/**
	 * Ottiene la provincia.
	 * 
	 * @return la provincia di appartenenza.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * Ottiene la regione.
	 * 
	 * @return la regione di appartenenza.
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Ottiene la circoscrizione.
	 * 
	 * @return la circoscrizione di appartenenza.
	 */
	public String getArea() {
		return area;
	}

	/**
	 * Ottiene il codice catastale.
	 * 
	 * @return il codice catastale della città.
	 */
	public String getFiscalCode() {
		return fiscalCode.trim().toUpperCase();
	}

	/**
	 * Ottiene la popolazione.
	 * 
	 * @return la popolazione della città.
	 */
	public long getPeople() {
		return people;
	}

	/**
	 * Imposta la provincia.
	 * 
	 * @param province la provincia.
	 */
	public void setProvince(String province) {
		this.province = province.trim();
	}

	/**
	 * Imposta la regione.
	 * 
	 * @param region la regione.
	 */
	public void setRegion(String region) {
		this.region = region.trim();
	}

	/**
	 * Imposta la circoscrizione.
	 * 
	 * @param area la circoscrizione.
	 */
	public void setArea(String area) {
		this.area = area.trim();
	}

	/**
	 * Imposta il codice catastale.
	 * 
	 * @param fiscalCode il codice catastale.
	 */
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode.trim().toUpperCase();
	}

	/**
	 * Imposta la popolazione.
	 * 
	 * @param people la popolazione.
	 */
	public void setPeople(long people) {
		this.people = people;
	}

	/**
	 * Controlla l'uguaglianza tra due città.
	 * 
	 * @return un valore booleano che indica se le città sono uguali, prendendo in
	 *         considerazione la denominazione e la provincia.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof City) {
			City ref = (City) obj;
			return ref.getName().equalsIgnoreCase(getName()) && ref.getProvince().equalsIgnoreCase(province);
		}
		return false;
	}

	/**
	 * Ottiene la rappresentazione di una città.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getName());
		if (acronym != null)
			result.append(" (").append(acronym).append(")");
		return result.toString();
	}
}
