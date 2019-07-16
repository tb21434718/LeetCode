import java.util.Calendar;

public class MinimumScoreTriangulationofPolygon1039 {
    public int minScoreTriangulation(int[] A) {
        int n=A.length;
        int dp[][]=new int[n][n];
        for(int i=3;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int k=j+i-1;
                int tmp=Integer.MAX_VALUE;
                for(int m=j+1;m<k;m++){
                    tmp=Math.min(tmp,dp[j][m]+A[j]*A[m]*A[k]+dp[m][k]);
                }
                if(tmp!=Integer.MAX_VALUE){
                    dp[j][k]=tmp;
                }
            }
        }
        return dp[0][n-1];
    }



    /*
     以数组第一个点和最后一个点为边肯定会有个三角形，以这条边作为基边，然后遍历这中间所有的点作为定点，
        形成的三角形又把大的多边形分成了两个小的多边形，子问题递归求解即可

        定义 f(0, n) 为 0~n 这些顶点组成的多边形的最优划分。
此时只考虑顶点0和顶点n组成的边，则1~n-1这些顶点都可以当做三角形的第三个顶点，假设选择可顶点k。
此时多边形被划分为三部分：多边形f(0,k)、三角形0,k,n、多边形f(k,n)。
最优值就是所有划分中的最小值。
     */
    public int minScoreTriangulation3(int[] A) {
        int n=A.length;
        if(n==3){
            return A[0]*A[1]*A[2];
        }
        int cache[][]=new int[n][n];
        return helper(0,n-1,A,cache);
    }
    int helper(int leftPoint,int rightPoint,int A[],int cache[][]){
        if(rightPoint-leftPoint==1)return 0;
        if(cache[leftPoint][rightPoint]!=0){
            return cache[leftPoint][rightPoint];
        }
        int tempresult=Integer.MAX_VALUE;
        for(int i=leftPoint+1;i<rightPoint;i++){
            int curarea=A[leftPoint]*A[i]*A[rightPoint];
            tempresult=Math.min(tempresult,helper(leftPoint,i,A,cache)+curarea+helper(i,rightPoint,A,cache));
        }
        cache[leftPoint][rightPoint]=tempresult;
        return tempresult;
    }

    /*
    Java, bottom-up:
    dp[i][j] means the minimum score to triangulate A[i] ~ A[j],
while there is edge connect A[i] and A[j].

We enumerate all points A[k] with i < k < j to form a triangle.

The score of this triangulation is dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]
    设 dp[i][j] 代表 A 中从第 i 到第 j个点围成凸多边形所得的最优解。
    枚举在 i,j之间的一点 k，考虑是否将该凸多边形剖分出 i, j, k 三点所围成的三角形。
    则有：dp[i][j]=min(dp[i][j],dp[i][k]+dp[k][j]+A[i]×A[j]×A[k])
     */
    public int minScoreTriangulation4(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }
}
