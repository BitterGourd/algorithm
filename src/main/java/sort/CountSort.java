package sort;

/**
 * 计数排序
 *
 * 对于一个输入数组中的一个元素 x, 只要我们知道了这个数组中比 x 小的元素的个数
 * 那么我们就可以直接把 x 放到（x + 1）的位置上, 这就是计数排序的基本思想
 *
 * 基于这个思想，计数排序的一个主要问题就是如何统计数组中元素的个数
 * 再加上输入数组中的元素都是 0-max 区间的一个整数这个条件
 * 那么就可以通过另外一个数组的地址表示输入元素的值
 * 数组的值表示元素个数的方法来进行统计
 *
 * 算法步骤：
 *  1.找出待排序的数组中最大和最小的元素
 *  2.统计数组中每个值为i的元素出现的次数，存入数组C的第i项
 *  3.对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）
 *  4.反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1
 */
public class CountSort {
    public void countSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }

        int max = max(arr);
        int[] count = new int[max + 1];
        // 为输入数组中每个元素计数
        for (int anArr : arr) {
            count[anArr]++;
        }

        // 计算各个数之前元素的总和
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        // 初始化一个新的数组存放排序后的元素
        int[] newArr = new int[count.length];
        for (int j = arr.length - 1; j >= 0; j--) {
            newArr[count[arr[j]] - 1] = arr[j];
            count[arr[j]]--;
        }

        System.arraycopy(newArr, 0, arr, 0, arr.length);
    }

    private int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }

        return max;
    }
}
