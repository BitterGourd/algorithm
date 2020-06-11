package sort;

/**
 * 冒泡排序
 *
 * 算法步骤：
 *  1.比较相邻的元素。如果第一个比第二个大，就交换他们两个
 *  2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数
 *  3.针对所有的元素重复以上的步骤，除了最后一个
 *  4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 */
public class BubbleSort {
    public void bubbleSort(int[] arr) {

        if (null == arr || arr.length < 2) {
            return;
        }

        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
