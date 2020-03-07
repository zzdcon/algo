package algo.leetcode.slidingwindow;
//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
// Related Topics 堆 Sliding Window


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxSlidingWindow {
//    时间复杂度的话，循环中主要是调用了 remove 函数和 offer 函数，虽然 offer 函数的时间复杂度是 log 级的，但是 remove 是 O(k) ，所以最终的时间复杂度依旧是 O(nk)。
//
//    作者：windliang
//    链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-3/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k==0) {
            return new int[]{};
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(( (o1, o2) -> o2.intValue() - o1.intValue()));
        int i=0;
        int j=0;
        int[] ans = new int[nums.length-k+1];
        while (i < nums.length) {
            if (heap.size()>=k) {
                heap.remove(nums[i-k]);
            }
            heap.offer(nums[i]);
            if (i>=k-1){
                ans[j++] = heap.peek();
            }
            i++;
        }
        return ans;
    }

//    作者：windliang
//    链接：https://leetcode-cn.com/problems/sliding-window-maximum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-5-3/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] maxSlidingWindow2(int[] nums, int k) {
        Deque<Integer> max = new ArrayDeque<>();
        int n = nums.length;
        if (n == 0) {
            return nums;
        }
        int result[] = new int[n - k + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (i >= k) {
                if (max.peekFirst() == nums[i - k]) {
                    max.removeFirst();
                }
            }
            while (!max.isEmpty() && nums[i] > max.peekLast()) {
                //不用保留比nums[i]小的数字的原因是
                //nums[i]进来早的数字也比nums[i]更先弹出，所以不用担心最大值是被扔掉的数字
                max.removeLast();
            }

            max.addLast(nums[i]);
            if (i >= k - 1) {
                result[index++] = max.peekFirst();
            }
        }
        return result;
    }



    public static void main(String[] args) {
        int[] ints = new MaxSlidingWindow().maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(ints);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
