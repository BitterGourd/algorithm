package sort;

/**
 * 基数排序
 */
public class RadixSort {
    public void radixSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    private void radixSort(int[] arr, int start, int end, int maxBits) {
        //桶的个数
        final int radix = 10;

        int[] bucket = new int[end - start + 1];
        //记录每个桶元素个数
        int[] count = new int[radix];

        //低位优先法
        //先个位排序，再按个位排好的数组十位排序，再百位排序...
        int i, j;
        // 1表示个位
        for (int bit = 1; bit <= maxBits; bit++) {
            for (i = 0; i < radix; i++) {
                count[i] = 0;
            }

            for (i = start; i <= end; i++) {
                j = getDigit(arr[i], bit);
                count[j]++;
            }

            //按当前数字位数（1为个位）把数组放入相应桶中
            for (i = 1; i < radix; i++) {
                count[i] += count[i - 1];
            }
            for (i = end; i >= start; i--) {
                j = getDigit(arr[i], bit);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }

            //更新arr数组的值
            for (i = start, j = 0; i <= end; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    /**
     * 获取元素在桶应该存放的位置
     */
    private int getDigit(int num, int bit) {
        return (num / (int) (Math.pow(10, bit - 1))) % 10;
    }

    private int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        return String.valueOf(max).length();
    }
}
