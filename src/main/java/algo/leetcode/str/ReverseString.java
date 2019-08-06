package algo.leetcode.str;
//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 
//
// 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。 
//
// 
//
// 示例 1： 
//
// 输入：["h","e","l","l","o"]
//输出：["o","l","l","e","h"]
// 
//
// 示例 2： 
//
// 输入：["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"] 
//

class ReverseString {

    // 方法一： 交换值，需要注意临界点的判断
    public void reverseString(char[] s) {
        int length = s.length;
        for (int i=0; i<length/2; i++) {
            Character temp = s[i];
            s[i] = s[length-i-1];
            s[length-i-1] = temp;
        }
    }
    // 方法二： 双指针，一个指针从前到后遍历，另一个指针从后到前遍历，直到相遇
    public void reverseString2(char[] s) {
        int length = s.length;
        if (length < 2) {
            return;
        }

        int i = 0;
        int j = length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}