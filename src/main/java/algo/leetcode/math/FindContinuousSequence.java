package algo.leetcode.math;//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
//


import com.alibaba.fastjson.JSON;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class FindContinuousSequence {
    /**
     * 方法一： 暴力法
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= target / 2; i++) {
            int sum = 0;
            int n = 0;
            List<Integer> list = new ArrayList<>();
            while (sum < target) {
                list.add(i + n);
                sum += i + n;
                n++;
            }
            if (sum == target) {
                ans.add(list);
            }
        }
        int[][] res = new int[ans.size()][];
        for (int i = 0; i < ans.size(); i++) {
            int[] array = new int[ans.get(i).size()];
            for (int j = 0; j < ans.get(i).size(); j++) {
                array[j] = ans.get(i).get(j);
            }
            res[i] = array;
        }
        return res;
    }


    /**
     * 通过sum = (l+h)*n/2
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence2(int target) {
        List<int[]> res = new ArrayList<>();
        for (int left = 1, right = 2; left < right && right <= target / 2 + 1; ) {
            int sum = (left + right) * (right - left + 1) / 2;
            if (sum == target) {
                int[] ans = new int[right - left + 1];
                for (int index = left; index <= right; index++) {
                    ans[index - left] = index;
                }
                res.add(ans);
                left++;
            } else if (sum < target) {
                right++;
            } else {
                left++;
            }
        }
        return res.toArray(new int[0][]);
    }

    /**
     * 两个连续的数之和等于target，那么相差为1， (target - 1) % 2 == 0， 且数组一定是从 (target - 1) / 2开始的，
     * 数组的元素就是2个；如果是3个连续的数组，那么三个数之间相差为1、2，(target - 1 - 2) % 3 == 0，
     * 且数组一定是从 (target - 1 - 2) / 3开始的，数组元素是3个，依次类推
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence3(int target) {
        List<int[]> res = new ArrayList<>();
        // 连续数的个数
        int i = 2;
        while (i < target / 2 + 1) {
            int subSum = 0;
            for (int k = 1; k < i; k++) {
                subSum += k;
            }
            if ((target - subSum) % i != 0) {
                i++;
                continue;
            }
            int start = (target - subSum) / i;
            if (start < 1) break;
            int[] ans = new int[i];
            for (int index = 0; index < i; index++) {
                ans[index] = start + index;
            }
            res.add(0, ans);
            i++;
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] continuousSequence = new FindContinuousSequence().findContinuousSequence3(9);
        System.out.println(JSON.toJSONString(continuousSequence));
        int[][] continuousSequence1 = new FindContinuousSequence().findContinuousSequence3(15);
        System.out.println(JSON.toJSONString(continuousSequence1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
