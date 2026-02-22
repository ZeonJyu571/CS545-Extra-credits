
//DFS
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null){
        return true;
    }
    else if (p == null || q == null){
        return false;
    }
    else if (p.val != q.val) {
        return false;
    }
    else{
        retrurn (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}

//BFS
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null){
        return true;
    }
    else if (p == null || q == null){
        return false;
    }

    Queue<TreeNode> frontier1 = new ArrayDeque<TreeNode>();
    Queue<TreeNode> frontier2 = new ArrayDeque<TreeNode>();

    frontier1.offer(p);
    frontier2.offer(q);

    while (!frontier1.isEmpty() && !frontier2.isEmpty()) {
        TreeNode curr1 = frontier1.poll();
        TreeNode curr2 = frontier2.poll();

        TreeNode left1 = curr1.left, left2 = curr2.left, right1 = curr1.right, right2 = curr2.right;

        if (left1 == null ^ left2 == null) {
            return false;
        }
        if (right1 == null ^ right2 == null) {
            return false;
        }
        if (left1 != null){
            frontier1.offer(left1);
        }
        if (left2 != null){
            frontier2.offer(left2);
        }
        if (right1 != null){
            frontier1.offer(right1);
        }
        if (right2 != null){
            frontier2.offer(right2);
        }
    }
    return (frontier1.isEmpty() && frontier2.isEmpty());
}