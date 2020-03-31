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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "province")
public class Province extends EntityBase {
	/**
	 * A province.
	 */
	private String acronym;
	/**
	 * The region where province is located.
	 */
	private Region region;

	/**
	 * Constructor.
	 */
	public Province() {
		this(0, null, null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param id      the province's primary key.
	 * @param name    the province's name.
	 * @param acronym the province's acronym.
	 * @param region  the province's region.
	 */
	public Province(long id, String name, String acronym, Region region) {
		super(id, name);
		setAcronym(acronym);
		setRegion(region);
	}

	@Override
	public int compareTo(EntityBase o) {
		if (o instanceof Province) {
			Region r = ((Province) o).getRegion();
			if (r.equals(this.getRegion()))
				return getName().compareTo(o.getName());
			else
				return getRegion().compareTo(r);
		}
		return super.compareTo(o);
	}

	/**
	 * 
	 * @return the acronym.
	 */
	@Column(name = "sigla", nullable = true)
	public String getAcronym() {
		return acronym;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Override
	public Long getId() {
		return super.getId();
	}

	/**
	 * 
	 * @return the region.
	 */
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_regione", referencedColumnName = "id")
	@JsonProperty("region")
	public Region getRegion() {
		return region;
	}

	/**
	 * 
	 * @param acronym the acronym.
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * 
	 * @param region the region.
	 */
	public void setRegion(Region region) {
		this.region = region;
	}
}
