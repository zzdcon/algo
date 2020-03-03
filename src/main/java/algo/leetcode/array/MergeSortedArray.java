package algo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MergeSortedArray {
    public void merge(int[] A, int m, int[] B, int n) {
        int lastIndex = 0;
        int match = 0;
        for (int i = 0; i < n; i++) {
            for (int j = lastIndex; j < A.length; j++) {
                if (B[i] < A[j] || j > m + match - 1) {
                    lastIndex = j;
                    match++;
                    int k = m + match - 1;
                    while (k > lastIndex) {
                        A[k] = A[k - 1];
                        k--;
                    }
                    A[lastIndex] = B[i];
                    break;
                }
            }
        }
    }

    public void merge2(int[] A, int m, int[] B, int n) {
        int a = 0;
        int b = 0;
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (a >= m) {
                ans[i] = B[b++];
            } else if (b >= n) {
                ans[i] = A[a++];
            } else if (A[a] > B[b]) {
                ans[i] = B[b++];
            } else {
                ans[i] = A[a++];
            }
        }
        for (int i=0; i<A.length;i++) {
            A[i] = ans[i];
        }

    }


}