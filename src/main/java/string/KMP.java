package string;

/**
 * KMP 算法
 * 字符串匹配问题
 * 时间复杂度 O(m+n)
 */
public class KMP {

    /** 返回给定 pattern 在 s 出现的起始索引 */
    public int kmp(String s, String pattern) {

        if (s == null || pattern == null || pattern.length() < 1 || s.length() < pattern.length()) {
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = pattern.toCharArray();

        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            }
            // s 与 pattern 第1个字符不相等，pattern 字符串往后挪一位再比较
            else if (next[i2] == -1) {
                i1++;
            }
            // s 的 i1 位置与 pattern 的 i2 位置不等，i2 位置之前还有与 s 相等的字符串
            else {
                i2 = next[i2];
            }
        }

        return i2 == str2.length ? i1 - i2 : -1;
    }

    /** 返回的是 str2 字符数组每个下标 n 对应的前 n-1 个字符最大相同前后缀的长度 */
    private int[] getNextArray(char[] str2) {

        if (str2.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[str2.length];
        // 规定模版字符串的第1个字符的最大相同前后缀长度为-1
        next[0] = -1;
        // 第2个字符的最大相同前后缀长度为0
        next[1] = 0;

        int i = 2;
        // cn 表示 i 的最大相同前后缀的前缀再往后一位
        int cn = 0;
        while (i < next.length) {
            // 如果 i-1 的字符与 i-1 的最大相同前后缀的前缀的后一位字符相等
            if (str2[i - 1] == str2[cn]) {
                // 则 i 的最大相同前后缀加1
                next[i++] = ++cn;
            }
            // cn 还能往前移动，找到 cn 的最大相同前后缀
            else if (cn > 0) {
                // 减小相同前后缀的长度，把 cn 往前挪到 cn'
                cn = next[cn];
            }
            // cn=0 ，表示 i 没有相同前后缀
            else {
                next[i++] = 0;
            }
        }

        return next;
    }
}
