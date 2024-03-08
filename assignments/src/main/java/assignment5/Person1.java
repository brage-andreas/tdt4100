package assignment5;

public class Person1 implements Named {
    private String givenName;
    private String familyName;

    public Person1(String givenName, String familyName) {
        validateLength(givenName, 1);
        validateLength(familyName, 1);

        this.givenName = givenName;
        this.familyName = familyName;
    }

    public void setGivenName(String name) {
        validateLength(name, 1);

        this.givenName = name;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public void setFamilyName(String name) {
        validateLength(name, 1);

        this.familyName = name;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public void setFullName(String name) {
        validateLength(name, 2);

        String[] split = name.split("\s");

        this.givenName = split[0];
        this.familyName = split[1];
    }

    public String getFullName() {
        return this.givenName + " " + this.familyName;
    }

    private void validateLength(String name, int expectedLength) throws IllegalArgumentException {
        if (name.split("\s").length != expectedLength) {
            throw new IllegalArgumentException("Invalid length");
        }
    }
}
