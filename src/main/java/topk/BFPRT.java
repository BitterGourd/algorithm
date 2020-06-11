package topk;

/**
 * TOP K 问题 (第 N 小、第 N 大)
 * 时间复杂度为 O(n), 稳定的 O(n)
 *
 * 思路 :
 *  1. 分组, 相邻的 5 个数一组, 把数组 arr 分为多个组, 剩余不足 5 个的数单独成一组      O(1)
 *  2. 组内排序, 每组 5 个数之间排序, 跨组不排序                                     O(N)
 *  3. 把每个组中位数取出来构成新数组, 长度 N/5                                      O(N)
 *  4. 递归调用 BFPRT 算法, 目的是找到新数组的中位数                                 T(N/5)
 *  5. 用新数组的中位数作为枢轴 pivot 去 partition                                  O(N)
 *  6. 如果命中直接返回, 没命中根据 pivot 判断走左边还是走右边                        T(7N/10)
 *  总的时间复杂度 = T(N/5) + T(7N/10) + O(N) = O(N)
 */
public class BFPRT {
    /** 获取前 K 小 */
    public int[] getMinKNums(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }

        int minKth = getMinKth(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }

        for (; index < res.length; index++) {
            res[index] = minKth;
        }

        return res;
    }

    private int bfprt(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }

        // pivot 枢轴
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            return bfprt(arr, begin, pivotRange[0] - 1, i);
        } else {
            return bfprt(arr, pivotRange[1] + 1, end, i);
        }
    }

    private int getMinKth(int[] arr, int k) {
        int[] copyArr = copyArray(arr);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    private int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }

        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    /**
     * 中位数的中位数
     * 先把 arr 划分为每组 5 个数的多个组(最后不足 5 个的单独 1 组), 再取多个组组成的数组的中位数
     */
    private int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }

        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    private void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i <= end; i++) {
            for (int j = i; j > begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    private int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, res.length);
        return res;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
