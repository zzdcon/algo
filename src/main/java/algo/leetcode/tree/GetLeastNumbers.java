package algo.leetcode.tree;//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
// 
//
// 示例 1： 
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
// 
//
// 示例 2： 
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0] 
//
// 
//
// 限制： 
//
// 
// 0 <= k <= arr.length <= 10000 
// 0 <= arr[i] <= 10000 
// 
// Related Topics 堆 分治算法


import com.alibaba.fastjson.JSON;

import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[0];
        }
        int[] ans = new int[k];

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : arr) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(i);
            } else if (priorityQueue.peek() > i) {
                priorityQueue.poll();
                priorityQueue.add(i);
            }
        }

        int i = 0;
        while (!priorityQueue.isEmpty()) {
            ans[i++] = priorityQueue.poll();
        }
        return ans;
    }

    //    数据范围有限时直接计数排序就行了：O(N)
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || arr.length ==0 || k ==0)
            return new int[0];
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }

        int cnt = 0;
        int[] res = new int[k];
        for (int i=0; i<counter.length; i++) {
            while (counter[i]-- > 0 && cnt<k) {
                res[cnt++] = i;
            }
            if (cnt == k) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(new GetLeastNumbers().getLeastNumbers2(new int[]{0, 0, 0, 2, 0, 5}, 2)));
        System.out.println(JSON.toJSON(new GetLeastNumbers().getLeastNumbers2(new int[]{0, 1, 2, 1}, 1)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
