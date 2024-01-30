package assignment2;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private static final Set<Character> ALLOWED_GENDERS = Set.of('M', 'F', '\0');
	private static final int[][] WEIGHTED_SUM = { { 3, 7, 6, 1, 8, 9, 4, 5, 2 }, { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 } };
	private static final String EMAIL_REGEX = "^(?<firstName>[a-zA-Z]+)\\.(?<lastName>[a-zA-Z]+)@(?<lowDomain>[a-zA-Z0-9.-]+)\\.(?<topDomain>ad|ae|af|ag|ai|al|am|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bl|bm|bn|bo|bq|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cw|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mf|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|ss|st|sv|sx|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tr|tt|tv|tw|tz|ua|ug|um|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|za|zm|zw)$";
	private static final String SSN_REGEX = "^(?<dd>\\d{2})(?<mm>\\d{2})(?<yy>\\d{2})\\d{2}(?<n3>\\d)(?<c1>\\d)(?<c2>\\d)$";

	private String fullName = null;
	private Date birthDate = null;
	private String email = null;
	private char gender = '\0';
	private String ssn = null;

	public String getName() {
		return this.fullName;
	}

	public void setName(String fullName) throws IllegalArgumentException {
		this.fullName = validateName(fullName);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) throws IllegalArgumentException {
		this.email = validateEmail(email);
	}

	public Date getBirthday() {
		return this.birthDate;
	}

	public void setBirthday(Date birthDate) throws IllegalArgumentException {
		this.birthDate = validateBirthday(birthDate);
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) throws IllegalArgumentException {
		this.gender = validateGender(gender);
	}

	public String getSSN() {
		return this.ssn;
	}

	public void setSSN(String ssn) throws IllegalArgumentException {
		this.ssn = validateSSN(ssn);
	}

	private String validateName(String fullName) throws IllegalArgumentException {
		String[] nameParts = fullName.split(" ");

		if (nameParts.length != 2) {
			throw new IllegalArgumentException("'fullName' must have exactly 2 words");
		}

		for (String name : nameParts) {
			if (!name.matches("^[a-zA-Z]{2,}$")) {
				throw new IllegalArgumentException(
						"Each name must be at least 2 characters long and contain only letters");
			}
		}

		return fullName;
	}

	private String validateEmail(String email) throws IllegalArgumentException {
		if (!email.matches(EMAIL_REGEX)) {
			throw new IllegalArgumentException(
					"Email address must be in the format 'firstName.lastName@domain.countryCode'.");
		}

		if (!email.startsWith(this.fullName.toLowerCase().replace(" ", "."))) {
			throw new IllegalArgumentException(
					"Email address' first and last name must match the person's name");

		}

		return email;
	}

	private Date validateBirthday(Date birthDate) throws IllegalArgumentException {
		if (birthDate.after(new Date())) {
			throw new IllegalArgumentException("'birthDate' must be in the past");
		}

		return birthDate;
	}

	private char validateGender(char gender) throws IllegalArgumentException {
		if (!ALLOWED_GENDERS.contains(gender)) {
			throw new IllegalArgumentException("'gender' must be one of 'M', 'F', or '\\0'");
		}

		return gender;
	}

	private String validateSSN(String ssn) throws IllegalArgumentException {
		if (this.birthDate == null) {
			throw new IllegalArgumentException("'ssn' cannot be validated without a birth date");
		}

		if (this.gender == '\0') {
			throw new IllegalArgumentException("'ssn' cannot be validated without a gender");
		}

		Matcher matcher = Pattern.compile(SSN_REGEX).matcher(ssn);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("'ssn' must have exactly 11 digits");
		}

		String dd = matcher.group("dd");
		String mm = matcher.group("mm");
		String yy = matcher.group("yy");
		String n3 = matcher.group("n3");
		String c1 = matcher.group("c1");
		String c2 = matcher.group("c2");

		if (!isMatchingDateInSSN(dd, mm, yy)) {
			throw new IllegalArgumentException("'ssn' must be a valid date that matches the person's birth date");
		}

		validateGenderDigit(n3);

		int c1Parsed = Integer.parseInt(c1);
		int c2Parsed = Integer.parseInt(c2);

		if (!validateControlDigits(ssn, c1Parsed, c2Parsed)) {
			throw new IllegalArgumentException("'ssn' must have valid control digits");
		}

		return ssn;
	}

	private void validateGenderDigit(String n3) throws IllegalArgumentException {
		boolean n3IsEven = Integer.parseInt(n3) % 2 == 0;
		boolean genderIsFemale = this.gender == 'F';

		if (n3IsEven && !genderIsFemale) {
			throw new IllegalArgumentException("'ssn' must have a valid gender digit");
		}
	}

	private boolean isMatchingDateInSSN(String dd, String mm, String yy) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.birthDate);

		String ddControl = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));
		String mmControl = String.format("%02d", calendar.get(Calendar.MONTH) + 1);
		String yyControl = String.format("%02d", calendar.get(Calendar.YEAR) % 100);

		return dd.equals(ddControl) && mm.equals(mmControl) && yy.equals(yyControl);
	}

	private boolean validateControlDigits(String ssn, int c1, int c2) {
		int c1Control = calculateControlDigit(ssn, WEIGHTED_SUM[0], 11, -1);
		int c2Control = calculateControlDigit(ssn, WEIGHTED_SUM[1], 11, c1Control);

		return c1Control == c1 && c2Control == c2;
	}

	private int calculateControlDigit(String ssn, int[] weights, int modulus, int previousControlDigit) {
		int sum = 0;

		for (int i = 0; i < WEIGHTED_SUM[0].length; i++) {
			sum += Character.getNumericValue(ssn.charAt(i)) * weights[i];
		}

		if (previousControlDigit != -1) {
			sum += previousControlDigit * weights[weights.length - 1];
		}

		int control = modulus - (sum % modulus);

		return control == modulus ? 0 : control;
	}
}
