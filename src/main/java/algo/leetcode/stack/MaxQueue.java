package algo.leetcode.stack;


//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O
//(1)。 
//
// 若队列为空，pop_front 和 max_value 需要返回 -1 
//
// 示例 1： 
//
// 输入: 
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出: [null,null,null,2,1,2]
// 
//
// 示例 2： 
//
// 输入: 
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出: [null,-1,-1]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000 
// 1 <= value <= 10^5 
// 
// Related Topics 栈 Sliding Window


import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class MaxQueue {

    LinkedList<Integer> maxQueue;
    LinkedList<Integer> queue;
    public MaxQueue() {
        queue = new LinkedList<>();
        maxQueue = new LinkedList<>();
    }
    
    public int max_value() {
        if (!maxQueue.isEmpty()) {
            return maxQueue.peek();
        }
        return -1;
    }
    
    public void push_back(int value) {
        while (!maxQueue.isEmpty() && value>maxQueue.peekLast()) {
           maxQueue.pollLast();
        }
        maxQueue.add(value);
        queue.add(value);
    }
    
    public int pop_front() {
        if (!queue.isEmpty()) {
           int num = queue.poll();
           if (!maxQueue.isEmpty() && num == maxQueue.peek()) {
               maxQueue.poll();
           }
           return num;
        }

        return -1;
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(15);
        maxQueue.push_back(9);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
//leetcode submit region end(Prohibit modification and deletion)
