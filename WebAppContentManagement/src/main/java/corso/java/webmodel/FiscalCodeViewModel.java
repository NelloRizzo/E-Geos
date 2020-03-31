package corso.java.webmodel;

public class FiscalCodeViewModel extends ViewModel {
	private String name;
	private String surname;
	private String birthday;
	private String birthCity;
	private String birthProvince;
	private String gender = "M";
	private String fiscalCode;
	private String[] genders = { "M", "F" };

	public String[] getGenders() {
		return genders;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public String getBirthProvince() {
		return birthProvince;
	}

	public String getGender() {
		return gender;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public FiscalCodeViewModel setName(String name) {
		this.name = name.toUpperCase();
		return this;
	}

	public FiscalCodeViewModel setSurname(String surname) {
		this.surname = surname.toUpperCase();
		return this;
	}

	public FiscalCodeViewModel setBirthday(String birthday) {
		this.birthday = birthday;
		return this;
	}

	public FiscalCodeViewModel setBirthCity(String birthCity) {
		this.birthCity = birthCity.toUpperCase();
		return this;
	}

	public FiscalCodeViewModel setBirthProvince(String birthProvince) {
		this.birthProvince = birthProvince.toUpperCase();
		return this;
	}

	public FiscalCodeViewModel setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public FiscalCodeViewModel setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
		return this;
	}

}
