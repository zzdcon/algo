package algo.leetcode.bfs;//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
//
// 示例 1: 
//
// 输入: n = 12
//输出: 3 
//解释: 12 = 4 + 4 + 4. 
//
// 示例 2: 
//
// 输入: n = 13
//输出: 2
//解释: 13 = 4 + 9. 
// Related Topics 广度优先搜索 数学 动态规划


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class NumSquares {

    /**
     * 准备小于给定数字 n 的完全平方数列表
     * 把减去平方数列表的余数放入栈
     * @param n
     * @return
     */
    public int numSquares(int n) {
        List<Integer> sList = new ArrayList<>();
        for (int i=1; i<n; i++) {
            int i1 = i * i;
            if (i1 == n) return 1;
            if (i1 > n) break;
            sList.add(i1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (Integer i : sList) queue.offer(n-i);
        int cnt = 1;
        while (!queue.isEmpty()) {
            cnt++;
            int size = queue.size();
            HashSet<Integer> set = new HashSet<>();
            for (int i=0; i<size; i++) {
                Integer poll = queue.poll();
                for (Integer j : sList) {
                    if (j.intValue() == poll.intValue()) {
                        return cnt;
                    } else if (j>poll) {
                        break;
                    } else if (!set.contains(poll-j)) {
                        queue.offer(poll-j);
                        set.add(poll-j);
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 不用队列直接用新set替换旧set
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        List<Integer> squreList = new ArrayList<>();
        for (int i=1; i<n; i++) {
            int i1 = i * i;
            if (i1>n) break;
            squreList.add(i1);
        }
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        int cnt = 0;
        while (!set.isEmpty()) {
            cnt++;
            HashSet<Integer> nextSet = new HashSet<>();
            for (Integer remainder : set) {
                for (Integer square : squreList) {
                    if (remainder.equals(square)) {
                        return cnt;
                    } else if (square>remainder) {
                        break;
                    } else{
                        nextSet.add(remainder-square);
                    }
                }
            }
            set=nextSet;
        }
        return cnt;
    }

    /**
     * 动态规划
     * numSquares(n)=min(numSquares(n-k) + 1)
     * @param n
     * @return
     */
    public int numSquares3(int n) {
        // 1.计算所有的开方数
        List<Integer> squreList = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            int i1 = i * i;
            if (i1>n) break;
            squreList.add(i1);
        }

        // 2.定义dp数组
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i=0; i<=n; i++) {
            for (int s : squreList) {
                if (s>i) break;
                dp[i] = Math.min(dp[i], dp[i-s]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new NumSquares().numSquares3(1));
//        System.out.println(new NumSquares().numSquares3(288));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
