package algo.leetcode.str;//给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
//
// 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0,
// 2, 5]。 
//
// 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。 
//
// 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？ 
//
// 
//
// 示例： 
//
// 输入: words = ["time", "me", "bell"]
//输出: 10
//说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 2000 
// 1 <= words[i].length <= 7 
// 每个单词都是小写字母 。 
// 
//


import algo.dataStructure.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumLengthEncoding {
    /**
     * 方法一：按字符串长度从长到短排序，然后拼接
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (w1, w2) -> w2.length() - w1.length());
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            String suffix = word + "#";
            if (sb.lastIndexOf(suffix) == -1) {
                sb.append(suffix);
            }
        }
        return sb.length();
    }

    /**
     * 方法二： 字典树, 反序插入字典树，字典树的叶子节点对应的单词即为符合条件的单词
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding2(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        Arrays.sort(words, (w1, w2)-> w2.length()-w1.length());
        TrieNode root = new TrieNode();
        int res = 0;
        for (String word : words) {
            if (root.insertWord(root, word)) {
                res += word.length() + 1;
            }
        }
        return res;
    }

    public int minimumLengthEncoding3(String[] words) {
        TrieNode trie = new TrieNode();
        Map<TrieNode, Integer> nodes = new HashMap();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            TrieNode cur = trie;
            for (int j = word.length() - 1; j >= 0; --j)
                cur = cur.get(word.charAt(j));
            nodes.put(cur, i);
        }

        int ans = 0;
        for (TrieNode node: nodes.keySet()) {
            if (node.cnt == 0)
                ans += words[nodes.get(node)].length() + 1;
        }
        return ans;

    }


    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int cnt;

        public TrieNode() {
        }

        public boolean insertWord(TrieNode root, String word) {
            boolean isNew = false;
            TrieNode cur = root;
            for (int i=word.length()-1; i>=0; i--) {
                int ch = word.charAt(i) - 'a';
                if (cur.children[ch] == null) {
                    cur.children[ch] = new TrieNode();
                    cnt++;
                    isNew = true;
                }
                cur = cur.children[ch];
            }
            return isNew;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                cnt++;
            }
            return children[c - 'a'];
        }

    }


    public static void main(String[] args) {
        System.out.println(new MinimumLengthEncoding().minimumLengthEncoding(new String[]{"time", "me", "bell"}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
