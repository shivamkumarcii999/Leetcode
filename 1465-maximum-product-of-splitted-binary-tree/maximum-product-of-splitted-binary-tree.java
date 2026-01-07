class Solution {
    long totalSum = 0;
    long maxProduct = 0;
    static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // Step 1: find total sum of tree
        totalSum = getTotalSum(root);

        // Step 2: find max product by splitting
        findMaxProduct(root);

        return (int)(maxProduct % MOD);
    }

    // First DFS: compute total sum
    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }

    // Second DFS: compute subtree sums & max product
    private long findMaxProduct(TreeNode node) {
        if (node == null) return 0;

        long left = findMaxProduct(node.left);
        long right = findMaxProduct(node.right);

        long subTreeSum = node.val + left + right;

        long product = subTreeSum * (totalSum - subTreeSum);
        maxProduct = Math.max(maxProduct, product);

        return subTreeSum;
    }
}
