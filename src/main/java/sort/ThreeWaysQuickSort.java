package sort;

/**
 * 三路随机快速排序
 *
 * 算法步骤：
 *  1.从数列中挑出一个元素，称为 "基准"（pivot）
 *  2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）
 *    在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 *  3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
 */
public class ThreeWaysQuickSort {

    public void quickSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    private int[] partition(int[] arr, int L, int R) {
        //加上这个swap随机快排，使复杂度期望接近O(n*logn)，不出现大幅波动
        swap(arr, (int) (Math.random() * (R - L + 1)) + L, R);

        int less = L - 1;
        //选取最后一个数作为基准判断
        int greater = R;
        while (L < greater) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                swap(arr, --greater, L);
            } else {
                L++;
            }
        }
        swap(arr, greater, R);

        //返回的是等于选择基准的左右边界
        return new int[]{less + 1, greater};
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
