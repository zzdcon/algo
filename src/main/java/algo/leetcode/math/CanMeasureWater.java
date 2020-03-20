package algo.leetcode.math;

//有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
//
// 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。 
//
// 你允许： 
//
// 
// 装满任意一个水壶 
// 清空任意一个水壶 
// 从一个水壶向另外一个水壶倒水，直到装满或者倒空 
// 
//
// 示例 1: (From the famous "Die Hard" example) 
//
// 输入: x = 3, y = 5, z = 4
//输出: True
// 
//
// 示例 2: 
//
// 输入: x = 2, y = 6, z = 5
//输出: False
// 
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)
class CanMeasureWater {
    /**
     * 这个问题的解法来源于裴蜀定理。 若 a，b 为整数，且 gcd(a,b) = d，
     * 则对于任意的整数x，y，ax + by都一定是 d 的倍数。 特别地，一定存在整数 x，y，使得 ax + by = d 成立。 正好与本题的要求相符合。
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if (x ==0 && y == 0) {
            return z == 0;
        }
        return z==0 || (x+y >= z && z%gcd(x,y) == 0);

    }

    private int gcd(int x, int y) {
        return x == 0? y : gcd(y%x, x);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
