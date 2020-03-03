package algo.leetcode.slidingwindow;

//给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
//
// 返回仅包含 1 的最长（连续）子数组的长度。 
//
// 
//
// 示例 1： 
//
// 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释： 
//[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。 
//
// 示例 2： 
//
// 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：
//[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 20000 
// 0 <= K <= A.length 
// A[i] 为 0 或 1 
// 
// Related Topics 双指针 Sliding Window


//leetcode submit region begin(Prohibit modification and deletion)
class LongestOnes {
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0;
        int ans = 0;
        int[] cnt = new int[182];
        while (right < A.length) {
            cnt[A[right]]++;
            ans = Math.max(ans, cnt[1]);
            if (ans + K < right - left + 1) {
                cnt[A[left]]--;
                left++;
            }
            right++;
        }
        return A.length - left;
    }

    public static void main(String[] args) {
        System.out.println(new LongestOnes().longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
