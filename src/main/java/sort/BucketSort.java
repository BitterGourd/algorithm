package sort;

/** 桶排序 */
public class BucketSort {
    /** only for 0~200 value , otherwise please use other sorting methods */
    public void bucketSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int[] bucket = new int[max + 1];
        for (int i = 0; i < bucket.length; i++) {
            bucket[arr[i]]++;
        }

        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = bucket[j];
            }
        }
    }
}
