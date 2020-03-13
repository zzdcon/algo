package algo.leetcode.array;

//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MajorityElement {
    /**
     * 用map记录每个数出现的次数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer cnt = map.getOrDefault(nums[i], 0);
            if (cnt == nums.length / 2) {
                return nums[i];
            }
            map.put(nums[i], cnt+1);
        }
        return 0;
    }

    /**
     * 先排序后取中间值
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     *  摩尔投票法
     *  从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1，减到0就重新换个数开始计数，总能找到最多的那个
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        int cnt = 0;
        int ans = 0;
        for (int i=1; i<nums.length; i++) {
            if (cnt == 0) {
                ans = nums[i];
                cnt++;
            } else {
                if (nums[i] != ans) {
                    cnt--;
                } else {
                    cnt++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new MajorityElement().majorityElement3(new int[]{3,3,4}));
        System.out.println(new MajorityElement().majorityElement(new int[]{2,2,1,1,1,2,2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
