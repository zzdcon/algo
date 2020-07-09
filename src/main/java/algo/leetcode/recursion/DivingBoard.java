package algo.leetcode.recursion;
//你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方
//法，生成跳水板所有可能的长度。 
// 返回的长度需要从小到大排列。 
// 示例： 
// 输入：
//shorter = 1
//longer = 2
//k = 3
//输出： {3,4,5,6}
// 
// 提示： 
// 
// 0 < shorter <= longer 
// 0 <= k <= 100000 
// 
// Related Topics 递归 记忆化


import com.alibaba.fastjson.JSON;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class DivingBoard {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) return new int[0];
        if (shorter == longer) return new int[]{shorter*k};
        int[] res = new int[k+1];
        for (int i=0; i<=k; i++) {
            res[i] = shorter*(k-i)+longer*i;
        }
        return res;
    }

    public static void main(String[] args) {
        DivingBoard divingBoard = new DivingBoard();
        System.out.println(JSON.toJSON(divingBoard.divingBoard(1,2,3)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
