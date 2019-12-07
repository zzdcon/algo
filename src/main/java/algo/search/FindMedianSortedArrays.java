package algo.search;//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法



//leetcode submit region begin(Prohibit modification and deletion)
class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int start=0, end=-1;
        if (len % 2 == 0) {
            start = len/2 - 1;
            end = start + 1;
        } else {
            start = len/2;
            end = start;
        }

        int mid1=0,mid2=0;

        int an1 = 0;
        int an2 = 0;
        for (int i = 0; i<len; i++) {
            int next;
            if (an1+1>nums1.length) {
                next = nums2[an2++];
            } else if (an2+1>nums2.length) {
                next = nums1[an1++];
            } else if (nums1[an1]<=nums2[an2]) {
                next=nums1[an1++];
            } else {
                next=nums2[an2++];
            }
            if (i==start) {
                if (len%2 == 1) {
                    return next;
                }
                mid1=next;
                continue;
            } else if (i == end) {
                mid2=next;
                return (mid1+mid2)*0.5;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        double medianSortedArrays = new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        System.out.println(medianSortedArrays);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
