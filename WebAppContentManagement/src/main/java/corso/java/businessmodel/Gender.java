package corso.java.businessmodel;

public enum Gender {
	Male(0), Female(40), Unknown(0);

	Gender(int fiscalCodeValue) {
		this.fiscalCodeValue = fiscalCodeValue;
	}

	public final int fiscalCodeValue;

	public static Gender fromChar(char gender) {
		switch (gender) {
		case 'm':
		case 'M':
			return Gender.Male;
		case 'f':
		case 'F':
			return Gender.Female;
		default:
			return Gender.Unknown;
		}
	}
}
