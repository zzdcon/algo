package algo.leetcode.backtracing;//给定一个没有重复数字的序列，返回其所有可能的全排列。
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Permute {
    Map<Integer, List<List<Integer>>> map = new HashMap<>();
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        return partition(0, nums);
    }

    public List<List<Integer>> partition(int index, int[] nums) {
        if (map.get(index) != null) {
            return map.get(index);
        }

        List<List<Integer>> ans = new ArrayList<>();
        if (index == nums.length-1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[index]);
            ans.add(list);
            map.put(index, ans);
            return ans;
        }

        int num = nums[index];
        List<List<Integer>> next = partition(index + 1, nums);
        int partLen = next.get(0).size();
        for (List<Integer> list : next) {
            for (int i=0; i<=partLen; i++) {
                ArrayList<Integer> list1 = (ArrayList<Integer>) list;
                ArrayList<Integer> list2 = (ArrayList<Integer>) list1.clone();
                list2.add(i, num);
                ans.add(list2);
            }
        }
        map.put(index, ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Permute().permute(new int[] {5,4, 6,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
