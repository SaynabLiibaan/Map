package handin2.ModelPart.TernarySearchTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TernaryTree implements Serializable {
    private TreeNode root;

    public TernaryTree(){
        root = new TreeNode();
    }

    //inserts a word into our tree
    public void insert(String word){
        if(word != null && !word.isEmpty()){
            insert(word,0,root);
        }
    }

    //we create a new treenode, which can be inserted in our ternary tree
    //example, if ch<node.val then the node's left node will be instantiated to a new node. 
    private TreeNode insert(String word, int index, TreeNode node) {
        char ch = word.charAt(index);
        if (node == null) node = new TreeNode(ch);
        if (ch < node.character) node.left = insert(word, index, node.left);
        else if (ch > node.character) node.right = insert(word, index, node.right);
        else if (index < word.length() - 1) node.mid = insert(word, index + 1, node.mid);
        else node.value = "*";
        return node;
    }

    //returns searchresults
    public List<String> searchResults(String search){

        List<String> searchResults = new ArrayList<>();

        TreeNode nodeWhereWeWillContinueOurSearchFrom = getSubTrie(root, search, 0);
        if(nodeWhereWeWillContinueOurSearchFrom != null){
            collect(nodeWhereWeWillContinueOurSearchFrom.mid, search, 0, search, searchResults);
        }
        return searchResults;
    }


    //helpmethod for getting the subtrie
    private TreeNode getSubTrie(TreeNode node, String prefix, int d){
        if(node == null) return null;
        if(prefix.length()==0) return null;
        char c = prefix.charAt(d);
        if (c < node.character) return getSubTrie(node.left, prefix, d);
        else if (c > node.character) return getSubTrie(node.right, prefix, d);
        else if(d < prefix.length()-1) return getSubTrie(node.mid, prefix, d+1);
        else return node;
    }


    private void collect(TreeNode node, String prefix, int depth,  String search, List<String> searchResults){
        if(node == null){
            return;
        }
        collect(node.left, prefix, depth, search, searchResults);
        collect(node.mid, prefix + node.character, depth + 1, search, searchResults);
        collect(node.right, prefix, depth, search, searchResults);

        if(node.value != null){
            searchResults.add(prefix + node.character);
        }
    }
}