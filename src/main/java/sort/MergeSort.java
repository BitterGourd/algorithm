package sort;

/**
 * 归并排序
 *
 * 算法步骤：
 *  1.申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
 *  2.设定两个指针，最初位置分别为两个已经排序序列的起始位置
 *  3.比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
 *  4.重复步骤 3 直到某一指针达到序列尾
 *  5.将另一序列剩下的所有元素直接复制到合并序列尾
 */
public class MergeSort {
    public void mergeSort(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];

        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        //两个必有且只有1个越界，以下while只会执行一个，把还未填充的元素依次填入
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }
}
