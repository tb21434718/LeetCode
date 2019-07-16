import java.util.Arrays;

public class FillingBookcaseShelves1105 {
    //在固定书架宽度下，填充不同厚度的书籍，使得书架的高度最低
    //books[i][0]书宽度，books[i][1]书高度
    /*
    dp[i]表示0..i放好，并且换行需要的最小height

        然后求dp数组的时候，就一直往前遍历，直到不能塞下一行,然后和dp[j-1]+heigth比较结果
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int len=books.length;
        if(len<1)return 0;
        int dp[]=new int[len];
        Arrays.fill(dp,10000);
        dp[0]=books[0][1];
        for(int i=1;i<len;i++){
            int max_height=0;
            int used_width=0;
            for(int j=i;j>=0&&used_width<=shelf_width;j--){
                max_height=Math.max(max_height,books[j][1]);
                used_width=used_width+books[j][0];
                if(used_width<=shelf_width){
                    if(j==0){
                        dp[i]=Math.min(dp[i],max_height);
                    }else {
                        dp[i] = Math.min(dp[j - 1] + max_height, dp[i]);
                    }
                }
            }
        }
        return dp[len-1];
    }
}
