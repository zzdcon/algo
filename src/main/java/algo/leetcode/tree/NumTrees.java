package algo.leetcode.tree;//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
//
// 示例: 
//
// 输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3 
//

class NumTrees {


    /**
     *  做题的思路，想象一下f(4)有几种情况，其实就4种，分别是以1为根，以2为根，以3为根，和以4为根。
        我们分别讨论4种根情况个数： 如果以1为根，那么剩余的数只能在1的右子树，且剩余数也是按顺序234，
        相当于f(3)的个数。故1为根有f(0) * f(3) = 5; 同理，以2为根，左子树只可能1，右子树是34，
        共有f(1)*f(2)=2； 以3为根，f(2)*f(1)=2，以4为根，f(3) * f(0) = 5; 总结算法如下:

     f(4) = f(0)*f(3) + f(1)* f(2) + f(2)*f(1) + f(3)*f(0);
     f(n) = sum(f(i)*f(n-i-1)) (i)
     使用动态规划

    */
    public int numTrees(int n) {
        int[] ans = new int[n+1];
        ans[0] = 1;
        for(int i =1; i<=n; i++) {
            int sum = 0;
            for(int j=0; j<i; j++) {
                sum = sum + ans[j]*ans[i-1-j];
            }
            ans[i] = sum;
        }
        return ans[n];
    }


    // 公式计算法  Cn+1 = (4n+2)/(n+2) * Cn
    public int numTrees2(int n) {
        long c = 1;
        for (int i=0; i<n; i++) {
            c = c * (4*i + 2) / (i + 2);
        }
        return (int)c;
    }



}