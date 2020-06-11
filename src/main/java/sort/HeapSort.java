package sort;

/**
 * 堆排序
 *
 * 在不超出数组临界值的情况下，设某一节点下标（0开始）为 i，则：
 *      i 的左子节点 : 2 * i + 1
 *      i 的右子节点 : 2 * i + 2
 *      i 的父节点 : (i - 1) / 2
 *
 * 算法步骤：
 *  1.创建一个堆 H[0……n-1]
 *  2.把堆首（最大值）和堆尾互换
 *  3.把堆的尺寸缩小 1，并调用 shift_down(0)，目的是把新的数组顶端数据调整到相应位置
 *  4.重复步骤 2，直到堆的尺寸为 1
 */
public class HeapSort {

    public void heapSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapsize = arr.length;
        //逻辑上删除堆顶
        swap(arr, 0, --heapsize);
        while (heapsize > 0) {
            //下沉
            heapify(arr, 0, heapsize);
            swap(arr, 0, --heapsize);
        }
    }

    /** 构建大顶堆 */
    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index, int heapsize) {
        int leftChild = index * 2 + 1;
        while (leftChild < heapsize) {
            int largest = leftChild + 1 < heapsize && arr[leftChild] > arr[leftChild + 1] ? leftChild : leftChild + 1;
            largest = arr[largest] > arr[index] ? largest : index;

            if (largest == index) {
                break;
            }

            swap(arr, largest, index);
            index = largest;
            leftChild = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
