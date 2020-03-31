package corso.java.businessmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A city.
 * 
 * @author nello
 *
 */
@Entity
@Table(name = "localita")
public class City extends EntityBase {
	/**
	 * The code for city identify on fiscal code.
	 */
	private String fiscalCode;
	/**
	 * If {@code true}, the city is capital of province.
	 */
	private boolean capital;
	/**
	 * Province where city is located.
	 */
	private Province province;
	/**
	 * City's people.
	 */
	private long people;
	/**
	 * Geographic location of the city.
	 */
	private Location location;

	/**
	 * Constructor.
	 */
	public City() {
		this(0, null, null, false, null, 0, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param primaryKey city's primary key.
	 * @param name       city's name.
	 * @param province   province of the city.
	 * @param capital    if {@code true} the city is province's capital.
	 * @param fiscalCode the fiscal code of the city.
	 * @param people     the people of the city.
	 * @param location   the location of the city.
	 */
	public City(long primaryKey, String name, Province province, boolean capital, String fiscalCode, long people,
			Location location) {
		super(primaryKey, name);
		setProvince(province);
		setCapital(capital);
		setFiscalCode(fiscalCode);
		setPeople(people);
		setLocation(location);
	}

	/**
	 * 
	 * @return if {@code true}, the city is capital.
	 */
	@Column(name = "capoluogo")
	public boolean getCapital() {
		return capital;
	}

	/**
	 * 
	 * @return the fiscal code.
	 */
	@Column(name = "istat", nullable = true)
	public String getFiscalCode() {
		return fiscalCode;
	}

	/**
	 * @return the location.
	 */
	@Column(name = "posizione", nullable = true)
	public Location getLocation() {
		return location;
	}

	/**
	 * 
	 * @return the people.
	 */
	@Column(name = "abitanti", nullable = true)
	public Long getPeople() {
		return people;
	}

	/**
	 * @return the primaryKey
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Override
	public Long getId() {
		return super.getId();
	}

	/**
	 * 
	 * @return the province.
	 */
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_provincia", referencedColumnName = "id")
	public Province getProvince() {
		return province;
	}

	/**
	 * 
	 * @param capital if {@code true}, the city is capital.
	 */
	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	/**
	 * 
	 * @param fiscalCode if {@code true}, the city is capital.
	 */
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	/**
	 * @param location the location to set.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * 
	 * @param people the people.
	 */
	public void setPeople(long people) {
		this.people = people;
	}

	/**
	 * 
	 * @param province the province.
	 */
	public void setProvince(Province province) {
		this.province = province;
	}

}
