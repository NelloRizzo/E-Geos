package corso.samples.lambda;

public class Program1 {

	public static boolean testIfChange(Integer a, Integer b) {
		return a > b;
	}

	public static void sort(Integer[] array) {
		for (int i = 0; i < array.length - 1; ++i)
			for (int j = i + 1; j < array.length; ++j)
				if (testIfChange(array[i], array[j])) {
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
		sort(numbers);
		System.out.println("Array dopo l'ordinamento");
		print(numbers);
	}

}
