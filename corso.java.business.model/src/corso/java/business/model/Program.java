package corso.java.business.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.io.BufferedReader;

public class Program {

	public static void main(String[] args) throws FileNotFoundException {
		CitiesStreamReader csr = new CitiesStreamReader(new FileInputStream("D:\\temp\\comuni.csv")).setHeaderLines(3);
		List<City> cities = csr.process();
		printList("Elenco delle città:", cities);
		boolean reading = true;
		while (reading) {
			System.out.print("Ricerca con lambda (. per terminare): ");
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String search = reader.readLine();
				if (search.equals("."))
					reading = false;
				else {
					List<City> result = cities.stream().filter(c -> c.getName().contains(search))
							.collect(Collectors.toList());
					printList(String.format("Ricerca di <%s>", search), result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void printList(String title, List<City> cities) {
		System.out.println(title);
		cities.stream().forEach(
				// interfaccia funzionale (alla base delle lambda)
				// implementazione di classe "anonima"
				new Consumer<City>() {
					// variabile di istanza e sua inizializzazione
					int count = 0;

					// l'unico metodo previsto dall'interfaccia
					@Override
					public void accept(City city) {
						System.out.format("%d\t%s\n", ++count, city);
					}

				});
//		int count = 0;
//		for (City city : cities) {
//			System.out.format("%d\t%s\n", ++count, city);
//		}
	}
}
