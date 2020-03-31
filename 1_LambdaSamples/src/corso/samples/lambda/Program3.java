package corso.samples.lambda;

import java.util.List;
import java.util.stream.Stream;

public class Program3 {

	static void print(String message, List<Data> list) {
		System.out.println(message);
		print(list);
	}

	static void print(List<Data> list) {
		int count = 0;
		for (Data d : list)
			System.out.format("%d\t%s\n", ++count, d);
	}

	public static void main(String[] args) {
		List<Data> list = Data.random(30);
		print("Elenco generato", list);
		Stream<Data> sd = list.stream();
		Stream<Data> even = sd.filter(n -> n.value % 2 == 0);
		list.add(new Data(-2));
		System.out.println("Estrazione dei numeri pari");
		even.forEach(d -> System.out.println(d.value));
		Stream<Integer> mapping = list.stream().filter(d -> d.value % 2 != 0).map(d -> d.value * 3);
		System.out.println("Mapping verso interi dei numeri dispari");
		mapping.forEach(v -> System.out.println(v));
	}

}
