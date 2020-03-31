package corso.java.businessmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Area where region is located.
 * 
 * @author nello
 *
 */
@Entity
@Table(name = "circoscrizioni")
public class Area extends EntityBase {
	/**
	 * Constructor.
	 */
	public Area() {
		this(0, null);
	}

	/**
	 * Constructor.
	 * 
	 * @param id   the primary key of area.
	 * @param name the name of area.
	 */
	public Area(long id, String name) {
		super(id, name);
	}

	/**
	 * @return the primaryKey
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Override
	public Long getId() {
		return super.getId();
	}

}
