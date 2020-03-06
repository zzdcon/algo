package algo.leetcode.math;//给定一个正整数 N，试求有多少组连续正整数满足所有数字之和为 N?
//
// 示例 1: 
//
// 
//输入: 5
//输出: 2
//解释: 5 = 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。 
//
// 示例 2: 
//
// 
//输入: 9
//输出: 3
//解释: 9 = 9 = 4 + 5 = 2 + 3 + 4 
//
// 示例 3: 
//
// 
//输入: 15
//输出: 4
//解释: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5 
//
// 说明: 1 <= N <= 10 ^ 9 
// Related Topics 数学


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class ConsecutiveNumbersSum {
    public int consecutiveNumbersSum(int N) {
        int add = 1;
        int i = 2;
        int result = 0;
        while (N > 2) {
            N -= add++;
            if (N >= i && N%i == 0) {
                result++;
            }
            i++;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
