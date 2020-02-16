package algo.leetcode.backtracing;//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Subsets {

    /**
     * 算法一： 递归算法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> backtrack = backtrack(0, nums);
        backtrack.add(new ArrayList<>());
        return backtrack;
    }

    public List<List<Integer>> backtrack(int indexStart, int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (indexStart == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[indexStart]);
            ans.add(list);
            return ans;
        }
        int num = nums[indexStart];
        List<List<Integer>> backtrack = backtrack(indexStart + 1, nums);
        for (List<Integer> list : backtrack) {
            List<Integer> plus = new ArrayList<>(list);
            plus.add(num);
            ans.add(plus);
        }

        List<Integer> list = new ArrayList<>();
        list.add(num);
        ans.add(list);
        ans.addAll(backtrack);
        return ans;
    }


    /**
     * 标准回溯算法
     * @param nums
     * @return
     */

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ans;
        }
        backtrack(new ArrayList<>(), 0, nums);
        return ans;
    }

    /**
     * 回溯方法
     * @param track 选择路径
     * @param start 选择列表
     * @param nums 选择列表
     * @return
     */
    public void backtrack(List<Integer> track, int start, int[] nums) {
        ans.add(new ArrayList<>(track));
        for (int i=start; i< nums.length; i++) {
            track.add(nums[i]);
            backtrack(track, i+1, nums);
            track.remove(track.size()-1);
        }
    }

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> subsets3(int[] nums) {
        if(nums == null || nums.length ==0){
            return lists;
        }

        List<Integer> list = new ArrayList<>();
        process(list, nums, 0);
        return lists;

    }

    private void process(List<Integer>list, int[] nums, int start){

        lists.add(new ArrayList(list));
        for(int i = start; i < nums.length; i++){

            list.add(nums[i]);
            process(list, nums, i+1);
            list.remove(list.size()-1);
        }
    }


    /**
     * 位移法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length ==0){
            return ans;
        }

        int no = 1<<nums.length;
        for (int i=0; i<no; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j=0; j<nums.length; j++) {
                if ((i>>j & 1) == 0) {
                    list.add(nums[j]);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Subsets().subsets4(new int[] {1, 2, 3}));
    }


}
//leetcode submit region end(Prohibit modification and deletion)
