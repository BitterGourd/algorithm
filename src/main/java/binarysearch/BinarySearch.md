如果需要在排序数组中进行搜索，或题目要求时间复杂度为 O(logn)，一般使用二分查找算法

二分查找的思路不难理解，但是边界条件容易出错，以下是套用模板，需要注意循环条件和指针位置的更新

##### 模板一

```java
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) { // 注意
        int mid = left + (right - left) >> 1;
        if (nums[mid] == target) {
            // 相关逻辑
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1; // 注意
        }
    }

    // 注意
    // 或根据场景，一般返回 left
    return -1;
}
```

##### 模板二

```java
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length;
    while (left < right) { // 注意
        int mid = left + (right - left) >> 1;
        if (nums[mid] == target) {
            // 相关逻辑
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid; // 注意
        }
    }

    // 注意
    // 或根据场景，一般返回 left
    return -1;
}
```

