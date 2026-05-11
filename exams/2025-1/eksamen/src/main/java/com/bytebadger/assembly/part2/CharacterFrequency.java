package com.bytebadger.assembly.part2;

import java.util.HashMap;
import java.util.Map;

// TODO: Import relevant libraries


public class CharacterFrequency {

    /**
     * This method counts the frequency of each character in a given string.
     * 
     * @return a Map with characters as keys and their frequencies as values
     */

    public static Map<Character, Integer> countCharacterFrequency(String input) {

        // TODO: Complete the method according to JavaDoc

        // ==== GJØRE DETTE LIVE ====
        Map<Character, Integer> result = new HashMap<>();

        if (input == null) {
            return result;
        }

        for (var character : input.toCharArray()) {
            int currentCount = result.getOrDefault(character, 0);
            result.put(character, currentCount + 1);
        }

        return result;
        // ===========================

        //return null;

    }
}
