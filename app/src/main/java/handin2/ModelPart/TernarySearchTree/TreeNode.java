package handin2.ModelPart.TernarySearchTree;

import java.io.Serializable;

public class TreeNode implements Serializable{
    char character;
    TreeNode left, mid, right;
    String value; // can be turned into an address object later
    //char end = '1';

    

    public TreeNode(){
      //do nothing
    }

    public TreeNode(char character){
        this.character = character;
       
    }
}


