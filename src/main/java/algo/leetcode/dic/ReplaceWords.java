package algo.leetcode.dic;
//在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词
//根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
//
// 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继
//承词有许多可以形成它的词根，则用最短的词根替换它。
//
// 你需要输出替换之后的句子。
//
//
//
// 示例 1：
//
//
//输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by
//the battery"
//输出："the cat was rat by the bat"
//
//
// 示例 2：
//
//
//输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
//输出："a a b c"
//
//
//
//
// 提示：
//
//
// 1 <= dictionary.length <= 1000
// 1 <= dictionary[i].length <= 100
// dictionary[i] 仅由小写字母组成。
// 1 <= sentence.length <= 10^6
// sentence 仅由小写字母和空格组成。
// sentence 中单词的总量在范围 [1, 1000] 内。
// sentence 中每个单词的长度在范围 [1, 1000] 内。
// sentence 中单词之间由一个空格隔开。
// sentence 没有前导或尾随空格。
//
//
//
//
// Related Topics 字典树 数组 哈希表 字符串 👍 291 👎 0


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class ReplaceWords {
    // 方法一：字符串逐段检查是否位于字典中
    public String replaceWords1(List<String> dictionary, String sentence) {
        Set<String> dicSet = new HashSet<>();
        for(String dict : dictionary) {
            dicSet.add(dict);
        }
        String[] words = sentence.split(" ");
        for(int i=0; i<words.length; i++) {
            for(int j=0; j<words[i].length(); j++) {
                if(dicSet.contains(words[i].substring(0, j))) {
                    words[i] = words[i].substring(0,j);
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    // 方法二： 字典法
    public String replaceWords2(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        for(String dic : dictionary) {
            TrieNode cur = root;
            for(int i = 0; i<dic.length(); i++) {
                cur.childrien.putIfAbsent(dic.charAt(i), new TrieNode());
                cur = cur.childrien.get(dic.charAt(i));
            }
            cur.childrien.put('#', new TrieNode());
        }
        String[] words = sentence.split(" ");
        for(int i=0; i<words.length; i++) {
            String newWord = findNode(words[i], root);
            if(newWord != null) {
                words[i] = newWord;
            }
        }
        return String.join(" ", words);
    }

    class TrieNode {
        public Map<Character, TrieNode> childrien;
        public TrieNode() {
            childrien = new HashMap<>();
        }

    }

    String findNode(String word, TrieNode root) {
        TrieNode cur = root;
        for(int i=0; i<word.length(); i++) {
            if(cur.childrien.containsKey(word.charAt(i))) {
                cur = cur.childrien.get(word.charAt(i));
                if(cur.childrien.containsKey('#')) {
                    return word.substring(0, i+1);
                }
            } else {
                return null;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        try {
            List<String> dictionary = JSONArray.parseArray( "[\"cat\",\"bat\",\"rat\"]", String.class);
            String sentence = "the cattle was rattled by the battery";
            System.out.println(new ReplaceWords().replaceWords2(dictionary, sentence));
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
