package algo.leetcode.slidingwindow;//中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
//
// 例如： 
//
// [2,3,4]，中位数是 3 
//
// [2,3]，中位数是 (2 + 3) / 2 = 2.5 
//
// 给出一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗
//口中元素的中位数，并输出由它们组成的数组。 
//
// 
//
// 示例： 
//
// 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。 
//
// 窗口位置                      中位数
//---------------               -----
//[1  3  -1] -3  5  3  6  7       1
// 1 [3  -1  -3] 5  3  6  7       -1
// 1  3 [-1  -3  5] 3  6  7       -1
// 1  3  -1 [-3  5  3] 6  7       3
// 1  3  -1  -3 [5  3  6] 7       5
// 1  3  -1  -3  5 [3  6  7]      6
// 
//
// 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。 
//
// 
//
// 提示： 
//
// 
// 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。 
// 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。 
// 
// Related Topics Sliding Window


import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianSlidingWindow {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new double[0];
        }
        int len = nums.length;
        double[] ans = new double[len-k+1];
        int j=0;
        for (int i=0; i< len; i++) {
            // 添加新数据
            maxHeap.offer(nums[i]);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            if (i>=k-1) {
                // 计算中位数
                ans[j++]= (k%2 == 0) ? (minHeap.peek()*0.5+maxHeap.peek()*0.5) : maxHeap.peek();
                // 移除数据
                if (nums[i-k+1] <= maxHeap.peek()) {
                    maxHeap.remove(nums[i-k+1]);
                } else {
                    minHeap.remove(nums[i-k+1]);
                }

            }

        }
        return ans;
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> o2 > o1 ? 1 : -1));
        maxHeap.add(1);
        maxHeap.add(-2147483648);
        maxHeap.add(5);
        System.out.println(maxHeap);


        System.out.println(new MedianSlidingWindow().medianSlidingWindow(new int[]{-2147483648,-2147483648,2147483647,-2147483648,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
