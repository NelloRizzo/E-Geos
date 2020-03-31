package corso.java.businessmodel;

import javax.persistence.CascadeType;
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

/**
 * A region.
 * 
 * @author nello
 *
 */
@Entity
@Table(name = "regioni")
public class Region extends EntityBase {
	/**
	 * The area where region is located.
	 */
	private Area area;

	/**
	 * Constructor.
	 */
	public Region() {
		this(0, null, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param id   the primary key.
	 * @param name the name.
	 * @param area the area.
	 */
	public Region(long id, String name, Area area) {
		super(id, name);
		setArea(area);
	}

	/**
	 * Compare two instances of regions to sort first by area the by name.
	 */
	@Override
	public int compareTo(EntityBase o) {
		if (o instanceof Region) {
			Region other = (Region) o;
			String otherAreaName = other.getArea().getName();
			String thisAreaName = getArea().getName();
			// if the area is the same...
			if (otherAreaName.equalsIgnoreCase(thisAreaName))
				// sort by region's name
				return getName().compareTo(o.getName());
			else
				// else sort by area name
				return thisAreaName.compareTo(otherAreaName);
		}
		return super.compareTo(o);
	}

	/**
	 * 
	 * @return the area where region is located.
	 */
	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_circoscrizione", referencedColumnName = "id")
	@JsonProperty("area")
	public Area getArea() {
		return area;
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
	 * @param area the area to set.
	 */
	public void setArea(Area area) {
		this.area = area;
	}

}
