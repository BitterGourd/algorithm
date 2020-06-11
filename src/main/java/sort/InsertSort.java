package sort;

/**
 * 直接插入排序
 *
 * 算法步骤：
 *  1.将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列
 *  2.从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置
 *    如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面
 */
public class InsertSort {
    public void insertSort(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
