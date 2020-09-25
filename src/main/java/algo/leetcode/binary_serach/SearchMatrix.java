package algo.leetcode.binary_serach;//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找 
// 👍 245 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SearchMatrix {

    /**
     * 方法一： 分别按列和行进行两次二分查找
     * 1. 取第一列数据进行二分查找，找到目标值可能所在行
     * 2. 在第一步得到的行中再次进行二分查找，找到目标值所在的列
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int i = searchY(matrix, target);
        if (i == -1) return false;
        return searchX(matrix[i], target);
    }

    public int searchY(int[][] matrix, int target) {
        int start = 0;
        int end = matrix.length - 1;
        if (target < matrix[start][0] || target > matrix[end][matrix[0].length - 1])
            return -1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (target == matrix[mid][0]) {
                return mid;
            } else if (target < matrix[mid][0]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (start + end) / 2;
    }

    public boolean searchX(int[] line, int target) {
        if (line.length == 0) return false;
        int start = 0;
        int end = line.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == line[mid]) {
                return true;
            } else if (target > line[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    /**
     * 方法二： 二维数组转一维数组，通过标准二分查找法进行查找
     * 注意到输入的 m x n 矩阵可以视为长度为 m x n的有序数组。
     * 由于该 虚 数组的序号可以由下式方便地转化为原矩阵中的行和列 (我们当然不会真的创建一个新数组) ，该有序数组非常适合二分查找。
     * row = idx / n ， col = idx % n。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0;
        int r = m * n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }


    /**
     * 方法三：缩小领域法
     *     因为每一行递增，每一列递增。所以我们可以从右上角往左下角找或者从左下角往右上角找。每次比较可以排除一行或者一列，时间复杂度为O(m+n)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        // 从左下角开始逐步缩小领域
        int m = matrix.length;
        int n = matrix[0].length;
        int row = m-1; int col = 0;
        while (row>=0 && col<n) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                row--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SearchMatrix().searchMatrix3(new int[][]{{1, 3}}, 3));
        System.out.println(new SearchMatrix().searchMatrix3(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 30));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
