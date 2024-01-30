package assignment2;

public class Vehicle {
	private static final String REGISTRATION_NUMBER_PATTERN = "^[A-Z]{2}[0-9]{4,5}$";
	private static final String REGISTRATION_NUMBER_PATTERN_LONG = "^[A-Z]{2}[0-9]{5}$";
	private static final String REGISTRATION_NUMBER_PATTERN_SHORT = "^[A-Z]{2}[0-9]{4}$";
	private FuelType fuelType;
	private String registrationNumber;
	private Type type;

	public enum Type {
		CAR, MOTORCYCLE
	}

	public enum FuelType {
		PETROL, DIESEL, HYDROGEN, ELECTRIC
	}

	public Vehicle(char type, char fuelType, String licensePlate) {
		this.setVehicleType(type);
		this.setFuelType(fuelType);
		this.setRegistrationNumber(licensePlate);
	}

	public char getVehicleType() {
		switch (this.type) {
			case CAR:
				return 'C';
			case MOTORCYCLE:
				return 'M';
			default:
				throw new IllegalArgumentException("Unknown vehicle type");
		}
	}

	public char getFuelType() {
		switch (this.fuelType) {
			case PETROL:
				return 'G';
			case DIESEL:
				return 'D';
			case HYDROGEN:
				return 'H';
			case ELECTRIC:
				return 'E';
			default:
				throw new IllegalArgumentException("Unknown fuel type");
		}
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String licensePlate) {
		if (this.type == null || this.fuelType == null) {
			throw new IllegalArgumentException("Registration number must be set after vehicle type and fuel type");
		}

		if (!licensePlate.matches(REGISTRATION_NUMBER_PATTERN)) {
			throw new IllegalArgumentException("Registration number is not valid");
		}

		boolean matchesLong = licensePlate.matches(REGISTRATION_NUMBER_PATTERN_LONG);
		boolean matchesShort = licensePlate.matches(REGISTRATION_NUMBER_PATTERN_SHORT);

		if (this.type == Type.CAR && !matchesLong) {
			throw new IllegalArgumentException("Cars must have a long registration number");
		}

		if (this.type == Type.MOTORCYCLE && !matchesShort) {
			throw new IllegalArgumentException("Motorcycles must have a short registration number");
		}

		final String[] ELECTRIC_PREFIXES = { "EL", "EK", "EV" };
		final String[] HYDROGEN_PREFIXES = { "HY" };

		boolean isPetrolOrDiesel = this.fuelType == FuelType.PETROL || this.fuelType == FuelType.DIESEL;
		boolean isElectric = this.fuelType == FuelType.ELECTRIC;
		boolean isHydrogen = this.fuelType == FuelType.HYDROGEN;
		boolean startsWithElectricPrefix = this.startsWithAny(licensePlate, ELECTRIC_PREFIXES);
		boolean startsWithHydrogenPrefix = this.startsWithAny(licensePlate, HYDROGEN_PREFIXES);

		if (isElectric && !startsWithElectricPrefix) {
			throw new IllegalArgumentException(
					"Electric vehicles must have a registration number starting with 'EL', 'EK', or 'EV'");
		} else if (!isElectric && startsWithElectricPrefix) {
			throw new IllegalArgumentException(
					"Vehicles using fuel other than electricity cannot have a registration number starting with 'EL', 'EK', or 'EV'");
		}

		if (isHydrogen && !startsWithHydrogenPrefix) {
			throw new IllegalArgumentException("Hydrogen vehicles must have a registration number starting with 'HY'");
		} else if (!isHydrogen && startsWithHydrogenPrefix) {
			throw new IllegalArgumentException(
					"Vehicles using fuel other than hydrogen cannot have a registration number starting with 'HY'");
		}

		if ((isPetrolOrDiesel) && (startsWithElectricPrefix || startsWithHydrogenPrefix)) {
			throw new IllegalArgumentException(
					"Vehicles using petrol as fuel cannot have a registration number starting with 'EL', 'EK', 'EV', or 'HY'");
		}

		this.registrationNumber = licensePlate;
	}

	private void setVehicleType(char typeChar) {
		if (this.fuelType != null || this.registrationNumber != null) {
			throw new IllegalArgumentException("Vehicle type must be set before fuel type and registration number");
		}

		this.type = switch (typeChar) {
			case 'C' -> Type.CAR;
			case 'M' -> Type.MOTORCYCLE;
			default -> throw new IllegalArgumentException("Unknown vehicle type");
		};
	}

	private void setFuelType(char fuelTypeChar) {
		if (this.type == null || this.registrationNumber != null) {
			throw new IllegalArgumentException(
					"Fuel type must be set before registration number and after vehicle type");
		}

		FuelType fuelType = switch (fuelTypeChar) {
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

	private boolean startsWithAny(String string, String[] prefixes) {
		for (String prefix : prefixes) {
			if (string.startsWith(prefix)) {
				return true;
			}
		}

		return false;
	}
}
