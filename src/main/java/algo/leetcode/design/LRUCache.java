package algo.leetcode.design;

//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
//
// 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的
//数据值留出空间。 
//
// 进阶: 
//
// 你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得密钥 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得密钥 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {
    int capacity;
    DoubleLinkedList doubleLinkedList;
    Map<Integer, Node> map;
    int size;

    class Node {
        Node pre;
        Node post;
        int val;
        int key;

        public Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    class DoubleLinkedList {
        Node dummyHead;
        Node dummyTail;

        public DoubleLinkedList() {
            dummyHead = new Node(-1, -1);
            dummyTail = new Node(-1, -1);
            dummyHead.post = dummyTail;
            dummyTail.pre = dummyHead;
        }
    }

    public LRUCache(int capacity) {
        doubleLinkedList = new DoubleLinkedList();
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        // node是第一个，直接返回
        if (node == doubleLinkedList.dummyHead.post) {
            return node.val;
        }
        refreshNode(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
        } else {
            node.val = value;
        }
        refreshNode(node);
    }

    private void refreshNode(Node node) {
        if (size == capacity && !map.containsKey(node.key)) {
            // 队列已满，删除数据
            Node originTail = doubleLinkedList.dummyTail.pre;
            originTail.pre.post = doubleLinkedList.dummyTail;
            doubleLinkedList.dummyTail.pre = originTail.pre;
            map.remove(originTail.key);
            size--;
        }
        if (!map.containsKey(node.key)) {
            // 新数据，直接插入到head
            Node originHead = doubleLinkedList.dummyHead.post;
            node.post = originHead;
            originHead.pre = node;
            doubleLinkedList.dummyHead.post = node;
            node.pre = doubleLinkedList.dummyHead;
            size++;
        } else {
            // 已有数据，移动到head
            // 移除node
            node.pre.post = node.post;
            node.post.pre = node.pre;
            // node插入到head
            node.post = doubleLinkedList.dummyHead.post;
            doubleLinkedList.dummyHead.post.pre = node;
            doubleLinkedList.dummyHead.post = node;
            node.pre = doubleLinkedList.dummyHead;
        }
        map.put(node.key, node);
    }


    public static void main(String[] args) {
//        LRUCache lruCache = new LRUCache(2);
//        lruCache.put(1,1);
//        lruCache.put(2,2);
//        System.out.println(lruCache.get(1));
//        lruCache.put(3,3);
//        System.out.println(lruCache.get(2));
//        lruCache.put(4,4);
//        System.out.println(lruCache.get(1));
//        System.out.println(lruCache.get(3));
//        System.out.println(lruCache.get(4));

        LRUCache lruCache = new LRUCache(1);
        lruCache.put(2,1);
        System.out.println(lruCache.get(2));
        lruCache.put(3,2);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)