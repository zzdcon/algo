package algo.leetcode.array;//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 如果数组中不存在目标值，返回 [-1, -1]。 
//
// 示例 1: 
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: [3,4] 
//
// 示例 2: 
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: [-1,-1] 
// Related Topics 数组 二分查找


//leetcode submit region begin(Prohibit modification and deletion)
class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int i=-1, j=-1;
        if (nums == null || nums.length == 0) {
            return new int[] {i, j};
        }

        int low = 0;
        int high = nums.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                i=mid;
                j=mid;
                while (i-1 >= 0 && nums[i-1] == target) {
                    i--;
                }
                while (j+1 <=nums.length-1 && nums[j+1] == target) {
                    j++;
                }
                return new int[] {i, j};
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[] {i, j};
    }

    public static void main(String[] args) {
        int[] ints = new SearchRange().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(new SearchRange().searchRange(new int[] {5,7,7,8,8,10}, 8).toString());
        System.out.println(new SearchRange().searchRange(new int[] {5,7,7,8,8,10}, 6).toString());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
