package corso.java.businessmodel;

import java.util.Calendar;
import java.util.Date;

import org.springframework.data.util.Pair;

public class PersonalData {
	private String name;
	private String surname;
	private Calendar birthday;
	private Gender gender;
	private City birthCity;

	public PersonalData() {
		this.birthday = Calendar.getInstance();
	}

	public PersonalData(String name, String surname, Date birthDate, Gender gender, City birthCity) {
		this();
		setName(name);
		setSurname(surname);
		setBirthday(birthDate);
		setGender(gender);
		setBirthCity(birthCity);
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthday() {
		return birthday.getTime();
	}

	public Gender getGender() {
		return gender;
	}

	public City getBirthCity() {
		return birthCity;
	}

	public PersonalData setName(String name) {
		this.name = name.toUpperCase();
		return this;
	}

	public PersonalData setSurname(String surname) {
		this.surname = surname.toUpperCase();
		return this;
	}

	public PersonalData setBirthday(Date birthday) {
		this.birthday.setTime(birthday);
		return this;
	}

	public PersonalData setGender(Gender gender) {
		this.gender = gender;
		return this;
	}

	public PersonalData setBirthCity(City birthCity) {
		this.birthCity = birthCity;
		return this;
	}

	public String getFiscalCode() {
		StringBuilder fc = new StringBuilder(16).append(parseSurname()).append(parseName()).append(parseBirthday())
				.append(parseCity());
		fc.append(calculateCheckCode(fc.toString()));
		return fc.toString();
	}

	private Pair<String, String> separateVowels(String text) {
		StringBuilder cons = new StringBuilder();
		StringBuilder vowels = new StringBuilder();
		text.toUpperCase().chars().filter(c -> Character.isAlphabetic(c)).forEach(c -> {
			if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
				vowels.append((char) c);
			else
				cons.append((char) c);
		});
		return Pair.of(cons.toString(), vowels.toString());
	}

	private String parseSurname() {
		Pair<String, String> cv = separateVowels(surname);
		return String.format("%s%sXXX", cv.getFirst(), cv.getSecond()).substring(0, 3);
	}

	private String parseName() {
		Pair<String, String> cv = separateVowels(name);
		String cons = cv.getFirst();
		if (cons.length() > 3)
			cons = cons.substring(0, 1) + cons.substring(2);
		return String.format("%s%sXXX", cons, cv.getSecond()).substring(0, 3);
	}

	private String parseBirthday() {
		final String MONTHS = "ABCDEHLMPRST";
		return String.format("%02d%c%02d", birthday.get(Calendar.YEAR) % 100,
				MONTHS.charAt(birthday.get(Calendar.MONTH)),
				birthday.get(Calendar.DAY_OF_MONTH) + gender.fiscalCodeValue);
	}

	private String parseCity() {
		return birthCity == null || birthCity.getFiscalCode() == null ? "X000" : birthCity.getFiscalCode();
	}

	private char calculateCheckCode(String fc) {
		final int[] ODDS = { 1, 0, 5, 7, 9, 13, 15, 17, 19, 21, 2, 4, 18, 20, 11, 3, 6, 8, 12, 14, 16, 10, 22, 25, 24,
				23 };
		fc = fc.concat("XXXXXX00X00X000X".substring(15 - fc.length())).substring(0, 15);
		int runningSum = 0;
		for (int index = 0; index < 15; ++index) {
			char ch = fc.charAt(index);
			int deplacement = (Character.isDigit(ch)) ? ch - '0' : ch - 'A';
			runningSum += index % 2 == 0 ? ODDS[deplacement] : deplacement;
		}
		return (char) (runningSum % 26 + 'A');
	}

	public boolean isValid() {
		return name != null && surname != null && birthCity != null;
	}
}
