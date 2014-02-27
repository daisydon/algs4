import java.util.ArrayList;

/**
 * Created by linyu on 2/24/14.
 */
public class RemoveSubTree {

    private class TreeInfo {
        private TreeNode node;
        private int sum;

        public TreeInfo() {

        }
        public TreeInfo node(TreeNode node) {
            this.node = node;
            return this;
        }
        public TreeInfo sum(int sum) {
            this.sum = sum;
            return this;
        }
        public int getSum() {
            return this.sum;
        }
    }

    public TreeInfo findSub(TreeNode root) {
        if (root != null) {
            int sum = root.getVal();
            for(TreeNode node: root.getChildren()) {
               sum += findSub(node).getSum();
            }

            if (sum == 0) {
                return null;
            }
            else {
                return new TreeInfo().node(root).sum(sum);
            }
        }
        return null;
    }

    public static void main(String[] args) {


    }
}
