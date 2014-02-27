import java.util.ArrayList;

/**
 * Created by linyu on 2/24/14.
 */
public class TreeNode {
    private int val;
    private ArrayList<TreeNode> children = new ArrayList<TreeNode>();

    public TreeNode () {

    }

    public TreeNode val(int val) {
        this.val = val;
        return this;
    }

    public int getVal() {
        return this.val;
    }
    public ArrayList<TreeNode> addChildren(TreeNode node) {
        children.add(node);
        return children;
    }

    public TreeNode children(ArrayList<TreeNode> children) {
        this.children = children;
        return this;
    }

    public ArrayList<TreeNode> getChildren() {
        return this.children;
    }
}
