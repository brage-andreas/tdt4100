package assignment5;

public class Person2 implements Named {
    private String fullName;

    public Person2(String fullName) {
        validateLength(fullName, 2);

        this.fullName = fullName;
    }

    public void setGivenName(String name) {
        validateLength(name, 1);

        this.fullName = name + " " + this.getFamilyName();
    }

    public String getGivenName() {
        return this.fullName.split("\s")[0];
    }

    public void setFamilyName(String name) {
        validateLength(name, 1);

        this.fullName = this.getGivenName() + " " + name;
    }

    public String getFamilyName() {
        return this.fullName.split("\s")[1];
    }

    public void setFullName(String name) {
        validateLength(name, 2);

        this.fullName = name;
    }

    public String getFullName() {
        return this.fullName;
    }

    private void validateLength(String name, int expectedLength) throws IllegalArgumentException {
        if (name.split("\s").length != expectedLength) {
            throw new IllegalArgumentException("Invalid length");
        }
    }
}
