package extraCredits6;

import java.util.*;

public class LC217_Easy_Mar_30 {
    public boolean containsDuplicate(int[] nums){
        Map<Integer, Integer> count = new HashMap<>();
        for (int i : nums) {
            count.put(i, count.getOrDefault(i, 0) + 1);
            if (count.get(i) > 1) {
                return true;
            }
        }
        return false;
    }
}
