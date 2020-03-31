/**
 * Modello di business.
 */
package corso.java.business.model;

/**
 * Definizione di una località.
 * 
 * @author nello
 *
 */
public class Place extends EntityBase {
	/**
	 * Denominazione della località.
	 */
	private String name;

	/**
	 * Costruttore.
	 */
	public Place() {
		super();
	}

	/**
	 * Costruttore.
	 * 
	 * @param id   chiave della località.
	 * @param name denominazione della località.
	 */
	public Place(long id, String name) {
		super(id);
		setName(name);
	}

	/**
	 * Ottiene la denominazione della località.
	 * 
	 * @return la denominazione della località.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Imposta la denominazione della località.
	 * 
	 * @param name la denominazione della località.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Ottiene la rappresentazione della località sotto forma di stringa.
	 * 
	 * @return la denominazione.
	 */
	@Override
	public String toString() {
		return getName();
	}
}
