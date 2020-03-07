package algo.leetcode.slidingwindow;//今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分
//钟结束后离开。 
//
// 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一
//分钟的顾客就会不满意，不生气则他们是满意的。 
//
// 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。 
//
// 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。 
// 
//
// 示例： 
//
// 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
//输出：16
//解释：
//书店老板在最后 3 分钟保持冷静。
//感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
// 
//
// 
//
// 提示： 
//
// 
// 1 <= X <= customers.length == grumpy.length <= 20000 
// 0 <= customers[i] <= 1000 
// 0 <= grumpy[i] <= 1 
// 
// Related Topics 数组 Sliding Window


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum = 0;
        int addSum = 0;
        int addSumMax = 0;
        LinkedList<Integer> addQueue = new LinkedList<>();
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] > 0) {
                if (grumpy[i] == 1) {
                    // 生气，有加成
                    addQueue.add(customers[i]);
                    addSum += customers[i];
                } else {
                    // 不生气
                    int s = customers[i];
                    sum += s;
                    // 没有加成，增量为0
                    addQueue.add(0);
                }
            } else {
                // 没有客人，没有加成
                addQueue.add(0);
            }

            if (addQueue.size() == X) {
                // 窗口满足
                if (addSum > addSumMax) {
                    System.out.println(addQueue);
                    addSumMax = addSum;
                }
                // 最左边数据移除队列
                addSum -= addQueue.pollFirst();
            }
        }
        return sum + addSumMax;
    }

    public int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        // 没有"抑制情绪"技巧的原始满意度
        int sum = 0;
        // X 滑动窗口内增加的满意度
        int subSum = 0;
        // 窗口滑动过程中增加满意度的最大值
        int subSumMax = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                // 娇气的店长未生气，满意度有效
                sum += customers[i];
            }
            // 使用技巧当前位置增加的满意度
            subSum += grumpy[i]*customers[i];
            // 更新窗口满意度最大值
            subSumMax = Math.max(subSum, subSumMax);
            if (i>=X-1) {
                // 窗口达到最大值，移除窗口最左边满意度增加值，下一个窗口可以继续增加数据
                subSum -= grumpy[i-X+1]*customers[i-X+1];
            }

        }
        return sum + subSumMax;
    }

    public static void main(String[] args) {
        System.out.println(new MaxSatisfied().maxSatisfied2(
                new int[]{1, 0, 1, 2, 1, 1, 7, 5},
                new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
