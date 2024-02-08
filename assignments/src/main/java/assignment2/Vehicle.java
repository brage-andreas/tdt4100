package assignment2;

public class Vehicle {
	private static final String HYDROGEN_REGISTRATION_NUMBER_PATTERN = "^(?:HY)[0-9]{4,5}$";
	private static final String ELECTRIC_REGISTRATION_NUMBER_PATTERN = "^(?:EL|EK|EV)[0-9]{4,5}$";
	private static final String REGISTRATION_NUMBER_PATTERN_SHORT = "^[A-Z]{2}[0-9]{4}$";
	private static final String REGISTRATION_NUMBER_PATTERN_LONG = "^[A-Z]{2}[0-9]{5}$";
	private static final String REGISTRATION_NUMBER_PATTERN = "^[A-Z]{2}[0-9]{4,5}$";
	private FuelType fuelType;
	private String registrationNumber;
	private Type type;

	private enum FuelType {
		PETROL, DIESEL, HYDROGEN, ELECTRIC
	}

	private enum Type {
		CAR, MOTORCYCLE
	}

	public Vehicle(char typeCharacter, char fuelTypeCharacter, String registrationNumber) {
		this.setVehicleType(typeCharacter);
		this.setFuelType(fuelTypeCharacter);
		this.setRegistrationNumber(registrationNumber);
	}

	public char getVehicleType() throws IllegalArgumentException {
		return switch (this.type) {
			case CAR -> 'C';
			case MOTORCYCLE -> 'M';
			default -> throw new IllegalArgumentException("Unknown vehicle type");
		};
	}

	public char getFuelType() throws IllegalArgumentException {
		return switch (this.fuelType) {
			case PETROL -> 'G';
			case DIESEL -> 'D';
			case HYDROGEN -> 'H';
			case ELECTRIC -> 'E';
			default -> throw new IllegalArgumentException("Unknown fuel type");
		};
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) throws IllegalArgumentException {
		if (this.type == null || this.fuelType == null) {
			throw new IllegalArgumentException("Registration number must be set after vehicle type and fuel type");
		}

		if (!registrationNumber.matches(REGISTRATION_NUMBER_PATTERN)) {
			throw new IllegalArgumentException("Registration number is not valid");
		}

		this.checkRegistrationNumberLength(registrationNumber);
		this.checkRegistrationNumberPrefix(registrationNumber);

		this.registrationNumber = registrationNumber;
	}

	public boolean isPetrol() {
		return this.fuelType == FuelType.PETROL;
	}

	public boolean isDiesel() {
		return this.fuelType == FuelType.DIESEL;
	}

	public boolean isHydrogen() {
		return this.fuelType == FuelType.HYDROGEN;
	}

	public boolean isElectric() {
		return this.fuelType == FuelType.ELECTRIC;
	}

	public boolean isFossil() {
		return this.isPetrol() || this.isDiesel();
	}

	private void setVehicleType(char typeCharacter) throws IllegalArgumentException {
		if (this.fuelType != null || this.registrationNumber != null) {
			throw new IllegalArgumentException("Vehicle type must be set before fuel type and registration number");
		}

		this.type = switch (typeCharacter) {
			case 'C' -> Type.CAR;
			case 'M' -> Type.MOTORCYCLE;
			default -> throw new IllegalArgumentException("Unknown vehicle type");
		};
	}

	private void setFuelType(char fuelTypeCharacter) throws IllegalArgumentException {
		if (this.type == null || this.registrationNumber != null) {
			throw new IllegalArgumentException(
					"Fuel type must be set before registration number and after vehicle type");
		}

		FuelType fuelType = switch (fuelTypeCharacter) {
			case 'G' -> FuelType.PETROL;
			case 'D' -> FuelType.DIESEL;
			case 'H' -> FuelType.HYDROGEN;
			case 'E' -> FuelType.ELECTRIC;
			default -> throw new IllegalArgumentException("Unknown fuel type");
		};

		if (this.type == Type.MOTORCYCLE && fuelType == FuelType.HYDROGEN) {
			throw new IllegalArgumentException("Motorcycles cannot use hydrogen as fuel");
		}

		this.fuelType = fuelType;
	}

	private void checkRegistrationNumberLength(String licensePlate) throws IllegalArgumentException {
		if (this.type == Type.CAR && !licensePlate.matches(REGISTRATION_NUMBER_PATTERN_LONG)) {
			throw new IllegalArgumentException("Car registration number must be 5 characters long");
		}

		if (this.type == Type.MOTORCYCLE && !licensePlate.matches(REGISTRATION_NUMBER_PATTERN_SHORT)) {
			throw new IllegalArgumentException("Motorcycle registration number must be 4 characters long");
		}
	}

	private void checkRegistrationNumberPrefix(String licensePlate) throws IllegalArgumentException {
		boolean startsWithElectricPrefix = licensePlate.matches(Vehicle.ELECTRIC_REGISTRATION_NUMBER_PATTERN);
		boolean startsWithHydrogenPrefix = licensePlate.matches(Vehicle.HYDROGEN_REGISTRATION_NUMBER_PATTERN);

		if (this.isElectric() ^ startsWithElectricPrefix) {
			throw new IllegalArgumentException(
					"Electric vehicles must have a registration number starting with 'EL', 'EK', or 'EV', and non-electric vehicles cannot");
		}

		if (this.isHydrogen() ^ startsWithHydrogenPrefix) {
			throw new IllegalArgumentException(
					"Hydrogen vehicles must have a registration number starting with 'HY', and non-hydrogen vehicles cannot");
		}

		if (this.isFossil() && (startsWithElectricPrefix || startsWithHydrogenPrefix)) {
			throw new IllegalArgumentException(
					"Petrol or diesel vehicles cannot have a registration number starting with 'EL', 'EK', 'EV', or 'HY'");
		}
	}
}
