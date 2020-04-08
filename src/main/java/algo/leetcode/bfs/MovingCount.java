package algo.leetcode.bfs;//地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一
//格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但
//它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？ 
//
// 
//
// 示例 1： 
//
// 输入：m = 2, n = 3, k = 1
//输出：3
// 
//
// 示例 1： 
//
// 输入：m = 3, n = 1, k = 0
//输出：1
// 
//
// 提示： 
//
// 
// 1 <= n,m <= 100 
// 0 <= k <= 20 
// 
//


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class MovingCount {
    /**
     * 用队列实现的BFS
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount2(int m, int n, int k) {
        if (k < 0) return 0;
        int[][] area = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int res = 1;
        area[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = pos[0] + dir[i][0];
                int y = pos[1] + dir[i][1];
                if (x < n && y < m && x >= 0 && y >= 0 && area[y][x] == 0) {
                    //标志访问过
                    area[y][x] = 1;
                    if (sum(x) + sum(y) <= k) {
                        queue.offer(new int[]{x, y});
                        res++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * DFS
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    int ans = 0;

    public int movingCount(int m, int n, int k) {
        dfs(new int[m][n], 0, 0, k);
        return ans;
    }

    /**
     * 深度搜索
     * @param area 记忆走过的路径
     * @param x  x坐标
     * @param y  y坐标
     * @param k
     */
    public void dfs(int[][] area, int x, int y, int k) {
        if (x >= 0 && y >= 0 && x < area[0].length && y < area.length) {
            // 当前位置没有超出格子
            if (area[y][x] == 0 && sum(x) + sum(y) <= k) {
                // 没有被访问过且位数之和不大于k
                ans++;
                // 标记为已访问
                area[y][x] = 1;
                // 沿着上下左右四个方向遍历
                dfs(area, x + 1, y, k);
                dfs(area, x - 1, y, k);
                dfs(area, x, y + 1, k);
                dfs(area, x, y - 1, k);
            }
        }
    }

    /**
     * 计算位数之和
     * @param x
     * @return
     */
    private int sum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x = x / 10;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new MovingCount().movingCount2(16, 8, 4));
        System.out.println(new MovingCount().movingCount2(2, 3, 1));
        System.out.println(new MovingCount().movingCount2(3, 1, 0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
