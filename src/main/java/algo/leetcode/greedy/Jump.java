package algo.leetcode.greedy;

// 给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组


//leetcode submit region begin(Prohibit modification and deletion)
class Jump {
    /**
     * 从选择可跳跃的选项里面，选择下一次可以跳到最远的位置
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int cnt = 0;
        for (int i=0; i<nums.length;) {
            int selectIndex = i;
            int maxNext = i;
            for (int step=1; step<=nums[i]; step++) {
                int index = i + step;
                int nextIndex = index + nums[index];
                if (index>=nums.length-1) {
                    cnt++;
                    return cnt;
                } else if (nextIndex > maxNext) {
                    maxNext = nextIndex;
                    selectIndex = index;
                }
            }
            i = selectIndex;
            cnt++;
        }
        return cnt;
    }

    /**
     * 挨个跳
     *
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 11. 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     *
     * 如果从这个 起跳点 起跳叫做第 1 次 跳跃，那么从后面 3 个格子起跳 都 可以叫做第 2 次 跳跃。
     *
     * 所以，当一次 跳跃 结束时，从下一个格子开始，到现在 能跳到最远的距离，都 是下一次 跳跃 的 起跳点。
     * 31. 对每一次 跳跃 用 for 循环来模拟。
     * 32. 跳完一次之后，更新下一次 起跳点 的范围。
     * 33. 在新的范围内跳，更新 能跳到最远的距离。
     *
     * 记录 跳跃 次数，如果跳到了终点，就得到了结果。
     *
     * @param nums
     * @return
     */
    public int jump2(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if( i == end){ //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    public static void main(String[] args) {
        System.out.println(new Jump().jump(new int[]{1,2}));
        System.out.println(new Jump().jump(new int[]{1,1,1,1}));
        System.out.println(new Jump().jump(new int[]{2,3,1,1,4}));
        System.out.println(new Jump().jump(new int[]{1}));
        System.out.println(new Jump().jump(new int[]{0}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
