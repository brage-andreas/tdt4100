package assignment5;

import java.util.Comparator;

public class NamedComparator implements Comparator<Named> {
    public int compare(Named named1, Named named2) {
        if (named1.getFamilyName().equals(named2.getFamilyName())) {
            return named1.getGivenName().compareTo(named2.getGivenName());
        }

        return named1.getFamilyName().compareTo(named2.getFamilyName());
    }
}
