package corso.java.webmodel;

import java.util.Date;

public class PersonModel {
	private String name;
	private String surname;
	private Date birthday;
	private String birthCity;
	private String birthProvince;
	private String gender;
	private String fiscalCode;

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getBirthday() {
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

	public PersonModel setName(String name) {
		this.name = name;
		return this;
	}

	public PersonModel setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public PersonModel setBirthday(Date birthday) {
		this.birthday = birthday;
		return this;
	}

	public PersonModel setBirthCity(String birthCity) {
		this.birthCity = birthCity;
		return this;
	}

	public PersonModel setBirthProvince(String birthProvince) {
		this.birthProvince = birthProvince;
		return this;
	}

	public PersonModel setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
}
