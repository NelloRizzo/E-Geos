/**
 * Modello di business.
 */
package corso.java.business.model;

/**
 * Entità di base. Superclasse di tutte le entità di business.
 * 
 * @author nello
 *
 */
public class EntityBase {
	/**
	 * Chiave dell'entità.
	 */
	private long id;

	/**
	 * Costruttore.
	 */
	public EntityBase() {
		setId(0);
	}

	/**
	 * Costruttore.
	 * 
	 * @param id Chiave dell'entità.
	 */
	public EntityBase(long id) {
		setId(id);
	}

	/**
	 * Imposta la chiave.
	 * 
	 * @param id Chiave dell'entità.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Restituisce la chiave.
	 * 
	 * @return la chiave dell'entità.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Controlla l'uguaglianza tra due entità.
	 * 
	 * @return un valore booleano che indica se l'istanza corrente è uguale
	 *         all'oggetto passato come parametro.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EntityBase)
			return obj.hashCode() == hashCode();
		return false;
	}

	/**
	 * Ottiene la codifica hash dell'entità.
	 * 
	 * @return il valore della chiave.
	 */
	@Override
	public int hashCode() {
		return ((Long)id).hashCode();
	}
}
