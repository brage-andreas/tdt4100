package assignment4;

import java.util.ArrayList;

public class Person {
	private String name;
	private Gender gender;
	private Person mother;
	private Person father;
	private ArrayList<Person> children = new ArrayList<>();

	public static enum Gender {
		Male, Female
	}

	public Person(String name, char genderChar) {
		this.setName(name);
		this.setGender(genderChar);
	}

	public String getName() {
		return this.name;
	}

	public char getGender() {
		return switch (this.gender) {
			case Male -> 'M';
			case Female -> 'F';
		};
	}

	public Person getMother() {
		return this.mother;
	}

	public Person getFather() {
		return this.father;
	}

	public int getChildCount() {
		return this.children.size();
	}

	public Person getChild(int n) {
		int MAX_INDEX = this.children.size() - 1;

		if (n < 0 || n > MAX_INDEX) {
			throw new IllegalArgumentException("'n' must be between 0 and " + MAX_INDEX);
		}

		return this.children.get(n);
	}

	public boolean isMale() {
		return this.gender == Gender.Male;
	}

	public boolean isFemale() {
		return this.gender == Gender.Female;
	}

	public void setMother(Person newMother) throws IllegalArgumentException {
		validateMother(newMother);

		Person oldMother = this.getMother();

		if (oldMother != null) {
			oldMother.removeChild(this);
		}

		this.mother = newMother;
		newMother.addChildToList(this);
	}

	public void setFather(Person newFather) throws IllegalArgumentException {
		validateFather(newFather);

		Person oldFather = this.getFather();

		if (oldFather != null) {
			oldFather.removeChild(this);
		}

		this.father = newFather;
		newFather.addChildToList(this);
	}

	public void addChild(Person child) throws IllegalArgumentException {
		validateChild(child);

		child.setParent(this);
	}

	public void removeChild(Person child) {
		this.removeChildFromList(child);
		child.removeParent(this);
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setGender(char genderChar) {
		this.gender = switch (genderChar) {
			case 'M' -> Gender.Male;
			case 'F' -> Gender.Female;
			default -> throw new IllegalArgumentException("'genderChar' must be 'M' or 'F'");
		};
	}

	private boolean is(Person person) {
		return person != null && this.equals(person);
	}

	private void validateMother(Person mother) throws IllegalArgumentException {
		if (this.is(mother)) {
			throw new IllegalArgumentException("Cannot set self to be self's mother");
		}

		if (this.children.contains(mother)) {
			throw new IllegalArgumentException("Cannot set self's child to be self's mother");
		}

		if (mother.isMale()) {
			throw new IllegalArgumentException("Cannot set male to be a mother");
		}
	}

	private void validateFather(Person father) throws IllegalArgumentException {
		if (this.is(father)) {
			throw new IllegalArgumentException("Cannot set self to be self's father");
		}

		if (this.children.contains(father)) {
			throw new IllegalArgumentException("Cannot set self's child to be self's father");
		}

		if (father.isFemale()) {
			throw new IllegalArgumentException("Cannot set female to be a father");
		}
	}

	private void validateChild(Person child) throws IllegalArgumentException {
		if (this.is(child)) {
			throw new IllegalArgumentException("Cannot set self to be self's child");
		}

		if (child.is(this.getFather()) || child.is(this.getMother())) {
			throw new IllegalArgumentException("Cannot set self's parent to be self's child");
		}
	}

	private void setParent(Person parent) {
		if (parent.isMale()) {
			this.setFather(parent);
		} else {
			this.setMother(parent);
		}
	}

	private void removeParent(Person parent) {
		if (parent.is(this.getFather())) {
			this.father.removeChildFromList(this);
			this.father = null;

			return;
		}

		if (parent.is(this.getMother())) {
			this.mother.removeChildFromList(this);
			this.mother = null;

			return;
		}
	}

	private void addChildToList(Person child) {
		if (!this.children.contains(child)) {
			this.children.add(child);
		}
	}

	private void removeChildFromList(Person child) {
		this.children.remove(child);
	}

	public String toString() {
		return this.name + " (" + this.getGender() + ")";
	}

	public static void main(String[] args) {
		Person me = new Person("Meg", 'M');
		Person myFirstSister = new Person("Søster 1", 'F');
		Person mySecondSister = new Person("Søster 2", 'F');
		Person myMother = new Person("Mamma", 'F');
		Person myFather = new Person("Pappa", 'M');
		Person myMothersMother = new Person("Mormor", 'F');
		Person myMothersFather = new Person("Morfar", 'M');
		Person myFathersMother = new Person("Farmor", 'F');
		Person myFathersFather = new Person("Farfar", 'M');

		me.setMother(myMother);
		me.setFather(myFather);
		myFirstSister.setMother(myMother);
		myFirstSister.setFather(myFather);
		mySecondSister.setMother(myMother);
		mySecondSister.setFather(myFather);
		myMother.setMother(myMothersMother);
		myMother.setFather(myMothersFather);
		myFather.setMother(myFathersMother);
		myFather.setFather(myFathersFather);

		System.out.println(me + " har " + me.getChildCount() + " barn");
		System.out.println(myFirstSister + " har " + myFirstSister.getChildCount() + " barn");
		System.out.println(mySecondSister + " har " + mySecondSister.getChildCount() + " barn");
		System.out.println(myMother + " har " + myMother.getChildCount() + " barn");
		System.out.println(myFather + " har " + myFather.getChildCount() + " barn");
		System.out.println(myMothersMother + " har " + myMothersMother.getChildCount() + " barn");
		System.out.println(myMothersFather + " har " + myMothersFather.getChildCount() + " barn");
		System.out.println(myFathersMother + " har " + myFathersMother.getChildCount() + " barn");
		System.out.println(myFathersFather + " har " + myFathersFather.getChildCount() + " barn");
	}
}
