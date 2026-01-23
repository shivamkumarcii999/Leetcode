import java.util.*;

class Solution {
    // Node for Doubly Linked List to represent the current array state
    class Node {
        long val;
        int id;      // Original index to handle "leftmost" tie-breaking
        int version; // Incremented whenever the node's value changes
        Node prev, next;

        Node(long val, int id) {
            this.val = val;
            this.id = id;
            this.version = 0;
        }
    }

    // Pair to store in the Priority Queue
    class Pair implements Comparable<Pair> {
        long sum;
        Node left, right;
        int leftVer, rightVer;

        Pair(long sum, Node left, Node right) {
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.leftVer = left.version;
            this.rightVer = right.version;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            // Tie-breaker: choose the leftmost pair based on initial ID
            return Integer.compare(this.left.id, other.left.id);
        }
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        Node[] nodes = new Node[n];
        int inversions = 0;

        // 1. Build the Linked List and count initial inversions
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
            if (i > 0) {
                nodes[i].prev = nodes[i - 1];
                nodes[i - 1].next = nodes[i];
                if (nodes[i - 1].val > nodes[i].val) {
                    inversions++;
                }
            }
        }

        // 2. Early exit if already sorted
        if (inversions == 0) return 0;

        // 3. Populate Priority Queue with all initial adjacent pairs
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Pair(nodes[i].val + nodes[i + 1].val, nodes[i], nodes[i + 1]));
        }

        int operations = 0;
        Set<Node> deleted = new HashSet<>();

        // 4. Greedy Simulation
        while (inversions > 0 && !pq.isEmpty()) {
            Pair top = pq.poll();

            // Validate the pair: skip if nodes were deleted or values changed (stale version)
            if (deleted.contains(top.left) || deleted.contains(top.right) || 
                top.left.next != top.right || 
                top.left.version != top.leftVer || top.right.version != top.rightVer) {
                continue;
            }

            Node L = top.left;
            Node R = top.right;
            Node LL = L.prev;
            Node RR = R.next;

            // Remove inversions related to the current pair before merging
            if (LL != null && LL.val > L.val) inversions--;
            if (L.val > R.val) inversions--;
            if (RR != null && R.val > RR.val) inversions--;

            // Perform Merge: Update L with the new sum and delete R
            L.val = top.sum;
            L.version++; // Change version to invalidate old PQ entries involving this node
            L.next = RR;
            if (RR != null) RR.prev = L;
            deleted.add(R);

            // Add back inversions created by the new merged value
            if (LL != null && LL.val > L.val) inversions++;
            if (RR != null && L.val > RR.val) inversions++;

            // Add new possible pairs to Priority Queue
            if (LL != null) {
                pq.add(new Pair(LL.val + L.val, LL, L));
            }
            if (RR != null) {
                pq.add(new Pair(L.val + RR.val, L, RR));
            }

            operations++;
        }

        return operations;
    }
}