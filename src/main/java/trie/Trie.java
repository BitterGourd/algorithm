package trie;

/**
 * Trie - 前缀树/字典树
 *
 * 对应 LeetCode 第 208 题  https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 * 此实现假设所有输入均由小写字母 a-z 构成
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * 向 Trie 树中插入键
     *
     * 从根开始搜索它对应于第一个键字符的链接。有两种情况：
     *      1.链接存在。沿着链接移动到树的下一个子层。算法继续搜索下一个键字符
     *      2.链接不存在。创建一个新的节点，并将它与父节点的链接相连，该链接与当前的键字符相匹配
     * 重复以上步骤，直到到达键的最后一个字符，然后将当前节点标记为结束节点，算法完成
     *
     * 时间复杂度：O(m) - m 为键长
     * 空间复杂度：O(m) - m 为键长。最坏的情况下，新插入的键和 Trie 树中已有的键没有公共前缀，此时需要添加 m 个结点
     */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if (!node.containsKey(curChar)) {
                node.put(curChar, new TrieNode());
            }   // 注意此时不能改动以前 node 的结束标记，因为该标记确定了前缀树中存在哪些 word
            node = node.get(curChar);
        }

        node.setEnd();
    }

    /**
     * 在 Trie 树中查找键
     *
     * 从根开始，检查当前节点中与键字符对应的链接。有两种情况：
     *      1.存在链接。移动到该链接后面路径中的下一个节点，并继续搜索下一个键字符
     *      2.不存在链接。若已无键字符，且当前结点标记为 isEnd，则返回 true。否则有两种可能，均返回 false :
     *          a)还有键字符剩余，但无法跟随 Trie 树的键路径，找不到键。
     *          b)没有键字符剩余，但当前结点没有标记为 isEnd。也就是说，待查找键只是 Trie 树中另一个键的前缀
     *
     * 时间复杂度：O(m) - m 为键长
     * 空间复杂度：O(1)
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    /**
     * 查找 Trie 树中的键前缀
     *
     * 与在 Trie 树中搜索键时使用的方法非常相似，从根遍历 Trie 树，
     * 直到键前缀中没有字符，或者无法用当前的键字符继续 Trie 中的路径
     * 与上面提到的【搜索键】算法唯一的区别是，到达键前缀的末尾时，总是返回 true
     * 不需要考虑当前 Trie 节点是否用 isEnd 标记，因为搜索的是键的前缀，而不是整个键
     *
     * 时间复杂度：O(m) - m 为键长
     * 空间复杂度：O(1)
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char curChar = prefix.charAt(i);
            if (node.containsKey(curChar)) {
                node = node.get(curChar);
            } else {
                return null;
            }
        }

        return node;
    }

    /** 前缀树的节点 */
    static class TrieNode {
        // 下一级节点
        private TrieNode[] links;
        // 每个前缀树节点下一级最多 26 个节点(假设所有输入均由小写字母 a-z 构成)
        private final int R = 26;
        // 标记当前节点(层)是否是结束节点(层) - 结束节点的意思是存在从根节点开始遍历到当前节点对应的 word
        // 此标记一旦变为 true 就不应该改变
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        /** 当前节点下一级是否包含值为 ch 的节点 */
        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }

        /** 获取节点下一个值为 ch 的节点，不存在则返回 null */
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }

        /** 创建或更新下一级值为 ch 的节点 */
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
