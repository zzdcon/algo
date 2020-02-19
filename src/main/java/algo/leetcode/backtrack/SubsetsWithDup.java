package algo.leetcode.backtrack;//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class SubsetsWithDup {
    List<List<Integer>> ans = new ArrayList<>();
    HashSet<String> ansStr = new HashSet<>();


    /**
     * 方法一：记录重复的数据, 添加的时候排除
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        backtrack(new ArrayList<>(), 0, nums);
        return ans;
    }

    private void backtrack(List<Integer> track, int index, int[] nums) {
        ArrayList<Integer> list = new ArrayList<>(track);
        if (ansStr.contains(list.toString())) {
            return;
        } else {
            ansStr.add(list.toString());
            ans.add(list);
        }
        for (int i = index; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(track, i + 1, nums);
            track.remove(track.size() - 1);

        }
    }


    /**
     * 排序后回溯时排除已经添加过的数字
     * 我们只需要判断当前数字和上一个数字是否相同，相同的话跳过即可。当然，要把数字首先进行排序。
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        backtrack2(new ArrayList<>(), 0, nums);
        return ans;
    }

    private void backtrack2(List<Integer> track, int index, int[] nums) {
        ans.add(new ArrayList<>(track));
        for (int i = index; i < nums.length; i++) {
            if (i> index && nums[i] == nums[i-1])
            track.add(nums[i]);
            backtrack2(track, i + 1, nums);
            track.remove(track.size() - 1);

        }
    }


    /**
     * 迭代法
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public List<List<Integer>> subsetsWithDup3(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> empty = new ArrayList<Integer>();
        result.add(empty);
        Arrays.sort(num);

        for (int i = 0; i < num.length; i++) {
            int dupCount = 0;
            //判断当前是否是重复数字，并且记录重复的次数
            while( ((i+1) < num.length) && num[i+1] == num[i]) {
                dupCount++;
                i++;
            }
            int prevNum = result.size();
            //遍历之前几个结果的每个解
            for (int j = 0; j < prevNum; j++) {
                List<Integer> element = new ArrayList<Integer>(result.get(j));
                //每次在上次的结果中多加 1 个重复数字
                for (int t = 0; t <= dupCount; t++) {
                    element.add(num[i]); //加入当前重复的数字
                    result.add(new ArrayList<Integer>(element));
                }
            }
        }
        return result;
    }

    /**
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public List<List<Integer>> subsetsWithDup4(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1<<num.length;
        for(int i=0;i<subsetNum;i++){
            List<Integer> list = new ArrayList<>();
            boolean illegal=false;
            for(int j=0;j<num.length;j++){
                //当前位是 1
                if((i>>j&1)==1){
                    //当前是重复数字，并且前一位是 0，跳过这种情况
                    if(j>0&&num[j]==num[j-1]&&(i>>(j-1)&1)==0){
                        illegal=true;
                        break;
                    }else{
                        list.add(num[j]);
                    }
                }
            }
            if(!illegal){
                lists.add(list);
            }

        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(new SubsetsWithDup().subsetsWithDup4(new int[]{1, 2, 3, 3}));
    }




}
//leetcode submit region end(Prohibit modification and deletion)
