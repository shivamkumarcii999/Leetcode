class Solution {

    int deepest = -1;
    int count = 0;
    TreeNode result = null;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // First traversal to find deepest level and count
        traverse(root, 0);

        // Second traversal to find subtree
        traverse2(root, 0);

        return result;
    }

    private void traverse(TreeNode node, int level) {
        if (node == null) return;

        if (level > deepest) {
            deepest = level;
            count = 1;
        } else if (level == deepest) {
            count++;
        }

        traverse(node.left, level + 1);
        traverse(node.right, level + 1);
    }

    private int traverse2(TreeNode node, int level) {
        if (node == null) return 0;

        int c = 0;
        if (level == deepest) {
            c = 1;
        }

        int left = traverse2(node.left, level + 1);
        int right = traverse2(node.right, level + 1);

        c += left + right;

        if (c == count && result == null) {
            result = node;
        }

        return c;
    }
}
