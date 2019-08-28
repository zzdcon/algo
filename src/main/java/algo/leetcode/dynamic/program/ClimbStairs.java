package algo.leetcode.dynamic.program;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class ClimbStairs {
    Map<Integer, Integer> stepMap = new HashMap<>();
    public int climbStairs(int n) {
        if(stepMap.containsKey(n)) {
            return stepMap.get(n);
        }

        if (n == 1) {
            stepMap.put(1, 1);
            return 1;
        }

        if (n == 2) {
            stepMap.put(2, 2);
            return 2;
        }

        int i = climbStairs(n - 1) + climbStairs(n - 2);
        stepMap.put(n, i);
        return i;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
