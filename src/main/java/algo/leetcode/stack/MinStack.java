package algo.leetcode.stack;//设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 
// push(x) -- 将元素 x 推入栈中。 
// pop() -- 删除栈顶的元素。 
// top() -- 获取栈顶元素。 
// getMin() -- 检索栈中的最小元素。 
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//

import java.util.Stack;

class MinStack {

    // 数据栈存储收据
    private Stack<Integer> data;
    // 辅助栈存储最小元素
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        helper = new Stack<>();
    }
    
    public void push(int x) {
        data.push(x);
        if (helper.empty() || x <= helper.peek()) {
            helper.push(x);
        }
    }
    
    public void pop() {
        if (data.empty()) {
            throw new RuntimeException("stack is empty, operation forbidden");
        }
        int val = data.pop();
        if (helper.peek() == val) {
            helper.pop();
        }
    }
    
    public int top() {
        if (data.empty()) {
            throw new RuntimeException("stack is empty, operation forbidden");
        }
        return data.peek();
    }

    public int getMin() {
        if (data.empty()) {
            throw new RuntimeException("stack is empty, operation forbidden");
        }
        return helper.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */