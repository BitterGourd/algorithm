package sort;

import java.util.Random;

/**
 * 快速排序
 * 思路解读参考：https://blog.csdn.net/shujuelin/article/details/82423852
 */
public class NormalQuickSort {

    private Random random = new Random();

    public void quickSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            int p = partition(arr, L, R);
            quickSort(arr, L, p - 1);
            quickSort(arr, p + 1, R);
        }
    }

    private int partition(int[] arr, int L, int R) {
        // 加随机，使时间复杂度更趋于 O(N*logN)
        // swap(arr, L, random.nextInt(R - L + 1) + L);

        // 以数组首元素为基准，也可以数组尾元素
        int pivot = arr[L];
        int lt = L, gt = R;
        while (lt < gt) {
            // 1.先从右往左找到小于基准 pivot 的
            // 一定要先从右往左探测
            while (lt < gt && arr[gt] >= pivot) {
                gt--;
            }
            // 2.再从左往右找到大于基准 pivot 的
            while (lt < gt && arr[lt] <= pivot) {
                lt++;
            }

            // 3.交换 lt 与 gt 的值，lt 继续往右，gt 继续往左，直到 lt==gt
            // 注意：lt、gt 每次交换完毕，lt 所在元素一定小于等于基准 pivot
            swap(arr, lt, gt);
        }

        // 此时 lt==gt，并且 lt/gt 左边的值小于等于基准 pivot，右边的值大于等于基准 pivot
        // 当前基准 pivot 探测结束，交换基准位置和 lt/gt 位置所在的元素
        swap(arr, L, lt);

        return lt;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
