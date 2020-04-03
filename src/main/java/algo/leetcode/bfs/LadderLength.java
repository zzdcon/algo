package algo.leetcode.bfs;

//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
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
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索


import com.google.common.collect.Lists;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class LadderLength {
    int ans = Integer.MAX_VALUE;

    /**
     * 方法一： DFS 超时
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        wordList.remove(beginWord);
        dfs(beginWord, endWord, wordList, 0);
        return ans == Integer.MAX_VALUE ? 0 : ans+1;
    }

    private void dfs(String beginWord, String endWord, List<String> wordList, int depth) {
        if (depth > ans) return;
        if (beginWord.equals(endWord)) {
            ans = Math.min(depth, ans);
            return;
        }
        Set<String> list = wordsWith1Diff2(wordList, beginWord);
        if (list.isEmpty()){
            return;
        }
        for (String word : list) {
            int depthSub = depth;
            ArrayList<String> lefts = new ArrayList<>(wordList);
            lefts.remove(word);
            dfs(word, endWord, lefts, ++depthSub);
        }
    }

    private HashSet<String> wordsWith1Diff2(List<String> words, String target) {
        HashSet<String> ans = new HashSet<>();
        char[] chars = target.toCharArray();
        for (int j=0; j<chars.length; j++) {
            for (Character a = 'a'; a<'z'; a++) {
                char temp = chars[j];
                chars[j] = a;
                String newT = new String(chars);
                if (words.contains(newT)){
                    ans.add(newT);
                }
                chars[j] = temp;
            }
        }
        return ans;
    }


    /**
     * 双端BFS
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> starts = new HashSet<>();
        HashSet<String> ends = new HashSet<>();
        HashSet<String> words = new HashSet<>(wordList);
        starts.add(beginWord);
        ends.add(endWord);
        if (!words.contains(endWord)) {
            return 0;
        }
        return bfs(starts, ends, words, 2);
    }

    public int bfs(HashSet<String> starts, HashSet<String> ends, HashSet<String> words, int depth) {
        if (starts.size() > ends.size()) {
            return bfs(ends, starts, words, depth);
        }
        words.removeAll(starts);
        HashSet<String> nextStarts = new HashSet<>();

        for (String start : starts) {
            char[] chars = start.toCharArray();
            for (int j=0; j<chars.length; j++) {
                char temp = chars[j];
                for (Character a = 'a'; a<='z'; a++) {
                    chars[j] = a;
                    String nextStart = new String(chars);
                    if (words.contains(nextStart)){
                       if (ends.contains(nextStart)) {
                           return depth;
                       }
                       nextStarts.add(nextStart);
                    }
                }
                chars[j] = temp;
            }
        }
        if (starts.size() == 0) {
            return 0;
        }
        return bfs(nextStarts, ends, words, depth+1);
    }

    private int deBfs(HashSet<String> start, HashSet<String> end, HashSet<String> words, int depth) {
        if (start.size() > end.size()) {
            return deBfs(end, start, words, depth);
        }
        words.removeAll(start);
        HashSet<String> next = new HashSet<>();
        for (String str : start) {
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    chars[i] = j;
                    String word = new String(chars);
                    if (words.contains(word)) {
                        if (end.contains(word)) {
                            return depth;
                        }
                        next.add(word);
                    }
                }
                chars[i] = temp;
            }
        }
        if (start.isEmpty()) {
            return 0;
        }
        return deBfs(next, end, words, depth + 1);

    }



    public static void main(String[] args) {
//        System.out.println(new LadderLength().ladderLength("a", "c" ,   Lists.newArrayList("a","b","c")));
//        System.out.println(new LadderLength().ladderLength("lost", "miss" ,   Lists.newArrayList("most","mist","miss","lost","fist","fish")));
//        System.out.println(new LadderLength().ladderLength("leet", "code" ,   Lists.newArrayList("lest","leet","lose","code","lode","robe","lost")));
        System.out.println(new LadderLength().ladderLength1("hit", "cog" ,   Lists.newArrayList("hot","dot","dog","lot","log","cog")));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
