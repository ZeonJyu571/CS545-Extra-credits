

// DFS
public int maxDepth(TreeNode root) {
    if (root == null){
        return 0;
    }
    else{
        int l_height = maxDepth(root.left);
        int r_height = maxDepth(root.right);
        return Math.max(l_height, r_height) + 1;
    }
}

//BFS
public int maxDepth(TreeNode root){
    if (root == null){
        return 0;
    }
    int height = 0;
    Queue<TreeNode> frontier = new ArrayDeque<TreeNode>();
    frontier.offer(root);
    while (!frontier.isEmpty()){
        int size = frontier.size();
        TreeNode curr = frontier.poll();
        if (curr.left != null){
            frontier.offer(curr.left);
        }
        if (curr.right != null){
            frontier.offer(curr.right);
        }
        size --;
    }
    height ++;

    return height;
}