package extraCredits7;

import java.util.*;

public class LC76_Hard {
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();

    public String minWindow(String s, String t) {
        for (char c : t.toCharArray()){
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int required = need.size();
        int formed = 0;

        int left = 0, len = Integer.MAX_VALUE;
        int ansL = -1, ansR = -1;

        for (int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (need.containsKey(c) && window.get(c).equals(need.get(c))){
                formed++;
            }

            while(formed == required){
                if (right - left + 1 < len){
                    len = right - left + 1;
                    ansL = left;
                    ansR = right + 1;
                }
                char lc = s.charAt(left);
                window.put(lc, window.get(lc) - 1);
                if (need.containsKey(lc) && window.get(lc) < need.get(lc)){
                    formed--;
                }
                left ++;
            }

        }
        return ansL == -1 ? "": s.substring(ansL, ansR);
    }
}
