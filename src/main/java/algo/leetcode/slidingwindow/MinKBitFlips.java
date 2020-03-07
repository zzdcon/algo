package algo.leetcode.slidingwindow;
//在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0
//。 
//
// 返回所需的 K 位翻转的次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。 
//
// 
//
// 示例 1： 
//
// 输入：A = [0,1,0], K = 1
//输出：2
//解释：先翻转 A[0]，然后翻转 A[2]。
// 
//
// 示例 2： 
//
// 输入：A = [1,1,0], K = 2
//输出：-1
//解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
// 
//
// 示例 3： 
//
// 输入：A = [0,0,0,1,0,1,1,0], K = 3
//输出：3
//解释：
//翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
//翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
//翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 30000 
// 1 <= K <= A.length 
// 
// Related Topics 贪心算法 Sliding Window


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MinKBitFlips {

    /**
     * 贪心算法， 从第一个0开始往后翻转K个数据
     *
     *              执行耗时:2080 ms,击败了5.79% 的Java用户
     * 				内存消耗:48 MB,击败了6.67% 的Java用户
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int len = A.length;
        int ans = 0;
        for (int i=0;i<len; i++) {
            if (A[i] == 0) {
                if (i+K > len) {
                    return -1;
                }
                ans++;
                for (int j=0; j<K; j++) {
                    A[i+j] = 1^A[i+j];
                }
            }
        }
        return ans;
    }

    /**
     * 队列保存翻转历史，队列的大小为偶数则表示当前位置没有翻转，为奇数表示翻转过一次
     *
     * 	            执行耗时:60 ms,击败了56.10% 的Java用户
     * 				内存消耗:42.6 MB,击败了27.69% 的Java用户
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips2(int[] A, int K) {
        LinkedList<Integer> queue = new LinkedList<>();
        int ans = 0;
        for (int i=0; i<A.length; i++) {
            if (!queue.isEmpty() && queue.getFirst() < i - K + 1) {
                // 之前超出K翻转的不影响i, 弹出堆首元素离当前位置i不超过K
                queue.removeFirst();
            }
            if (queue.size()%2 == A[i]) {
                // 需要翻转（已翻转偶数次且当前为0 或者 已翻转奇数次且当前为1）
                if (i+K > A.length) {
                    //不满足翻转的窗口大小
                    return -1;
                }
                queue.add(i);
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MinKBitFlips().minKBitFlips2(new int[]{0,1,0}, 1));
        System.out.println(new MinKBitFlips().minKBitFlips2(new int[]{1,1,0}, 2));
        System.out.println(new MinKBitFlips().minKBitFlips2(new int[]{0,0,0,1,0,1,1,0}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
