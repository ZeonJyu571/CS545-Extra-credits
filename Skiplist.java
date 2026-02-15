import java.util.Arrays;
import java.util.Random;

public class Skiplist {

    private static final int MAX_LEVEL = 100;
    private static final double PROBABILITY = 0.5;
    private Node head;
    private int level;
    private Random rand;

    public Skiplist(){
        head = new Node(-100, MAX_LEVEL);
        level = 0;
        rand = new Random();
    }

    public boolean search(int target){
        // 1. Find the rightmost node at each level that is smaller than the target.
        // 2. On the base level, move one step forward, so that the curr would be >= target.
        // 3. Check whether it is equal to the target.

        Node curr = head;

        for (int i = level - 1; i >= 0; i--){
            while (curr.forward_list[i] != null && curr.forward_list[i].val < target){
                curr = curr.forward_list[i];
            }
        }
        curr = curr.forward_list[0];
        return curr != null && curr.val == target;
    }

    public void add(int num){
        // 1. Initialize the node to be inserted
        // 2. Find out the rightmost node at each level that is smaller than the node to be inserted, then mark them down in an array
        // 3. Point the node to be inserted to the next node of these nodes, then point these nodes to the one pending insertion

        Node curr = head;
        Node[] rightmost = new Node[MAX_LEVEL];
        Arrays.fill(rightmost, head);

        int lv = level_generator();
        level = Math.max(level, lv);
        Node to_be_inserted = new Node(num,lv);

        for (int i = level - 1; i >= 0; i--){
            while (curr.forward_list[i] != null && curr.forward_list[i].val < num){
                curr = curr.forward_list[i];
            }
            rightmost[i] = curr;
        }

        for (int i = lv - 1; i >= 0; i--){
            to_be_inserted.forward_list[i] = rightmost[i].forward_list[i];
            rightmost[i].forward_list[i] = to_be_inserted;
        }
    }

    public boolean erase(int num){
        Node curr = head;
        Node[] rightmost = new Node[MAX_LEVEL];

        for (int i = level - 1; i >= 0; i--){
            while(curr.forward_list[i] != null && curr.forward_list[i].val < num){
                curr = curr.forward_list[i];
            }
            rightmost[i] = curr;
        }
        curr = curr.forward_list[0];
        if(curr == null || curr.val != num){
            return false;
        }
        for(int i = 0; i < level; i++){
            if(rightmost[i].forward_list[i] != curr){
                break;
            }
            rightmost[i].forward_list[i] = curr.forward_list[i];
        }
        // in case the deleted node has the highest level
        for(int i = level - 1; i >= 1; i--){
            if(head.forward_list[i] == null){
                level--;
            }
        }
        return true;
    }

    private int level_generator(){
        int lv = 1;
        while(rand.nextDouble() < PROBABILITY && lv < MAX_LEVEL){
            lv++;
        }
        return lv;
    }

}

class Node{
    int val;
    Node[] forward_list;

    public Node(int val, int max_level){
        this.val = val;
        this.forward_list = new Node[max_level];
    }
}