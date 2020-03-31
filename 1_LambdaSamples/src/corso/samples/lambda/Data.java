package corso.samples.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * Dati di esempio.
 * 
 * @author nello
 *
 */
class Data {
	private static final int MAX_VALUE = 1000;
	private static final int MIN_VALUE = 0;
	/**
	 * Valore veicolato.
	 */
	public final int value;

	/**
	 * Costruttore.
	 * 
	 * @param value valore gestito.
	 */
	public Data(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Data)
			return ((Data) obj).value == value;
		return false;
	}

	@Override
	public String toString() {
		return String.format("Data { valore = %d }", value);
	}

	/**
	 * Generatore di numeri casuali.
	 */
	private final static Random rnd = new Random();

	/**
	 * Genera un dato di esempio.
	 * 
	 * @return il dato casuale di esempio.
	 */
	public static Data random() {
		return new Data(rnd.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE);
	}

	/**
	 * Genera una serie di dati di esempio.
	 * 
	 * @param count il totale dei dati da produrre.
	 * @return la lista dei dati prodotti.
	 */
	public static List<Data> random(int count) {
		List<Data> list = new ArrayList<>();
		for (int counter = 0; counter < count; ++counter)
			list.add(Data.random());
		return list;
	}

}
