package corso.java.business.model;

/**
 * Un decoratore per l'aggiunta di un riferimento geografico.
 * 
 * @author nello
 *
 */
public class GeographicDecorator extends Place {
	/**
	 * Posizione geografica.
	 */
	private Position position;
	/**
	 * Località.
	 */
	private Place place;

	/**
	 * Costruttore.
	 * 
	 * @param place    la località.
	 * @param position la posizione geografica.
	 */
	public GeographicDecorator(Place place, Position position) {
		setPlace(place);
		setPosition(position);
	}

	/**
	 * Ottiene la posizione.
	 * 
	 * @return la posizione geografica.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Ottiene la località.
	 * 
	 * @return la località.
	 */
	public Place getPlace() {
		return place;
	}

	/**
	 * Imposta la posizione.
	 * 
	 * @param position posizione geografica.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Imposta la località.
	 * 
	 * @param place la località.
	 */
	public void setPlace(Place place) {
		this.place = place;
	}

	/**
	 * Restituisce il nome della località.
	 * 
	 * @return il nome della località.
	 */
	public String getName() {
		return place.toString();
	}

	/**
	 * Restituisce la chiave della località.
	 * 
	 * @return la chiave della località.
	 */
	public long getId() {
		return place.getId();
	}

	/**
	 * Ottiene la rappresentazione sotto forma di stringa.
	 * 
	 * @return una stringa che rappresenta la località e le sue coordinate
	 *         geografiche.
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(place.toString()).append(" [").append(position.toString()).append(']')
				.toString();
	}
}
