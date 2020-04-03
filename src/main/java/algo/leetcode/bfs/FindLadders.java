package algo.leetcode.bfs;


//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换
//需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法


import com.google.common.collect.Lists;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class FindLadders {

    List<List<String>> res = new ArrayList<>();
    int minListLen = Integer.MAX_VALUE;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return res;
        List<String> routine = new ArrayList<>();
        routine.add(beginWord);
        dfs(beginWord, endWord, wordList, routine);
        if (minListLen != Integer.MAX_VALUE) {
            Iterator<List<String>> iterator = res.iterator();
            while (iterator.hasNext()) {
                List<String> next = iterator.next();
                if (next.size() > minListLen) {
                    iterator.remove();
                }
            }
        }
        return res;
    }

    public void dfs(String beginWord, String endWord, List<String> wordList, List<String> routine) {
        if (wordList.isEmpty() || routine.size() > minListLen) {
            return;
        }

        if (beginWord.equals(endWord)) {
            res.add(routine);
            minListLen = routine.size();
            return;
        }

        List<String> list = wordWith1Diff(beginWord, wordList);
        for (String nextBegin : list) {
            List<String> routineSub = new ArrayList<>(routine);
            List<String> words = new ArrayList<>(wordList);
            routineSub.add(nextBegin);
            words.remove(nextBegin);
            dfs(nextBegin, endWord, words, routineSub);
        }
    }

    public List<String> wordWith1Diff(String beginWord, List<String> words) {
        List<String> wordsWith1Diff = new ArrayList<>();
        char[] chars = beginWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                chars[i] = j;
                String str = new String(chars);
                if (!str.equals(beginWord) && words.contains(str)) {
                    wordsWith1Diff.add(str);
                }
            }
            chars[i] = temp;
        }
        return wordsWith1Diff;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = Lists.newArrayList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(new FindLadders().findLadders("hit", "cog", strings));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
