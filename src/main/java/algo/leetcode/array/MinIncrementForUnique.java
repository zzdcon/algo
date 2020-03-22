package algo.leetcode.array;//给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
//
// 返回使 A 中的每个值都是唯一的最少操作次数。 
//
// 示例 1: 
//
// 输入：[1,2,2]
//输出：1
//解释：经过一次 move 操作，数组将变为 [1, 2, 3]。 
//
// 示例 2: 
//
// 输入：[3,2,1,2,1,7]
//输出：6
//解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
//可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
// 
//
// 提示： 
//
// 
// 0 <= A.length <= 40000 
// 0 <= A[i] < 40000 
// 
// Related Topics 数组


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MinIncrementForUnique {
    /**
     * 方法一：计数
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        if (A.length == 0) return 0;
        int[] counter = new int[80000];
        int ans = 0;
        for (int a : A) {
            counter[a]++;
        }
        // 记录下次存放的起点
        int start1 = 0;
        for (int i = 0; i < 40000; i++) {
            if (counter[i] == 0) {
                continue;
            }
            // 计算起点
            int start = Math.max(start1, i + 1);
            // 将重复的数字后移，移动到为空的位置
            while (counter[i] > 1) {
                while (counter[start] > 0) {
                    start++;
                }
                counter[i]--;
                counter[start]++;
                ans += (start - i);
                start++;
                start1 = start;
            }
        }
        return ans;
    }

    /**
     * 方法二： 排序后移动
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique2(int[] A) {
        if (A.length == 0) return 0;
        Arrays.sort(A);
        int cnt = 0;
        for (int i = 1; i < A.length; ++i) {
            if (A[i] <= A[i - 1]) {
                int j = A[i - 1] - A[i] + 1;
                A[i] += j;
                cnt += j;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new MinIncrementForUnique().minIncrementForUnique(new int[]{1,2,2}));
        System.out.println(new MinIncrementForUnique().minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
