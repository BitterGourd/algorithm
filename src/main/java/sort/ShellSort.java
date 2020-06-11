package sort;

/**
 * 希尔排序
 *
 * 是插入排序的一种高效率的实现，也叫缩小增量排序
 * 简单的插入排序中，如果待排序列是正序时，时间复杂度是 O(n)
 * 如果序列是基本有序的，使用直接插入排序效率就非常高，希尔排序就利用了这个特点
 *
 * 基本思想是：先将整个待排记录序列分割成为若干子序列分别进行直接插入排序
 *           待整个序列中的记录基本有序时再对全体记录进行一次直接插入排序
 *
 * 算法步骤：
 *  1.选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1
 *  2.按增量序列个数 k，对序列进行 k 趟排序；
 *  3.每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，分别对各子表进行直接插入排序
 *    仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度
 */
public class ShellSort {
    public void shellSort(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        int increment = arr.length / 2;
        while (increment >= 1) {
            shellSort(arr, increment);
            increment /= 2;
        }
    }

    private void shellSort(int[] arr, int increment) {
        for (int i = increment; i < arr.length; i++) {
            int j = i - increment;
            int temp = arr[i];
            while (j >= 0 && arr[j] > temp) {
                arr[j + increment] = arr[j];
                j -= increment;
            }

            arr[j + increment] = temp;
        }
    }
}
