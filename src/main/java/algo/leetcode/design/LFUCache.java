package algo.leetcode.design;

//设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
//
// get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。 
//put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平
//局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。 
//
// 进阶： 
//你是否可以在 O(1) 时间复杂度内执行两项操作？ 
//
// 示例： 
//
// 
//LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回 1
//cache.put(3, 3);    // 去除 key 2
//cache.get(2);       // 返回 -1 (未找到key 2)
//cache.get(3);       // 返回 3
//cache.put(4, 4);    // 去除 key 1
//cache.get(1);       // 返回 -1 (未找到 key 1)
//cache.get(3);       // 返回 3
//cache.get(4);       // 返回 4 
// Related Topics 设计


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {
    // key到节点的映射
    Map<Integer, Node> map;
    // 频率到双向链表的映射
    Map<Integer, DoubleLinkedList> frequencyMap;
    // 缓存最大容量
    int capacity;
    // 当前总节点数目
    int size;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        frequencyMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        refreshNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node = map.get(key);
        if (node != null) {
            if (node.val == value){
                return;
            }
            node.val = value;
        } else {
            node = new Node(key, value);
        }
        refreshNode(node);
    }



    private void refreshNode(Node node) {
        // 超出容量，删除一个旧数据
        if (size == capacity && !map.containsKey(node.key)) {
            for (int i = 1; i <= frequencyMap.size(); i++) {
                DoubleLinkedList doubleLinkedList = frequencyMap.get(i);
                if (doubleLinkedList != null && doubleLinkedList.count > 0) {
                    // 删除频率最低的队列的尾部元素
                    Node originTail = doubleLinkedList.dummyTail.pre;
                    originTail.pre.next = doubleLinkedList.dummyTail;
                    doubleLinkedList.dummyTail.pre = originTail.pre;
                    size--;
                    doubleLinkedList.count--;
                    map.remove(originTail.key);
                    break;
                }
            }
        }
        map.put(node.key, node);
        //更新原频率map，从原队列删除
        DoubleLinkedList preDList = frequencyMap.get(node.frequency);
        if (preDList != null) {
            //从原频率链表删除该节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
            preDList.count--;
            size--;
        }
        // 在新频率链表加入节点
        node.frequency++;
        size++;
        DoubleLinkedList curDList = frequencyMap.get(node.frequency);
        if (curDList == null) {
            // 该频率的队列不存在，新建队列
            curDList = new DoubleLinkedList(node);
            frequencyMap.put(node.frequency, curDList);
        } else {
            // 插入到队列头部
            curDList.count++;
            node.next = curDList.dummyHead.next;
            curDList.dummyHead.next.pre = node;
            node.pre = curDList.dummyHead;
            curDList.dummyHead.next = node;
        }


    }

    class Node {
        Node pre;
        Node next;
        int frequency;
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", val=" + val +
                    '}';
        }
    }

    class DoubleLinkedList {
        Node dummyHead;
        Node dummyTail;
        int count;

        public DoubleLinkedList(Node node) {
            this.dummyHead = new Node(-1, -1);
            this.dummyTail = new Node(-1, -1);
            dummyHead.next = node;
            node.pre = dummyHead;
            node.next = dummyTail;
            dummyTail.pre = node;
            this.count = 1;
        }
    }


    public static void main(String[] args) {
//        LFUCache lfuCache = new LFUCache(2);
//        lfuCache.put(1, 1);
//        lfuCache.put(2, 2);
//        System.out.println(lfuCache.get(1));
//        lfuCache.put(3, 3);
//        System.out.println(lfuCache.get(2));
//        System.out.println(lfuCache.get(3));
//        lfuCache.put(4, 4);
//        System.out.println(lfuCache.get(1));
//        System.out.println(lfuCache.get(3));
//        System.out.println(lfuCache.get(4));

        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(2, 2);
        lfuCache.put(1, 1);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        lfuCache.put(3, 3);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(4));


    }

}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
