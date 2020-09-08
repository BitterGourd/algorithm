package bitmap;

import java.util.BitSet;

/**
 * bitMap 算法 Java 内部实现 {@link BitSet}，推荐阅读 https://juejin.im/post/6844904071548108813
 *
 * 底层使用 long[] 保存数据，每个 long 类型的数值在二进制上能表示 64 个数的状态(0/1)
 * 多个 long 数值就能表示更多数的状态
 */
public class BitMap {

    public static void main(String[] args) {
        BitSet bitSet = new BitSet();

        int[] value = {1, 2, 3, 10, 45};
        for(int v : value) {
            bitSet.set(v);
        }

        // 64，对应二进制的下标 0-63
        System.out.println(bitSet.size());
        // 1000000000000000000000000000000000010000001110
        System.out.println(Long.toBinaryString(bitSet.toLongArray()[0]));
    }
}
