package sort;

/**
 * 简单选择排序
 *
 * 算法步骤：
 *  1.首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 *  2.再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
 *  3.重复第二步，直到所有元素均排序完毕
 */
public class SelectSort {
    public void selectSort(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
