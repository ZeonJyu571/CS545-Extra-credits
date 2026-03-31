package extraCredits6;

import java.util.*;

public class LC383_Easy_Mar_30 {
    public boolean canConstruct(String ransomNote, String magazine){
        if (magazine.length() < ransomNote.length()){
            return false;
        }
        Map<Character, Integer> count = new HashMap<>();
        for (char c: magazine.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (char c : ransomNote.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) - 1);
            if (count.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
