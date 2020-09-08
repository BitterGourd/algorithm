package binarysearch;

/**
 * 以 LeetCode 第 35 题 【搜索插入位置】 为例
 * 题目链接：https://leetcode-cn.com/problems/search-insert-position/
 */
public class BinarySearch {

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
