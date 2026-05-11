package com.bytebadger.assembly.part2;

// TODO: Import relevant libraries

public class Anagram {

    /**
     * This method checks if two strings are anagrams.
     * 
     * Two strings are anagrams if they contain the same characters
     * in the same frequency, but possibly in a different order.
     * The method ignores case and spaces.
     * 
     * Empty strings cannot be anagrams.
     * Strings of different length cannot be anagrams.
     * 
     * Example:
     * The words "listen" and "silent" are anagrams.
     * 
     * @param str1 the first input string
     * @param str2 the second input string
     * @return a boolean value indicating whether the two strings are anagrams
     */
    public boolean isAnagram(String str1, String str2) {

        // TODO: Complete the method according to JavaDoc

        // ==== GJØRE DETTE LIVE ====
        if (str1 == null || str2 == null) {
            return false;
        }

        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }

        if (str1.length() == 0 || str2.length() == 0) {
            return false;
        }

        var strA = str1.replaceAll(" ", "").toLowerCase();
        var strB = str2.replaceAll(" ", "").toLowerCase();


        var a = CharacterFrequency.countCharacterFrequency(strA);
        var b = CharacterFrequency.countCharacterFrequency(strB);

        return a.equals(b);
        // ===========================

        //return true; // Placeholder return statement, replace with actual logic

    }
}

