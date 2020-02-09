package algo.leetcode.search;//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 
//
// 你可以假设数组中不存在重复的元素。 
//
// 你的算法时间复杂度必须是 O(log n) 级别。 
//
// 示例 1: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
// 
//
// 示例 2: 
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1 
// Related Topics 数组 二分查找


import javax.sql.rowset.serial.SerialArray;

//leetcode submit region begin(Prohibit modification and deletion)
class Search {
    /**
     * 方法一： 二分法，判断先判断target, 再根据区间判断移动方向
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length-1;
        int mid;
        boolean left = target > nums[0];
        while (low <= high) {
            if (nums[low] == target) {
                return low;
            }
            if (nums[high] == target) {
                return high;
            }
            mid = (low + high)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target){
                if ((left && nums[mid] > nums[0]) || (!left && nums[mid] < nums[0])) {
                    low = mid+1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (!left && nums[mid] > nums[0]) {
                    low = mid+1;
                } else {
                    high = mid -1;
                }
            }
        }
        return -1;
    }

    /**
     * 方法二： 同样是二分法，但是先判断区间再判断移动方向
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            if (nums[low] == target) {
                return low;
            }

            if (nums[high] == target) {
                return high;
            }

            mid = low + (high - low)/2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                if (target > nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target < nums[high] && target > nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Search().search(new int[]{5, 1, 2, 3, 4}, 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
