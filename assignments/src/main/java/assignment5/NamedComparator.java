package assignment5;

public class NamedComparator {
    public int compare(Named named1, Named named2) {
        int familyNameComparison = getReturnFromComparison(named1.getFamilyName().compareTo(named2.getFamilyName()));
        int givenNameComparison = getReturnFromComparison(named1.getGivenName().compareTo(named2.getGivenName()));

        if (familyNameComparison == 0 && givenNameComparison == 0) {
            return 0;
        }

        if (familyNameComparison == 0) {
            return givenNameComparison;
        }

        return familyNameComparison;
    }

    private int getReturnFromComparison(int comparison) {
        if (comparison < 0) {
            return -1;
        } else if (comparison > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
