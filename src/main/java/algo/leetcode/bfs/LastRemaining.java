package algo.leetcode.bfs;//0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。 
//
// 
//
// 示例 1： 
//
// 输入: n = 5, m = 3
//输出: 3
// 
//
// 示例 2： 
//
// 输入: n = 10, m = 17
//输出: 2
// 
//
// 
//
// 限制： 
//
// 
// 1 <= n <= 10^5 
// 1 <= m <= 10^6 
// 
//


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class LastRemaining {
    /**
     * 方法一： 约瑟夫环 问题
     * <p>
     * 最后一个剩下的数坐标为0， 在上一轮剩下两个数的时候，坐标为：m=3,  (0+m)%2 = 1, 即在上一轮的数据中坐标为1，
     * 继续往前 倒数第二轮时，所在坐标为 (1+m)%3 = 1
     * 继续迭代，知道找到第一轮，即没删除数据时，最后一个剩下的数字的坐标
     * <p>
     * <p>
     * <p>
     * 第四轮反推，补上 mm 个位置，然后模上当时的数组大小 22，位置是(0 + 3) % 2 = 1。
     * <p>
     * 第三轮反推，补上 mm 个位置，然后模上当时的数组大小 33，位置是(1 + 3) % 3 = 1。
     * <p>
     * 第二轮反推，补上 mm 个位置，然后模上当时的数组大小 44，位置是(1 + 3) % 4 = 0。
     * <p>
     * 第一轮反推，补上 mm 个位置，然后模上当时的数组大小 55，位置是(0 + 3) % 5 = 3
     * <p>
     * 作者：sweetieeyi
     * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    /**
     * 方法二： 列表
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<n; i++) {
            list.add(i);
        }

        int index = 0;
        while (list.size() > 1) {
            index = (index+m-1)%list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    public static void main(String[] args) {
//        System.out.println(new LastRemaining().lastRemaining(5, 3));
//        System.out.println(new LastRemaining().lastRemaining(10, 17));
//        System.out.println(new LastRemaining().lastRemaining(9, 13));
//        System.out.println(new LastRemaining().lastRemaining(78, 142));

        System.out.println(new LastRemaining().lastRemaining2(5, 3));
        System.out.println(new LastRemaining().lastRemaining2(10, 17));
        System.out.println(new LastRemaining().lastRemaining2(9, 13));
        System.out.println(new LastRemaining().lastRemaining2(78, 142));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
