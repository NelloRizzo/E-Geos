package corso.samples.lambda;

public class Program2 {

	public interface Test {
		boolean change(Integer a, Integer b);
	}

	public static class TestSortAsc implements Test {

		@Override
		public boolean change(Integer a, Integer b) {
			return a > b;
		}

	}

	public static void sort(Integer[] array, Test test) {
		for (int i = 0; i < array.length - 1; ++i)
			for (int j = i + 1; j < array.length; ++j)
				if (test.change(array[i], array[j])) {
					Integer temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
	}

	public static void print(Integer[] array) {
		for (Integer n : array)
			System.out.println(n);
	}

	public static void main(String[] args) {
		Integer[] numbers = { 432, 4765, 234, 987, 142, 7908, 1420, 76345 };
		System.out.println("Array prima dell'ordinamento");
		print(numbers);
		sort(numbers, new TestSortAsc());
		System.out.println("Array dopo l'ordinamento");
		print(numbers);
		System.out.println("Ordinamento decrescente con classe anonima");
		sort(numbers, new Test() {
			@Override
			public boolean change(Integer a, Integer b) {
				return a < b;
			}
		});
		System.out.println("Prima i pari crescenti e poi i dispari descrescenti (esempio di lambda)");
		sort(numbers, (a, b) -> {
			if (a % 2 == 0)
				return (b % 2 == 0) ? a > b : false;
			else
				return (b % 2 != 0) ? b > a : true;
		});
		print(numbers);
		System.out.println("Ordinamento decrescente con lambda");
		sort(numbers, (a, b) -> a < b);
		print(numbers);
	}

}
