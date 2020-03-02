package algo.leetcode.heap;//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 堆 设计


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    private int balance = 0;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 -o1);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num){
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public void addNum1(int num) {
        if (balance == 0) {
            // 左右平衡，优先插入大顶堆
            balance++;
            if (minHeap.peek() != null && num > minHeap.peek()) {
                // 小顶堆堆顶元素插入大顶堆，新数据插入小顶堆
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            } else {
                // 直接插入大顶堆
                maxHeap.add(num);
            }
        } else {
            // 大顶堆数据多，需要插入小顶堆
            balance--;
            if (num < maxHeap.peek()) {
                // 大顶堆元素弹出，插入到小顶堆，新数据插入到大顶堆
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            } else {
                // 无须调整，直接插入小顶堆
                minHeap.add(num);
            }
        }

    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + (minHeap.peek() == null ? 0 : minHeap.peek()))/2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public double findMedian1() {
        if (balance == 0) {
            return (maxHeap.peek() + minHeap.peek())/2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(4);
        System.out.println(medianFinder.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
