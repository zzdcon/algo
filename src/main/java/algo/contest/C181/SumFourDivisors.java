package algo.contest.C181;

//给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。
//
//        如果数组中不存在满足题意的整数，则返回 0 。
//
//
//
//        示例：
//
//        输入：nums = [21,4,7]
//        输出：32
//        解释：
//        21 有 4 个因数：1, 3, 7, 21
//        4 有 3 个因数：1, 2, 4
//        7 有 2 个因数：1, 7
//        答案仅为 21 的所有因数的和。
//
//
//        提示：
//
//        1 <= nums.length <= 10^4
//        1 <= nums[i] <= 10^5
class SumFourDivisors {
    public int sumFourDivisors(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int ans = 0;
        for (int num : nums) {
            int cnt = 0;
            int sum = 0;
            for (int i=2; i<=num/2; i++) {
                if (num % i == 0) {
                    cnt++;
                    sum += i;
                }
                if (cnt > 2) {
                    break;
                }
            }
            if (cnt == 2) {
                ans = ans + sum + 1 + num;
            }
        }
        return ans;
    }
}