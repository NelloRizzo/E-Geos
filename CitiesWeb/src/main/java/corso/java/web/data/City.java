package corso.java.web.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapping dei dati di una città.
 * 
 * @author nello
 *
 */
@Entity
@Table(name = "comuni")
public class City {
	/**
	 * Chiave.
	 */
	private Long id;
	/**
	 * Denominazione.
	 */
	private String name;
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
	private Long people;
	/**
	 * Capoluogo di provincia.
	 */
	private boolean capital;

	/**
	 * Costruttore.
	 */
	public City() {
	}

	/**
	 * Costruttore.
	 * 
	 * @param id         la chiave.
	 * @param name       la denominazione.
	 * @param province   la provincia.
	 * @param acronym    la sigla della provincia.
	 * @param capital    indica se la città è capoluogo di provincia.
	 * @param region     la regione.
	 * @param area       la circoscrizione.
	 * @param fiscalCode il codice catastale.
	 * @param people     la popolazione.
	 */
	public City(Long id, String name, String province, String acronym, boolean capital, String region, String area,
			String fiscalCode, Long people) {
		setId(id);
		setName(name);
		setProvince(province);
		setAcronym(acronym);
		setCapital(capital);
		setRegion(region);
		setArea(area);
		setFiscalCode(fiscalCode);
		setPeople(people);
	}

	/**
	 * Ottiene la chiave.
	 * 
	 * @return la chiave.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	/**
	 * Imposta la chiave.
	 * 
	 * @param id la chiave della città.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Ottiene la provincia.
	 * 
	 * @return la provincia di appartenenza.
	 */
	@Column(name = "provincia", nullable = true)
	public String getProvince() {
		return province;
	}

	/**
	 * Ottiene la sigla della provincia.
	 * 
	 * @return la sigla della provincia.
	 */
	@Column(name = "sigla", nullable = true)
	public String getAcronym() {
		return acronym;
	}

	/**
	 * Ottiene la regione.
	 * 
	 * @return la regione.
	 */
	@Column(name = "regione", nullable = true)
	public String getRegion() {
		return region;
	}

	/**
	 * Ottiene la circoscrizione.
	 * 
	 * @return la circoscrizione.
	 */
	@Column(name = "circoscrizione", nullable = true)
	public String getArea() {
		return area;
	}

	/**
	 * Ottiene il codice catastale.
	 * 
	 * @return il codice catastale.
	 */
	@Column(name = "codice_catastale", nullable = true)
	public String getFiscalCode() {
		return fiscalCode;
	}

	/**
	 * Ottiene la popolazione.
	 * 
	 * @return la popolazione.
	 */
	@Column(name = "popolazione", nullable = true)
	public Long getPeople() {
		return people;
	}

	/**
	 * Controlla se si tratta di capoluogo di provincia.
	 * 
	 * @return un valore booleano che indica se la città è un capoluogo di
	 *         provincia.
	 */
	@Column(name = "capoluogo", nullable = false)
	public boolean isCapital() {
		return capital;
	}

	/**
	 * Imposta la provincia.
	 * 
	 * @param province la provincia.
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Imposta la sigla.
	 * 
	 * @param acronym la sigla.
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * Imposta la regione.
	 * 
	 * @param region la regione.
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Imposta la circoscrizione.
	 * 
	 * @param area la circoscrizione.
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * Imposta il codice catastale.
	 * 
	 * @param fiscalCode il codice catastale.
	 */
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	/**
	 * Imposta la popolazione.
	 * 
	 * @param people la popolazione.
	 */
	public void setPeople(Long people) {
		this.people = people;
	}

	/**
	 * Imposta la condizione di capoluogo di provincia.
	 * 
	 * @param capital indica se la città è un capoluogo di provincia.
	 */
	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	/**
	 * Ottiene la denominazione.
	 * 
	 * @return la denominazione della città.
	 */
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	/**
	 * Imposta la denominazione.
	 * 
	 * @param name la denominazione della città.
	 */
	public void setName(String name) {
		this.name = name;
	}

}
