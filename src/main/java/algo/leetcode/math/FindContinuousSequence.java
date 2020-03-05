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


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class FindContinuousSequence {
    public int[][] findContinuousSequence(int target) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i<=target/2; i++) {
            int sum = 0;
            int n = 0;
            List<Integer> list = new ArrayList<>();
            while (sum < target) {
                list.add(i+n);
                sum += i+n;
                n++;
            }
            if (sum == target) {
                ans.add(list);
            }
        }
        int[][] res = new int[ans.size()][];
        for (int i=0; i<ans.size(); i++) {
            int[] array = new int[ans.get(i).size()];
            for (int j=0; j<ans.get(i).size(); j++) {
                array[j] = ans.get(i).get(j);
            }
            res[i] = array;
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] continuousSequence = new FindContinuousSequence().findContinuousSequence(9);
        System.out.println(new FindContinuousSequence().findContinuousSequence(9));
        int[][] continuousSequence1 = new FindContinuousSequence().findContinuousSequence(15);

        System.out.println(new FindContinuousSequence().findContinuousSequence(15));
        System.out.println(new FindContinuousSequence().findContinuousSequence(15));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
