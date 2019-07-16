import java.util.*;


public class LongestStringChain1048 {
    public static void main(String []args){
        String s="abc";
        String b=s.substring(3,3);
    }

    public int longestStrChain(String[] words) {
        int len=words.length;
        if(len<1)return 0;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()<o2.length())
                    return -1;
                if(o1.length()>o2.length())
                    return 1;
                return 0;
            }
        });
        int dp[]=new int[len];
        Arrays.fill(dp,1);
        int result=1;
        for(int i=0;i<len;i++){
            for(int j=0;j<i;j++){
                if(isPre(words[j],words[i])){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                result=Math.max(result,dp[i]);
            }
        }
        return result;
    }
    boolean isPre(String a,String b){
        if(a.length()+1!=b.length())return false;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)!=b.charAt(i)){
                return a.equals(b.substring(0,i)+b.substring(i+1,b.length()));
            }
        }
        return true;
    }
    public int longestStrChain2(String[] words) {
        List<List<String>> strings = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            strings.add(new ArrayList<>());
        }
        for (String word : words) {
            strings.get(word.length() - 1).add(word);
        }
        int ans = 0;
        for (int i = 0; i < 16; i++) {
            if (ans > 15 - i) {
                break;
            }
            List<String> curs = strings.get(i);
            for (String cur : curs) {
                ans = Math.max(ans, test(cur, i, strings));
            }
        }
        return ans;
    }

    private int test(String s, int idx, List<List<String>> strings) {
        if (idx == 15) return 1;
        List<String> nexts = strings.get(idx + 1);
        int ans = 1;
        for (String next : nexts) {
            if (match(s, next)) {
                ans = Math.max(ans, test(next, idx + 1, strings) + 1);
            }
        }
        return ans;
    }

    private boolean match(String pred, String word) {
        if (word.length() - pred.length() != 1) return false;
        int i = 0, j = 0;
        boolean inserted = false;
        while (j < pred.length()) {
            if (word.charAt(i) != pred.charAt(j)) {
                if (inserted) return false;
                inserted = true;
                i++;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }
}
