package algo.leetcode.greedy;

//老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
//
// 你需要按照以下要求，帮助老师给这些孩子分发糖果： 
//
// 
// 每个孩子至少分配到 1 个糖果。 
// 相邻的孩子中，评分高的孩子必须获得更多的糖果。 
// 
//
// 那么这样下来，老师至少需要准备多少颗糖果呢？ 
//
// 示例 1: 
//
// 输入: [1,0,2]
//输出: 5
//解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
// 
//
// 示例 2: 
//
// 输入: [1,2,2]
//输出: 4
//解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。 
// Related Topics 贪心算法


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Candy {
    /**
     * 方法一： 暴力破解
     *
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        if (ratings.length == 0) return 0;
        int[] ans = new int[ratings.length];
        Arrays.fill(ans, 1);
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 1; i < ratings.length; ) {
                if (ratings[i] > ratings[i - 1] && ans[i] <= ans[i - 1]) {
                    ans[i]++;
                    flag = true;
                }
                if (ratings[i] < ratings[i - 1] && ans[i] >= ans[i - 1]) {
                    ans[i - 1]++;
                    flag = true;
                }
                i++;
            }
        }
        int res = 0;
        for (int i = 0; i < ans.length; i++) {
            res += ans[i];
        }
        return res;
    }

    /**
     * 1.新建一个数组保存每个孩子的糖果数，开始全部给每个小孩都发一个糖；
     * 2.第一次遍历先从左向右 若右边的孩子评分比左边高 则右边的糖果数等于左边的糖果数加一
     * 3.第二次遍历先从右向左 若左边的孩子评分比右边高且左边的孩子糖果数小于等于右边的 则左边的糖果数等于右 边的糖果数加一 同时求和
     *
     * @param ratings
     */
    public int candy2(int[] ratings) {
        if (ratings.length == 0) return 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        for (int i=1; i<ratings.length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }

        for (int i=ratings.length-1; i>0; i--) {
            if (ratings[i] < ratings[i-1] && candies[i-1]<=candies[i]) {
                candies[i-1] = candies[i]+1;
            }
        }

        int sum = 0;
        for (int i=0; i<candies.length; i++) {
            sum += candies[i];
        }

        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new Candy().candy(new int[]{1, 6, 10, 8, 7, 3, 2}));
        System.out.println(new Candy().candy2(new int[]{1, 6, 10, 8, 7, 3, 2}));
        System.out.println(new Candy().candy2(new int[]{1, 2, 87, 87, 87, 2, 1}));
        System.out.println(new Candy().candy2(new int[]{1, 0, 2}));
        System.out.println(new Candy().candy2(new int[]{1, 2, 2}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
