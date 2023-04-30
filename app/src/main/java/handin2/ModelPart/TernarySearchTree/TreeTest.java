package handin2.ModelPart.TernarySearchTree;

public class TreeTest{

    public void test1(){
        TernaryTree tree = new TernaryTree();
        tree.insert("amager");
        tree.insert("amalienborg");
        tree.insert("alberte");
        tree.insert("koge");
        tree.insert("kobenhavn");

        System.out.println(tree.searchResults("ko"));
    }
}