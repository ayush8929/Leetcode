class Solution {
    public int maxDepth(String s) {
        int num=0, max = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                num++;
                if(num > max) max = num;
            }else if(s.charAt(i) == ')'){
                num--;
            }
        }
        return max;
    }
}