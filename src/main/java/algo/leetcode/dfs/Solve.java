package algo.leetcode.dfs;//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solve {
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        int x = board[0].length;
        int y = board.length;
        int i=0, j=0;
        while (i<x) {
            dfs(board, i, 0);
            dfs(board, i, y-1);
            i++;
        }
        while (j<board.length) {
            dfs(board, 0, j);
            dfs(board, x-1, j);
            j++;
        }

        for (i=0; i<x; i++) {
            for (j=0; j<y; j++) {
                if (board[j][i] == 'O') {
                    board[j][i] = 'X';
                }
                if (board[j][i] == '#') {
                    board[j][i] = 'O';
                }

            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (x < 0 || x>=board[0].length || y<0 || y>=board.length || board[y][x] != 'O') {
            return;
        }
        board[y][x] = '#';
        dfs(board, x+1, y);
        dfs(board, x-1, y);
        dfs(board, x, y-1);
        dfs(board, x, y+1);
    }

    public static void main(String[] args) {
        new Solve().solve(new char[][]{{'O'}});
    }

}
//leetcode submit region end(Prohibit modification and deletion)
