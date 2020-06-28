package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache：最近最少使用缓存机制
 *
 * 对应 LeetCode 第 146 题 https://leetcode-cn.com/problems/lru-cache/
 */
public class LRUCache {

    // 保存需要缓存的数据，在此规定链表越靠近头部最近越常使用，越靠近尾部越少使用
    private DLinkedNode head, tail;
    // LRUCache 的容量，超过容量就需要进行淘汰
    private int capacity;
    // 保存链表各节点与 key 的映射，这样进行 get 操作时就能获得 O(1) 的时间复杂度
    private Map<Integer, DLinkedNode> map = new HashMap<>(16);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // 使用虚拟头/尾节点避免边界判断
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    /** 获取缓存数据 */
    public int get(int key) {
        DLinkedNode node = map.get(key);

        // key 不存在缓存中
        if (node == null) {
            return -1;
        } else {
            // key 存在缓存中，将此 node 移至链表头部
            moveNodeToHead(node);
            return node.value;
        }
    }

    /** 数据写入缓存 */
    public void put(int key, int value) {
        DLinkedNode node = map.get(key);

        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            map.put(key, newNode);
            // 添加至双向链表的头部
            addNodeToHead(newNode);
            // 如果 size 大于 capacity，移除链表尾部节点
            if (map.size() > capacity) {
                DLinkedNode tail1 = removeTail();
                // 同时删除尾节点映射
                map.remove(tail1.key);
            }
        } else {
            // 如果 key 存在，修改 value，并移到头部
            node.value = value;
            moveNodeToHead(node);
        }
    }

    /** 自定义双向链表，面试中设计题面试官一般不期望使用语言自带数据结构 */
    static class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private void removeNode(DLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void addNodeToHead(DLinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void moveNodeToHead(DLinkedNode node) {
        removeNode(node);
        addNodeToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }
}
