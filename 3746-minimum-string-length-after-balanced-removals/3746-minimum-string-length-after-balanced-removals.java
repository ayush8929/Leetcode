class Solution {
    public int minLengthAfterRemovals(String s) {
        int countA = 0, countB = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') countA++;
            else countB++;
        }
        return Math.abs(countA - countB);
    }
}