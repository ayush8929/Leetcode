class Solution {
    public long minOperations(int[] nums) {
        long total = 0;
        for(int num:nums) total += costFor(num);
        return total;
    }
    private long costFor(long x){
        int t = (int) (x%2);
        long best = Long.MAX_VALUE;
        String xs = Long.toString(x);
        int L = xs.length();
        int halfLen = (L+1)/2;
        long pow = 1;
        for(int i =0; i<halfLen -1; i++) pow*= 10;
        long lo =pow, hi=pow*10-1;
        long p0 = Long.parseLong(xs.substring(0, halfLen));
        int d0 = (int) (p0/pow);
        for(long p = p0-1; p<=p0+1; p++){
            if(p<lo || p>hi) continue;
            int lead = (int)(p/pow);
            if(lead % 2 != t) continue;
            best = Math.min(best, Math.abs(buildPalindrome(p, halfLen, L) - x));
        }
        if(d0-1 >= 1 && (d0-1) % 2 == t){
            long pLower=(long)(d0-1) * pow + (pow-1);
            best = Math.min(best, Math.abs(buildPalindrome(pLower, halfLen, L) -x));
        }
        if(d0+1 <= 9 && (d0 + 1) % 2 == t){
            long pUpper=(long)(d0+1) * pow;
            best = Math.min(best, Math.abs(buildPalindrome(pUpper, halfLen, L) -x));
        }
        if(L-1 >= 1){
            int Lm = L-1, halfLm = (Lm + 1)/2;
            long powM = 1;
            for(int i=0; i<halfLm - 1; i++) powM *= 10;
            int maxD = (t==0) ? 8 : 9;
            long pMax = (long) maxD*powM + (powM-1);
            best = Math.min(best, Math.abs(buildPalindrome(pMax, halfLm, Lm) - x));
        }
        int Lp = L+1, halfLp= (Lp + 1) /2;
        long powP = 1;
        for(int i =0; i<halfLp -1; i++) powP *= 10;
        int minD = (t==0) ? 2:1;
        long pMin = (long) minD* powP;
        best = Math.min(best , Math.abs(buildPalindrome(pMin, halfLp, Lp) - x));

        return best/2;
    }
    private long buildPalindrome(long prefix, int halfLen, int totalLen){
        String s = Long.toString(prefix);
        while(s.length() < halfLen) s="0" + s;
        String toMirror = (totalLen % 2 == 0) ? s: s.substring(0, s.length() - 1);
        return Long.parseLong(s + new StringBuilder(toMirror).reverse());
    }
}