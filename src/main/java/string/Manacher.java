package string;

/**
 * Manacher 算法
 * 回文串问题
 * 时间复杂度 O(n)
 */
public class Manacher {
    /** 最长回文长度 */
    public int manacher(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] charArray = manacherString(str);
        // pArr[i]表示以 i 为中心的最长回文的半径,只有自身半径算1
        int[] pArr = new int[charArray.length];
        // 目前最长回文的中点
        int C = -1;
        // 以 C 为中心的最长回文的右边界，也就是 R=i+pArr[i]
        int R = -1;
        int max = Integer.MAX_VALUE;
        /*
         *  1) i 在 R 外
         *          -- 暴力扩回文右边界
         *  2) i 在 R 内
         *      A : i 关于 C 的对称点在以 C 为中心的最长回文的左边界内
         *          -- 直接确定以 i 为中心的最长回文的半径为1
         *      B : i 关于 C 的对称点在以 C 为中心的最长回文的左边界上
         *          -- i~R 段不用验证，可以确定是以 i 为中心的最长回文的一部分，边界 R 外的需要验证
         *      C : i 关于 C 的对称点在以 C 为中心的最长回文的左边界外
         *          -- 直接确定以 i 为中心的最长回文的半径为1
         */
        for (int i = 0; i < charArray.length; i++) {
            // i < R，即 i 在 R 内，2*C-i 是 i 关于 C 的对称点
            // Math.min(pArr[2 * C - i], R - i) : 起码不用验证的区域
            // A情况时多扩 R - i ，减少代码总量
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 尝试扩展目前最长回文的右边界，暴力扩
            while (i + pArr[i] < charArray.length && i - pArr[i] > -1) {
                if (charArray[i + pArr[i]] == charArray[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            // 我们每走一步 i，都要和 R 比较，我们希望 R 尽可能的远
            // 这样才有机会执行 i < R 这句代码
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            max = Math.max(max, pArr[i]);
        }

        return max - 1;
    }

    /** 格式化字符串，给每个字符左右两边加 # 号，使得无论是奇数个字符还是偶数个字符最后结果都是奇数个字符 */
    private char[] manacherString(String str) {
        char[] charArray = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];

        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArray[index++];
        }

        return res;
    }
}
