package aud;

import aud.util.Graphvizable;

/** Simple implementation of 2-3-4-trees based on {@link KTreeNode}.
   
   Note that in contrast to {@link BinarySearchTree} ...
   <ul>
   <li>we store only keys rather than key-value pairs.</li>
   <li>there is no dummy node {@code head}, there is no reference to parents.</li>
   </ul>
   
   The recursive {@link #insert} splits all 3-nodes on the way down to the
   insertion position. (This may split the root!) So the final
   {@link KTreeNode#insert} is guaranteed to not cause a split.
   <p>
   
   <em>Note that there is currently no operation for removal. (This would cause
    {@link KTreeNode#mergeChild}.)</em>
 */
public class A234Tree<Key extends Comparable<Key>> implements Graphvizable {

  KTreeNode<Key> root_;

  /** create an empty 2-3-4-tree */
  public A234Tree() {
    root_=new KTreeNode<Key>();
  }

  /** find key in tree     
     @return found key, note that this is generally a <em>different</em>
     instance than {@code key}! (We have "only" {@code compareKeys()==0}.)
    */
  public Key find(Key key) {
    return root_.find(key);
  }

  /** insert entry    
     @return {@code true} if {@code key} was not an entry of child before
   */
  public boolean insert(Key key) {    
    return insert(root_,key);
  }

  /** Split 5-node and merges with parent.
     <ul>
     <li>I'm sort of cheating here: I let a node eventually become a 5-node
     and split only then. Implementation is simpler, but it's not (storage) 
     efficient!</li>
     <li>This may lead to a recursive split up to the root.</li>
     <li>The method has <em>no effect</em> on 2- and 3-nodes.</li>
     <li>{@code split} calls {@link #onSplit} <em>before</em> the split.</li>
     </ul>
   */
  protected void split(KTreeNode<Key> node) {
    if (node.getK()>4) { // CHEATING: we created and split a 5-node!
      
      onSplit(node);

      node.split(2);     

      if (node.parent_!=null) {
        int i=node.parent_.getIndexOfChild(node);        
        assert (i>=0);
        node.parent_.mergeChild(i);
        node.parent_.checkConsistency();

        split(node.parent_); // eventually split parent
      }
    }
  }

  /** callback invoked by {@link #split}, default implementation is empty */
  protected void onSplit(KTreeNode<Key> node) {
  }

  protected boolean insert(KTreeNode<Key> node, Key key) {
    // recursive find/insert (similar to KTreeNode#find)
    for (int i=1; i<node.getK(); ++i) {
      int cmp=node.compareKeys(key,node.getKey(i));
      if (cmp==0)
        return false; // key exists in tree
      else if (cmp<0) {
        if (node.getChild(i-1)==null) {
          
          node.insert(key,i-1);
          split(node);
          return true;
          
        } else
          return insert(node.getChild(i-1),key); // recursive find
      }
    }

    KTreeNode<Key> right=node.getChild(node.getK()-1);
    if (right!=null)
      return insert(right,key);                 // recursive find

    node.insert(key,node.getK()-1);
    split(node);
    
    return true;
  }

  @Override
  public String toDot() {
    return root_.toDot();
  }

  /** get TikZ code for LaTeX export (calls {@link KTreeNode#toTikZ}) */
  public String toTikZ() {
    return root_.toTikZ();
  }

  @Override public String toString() {
    return root_.toString();
  }

  /** few consistency checkschild.entries_.at(i).child 
      @throws RuntimeException on error (and/or assertion fails)
   */
  public void checkConsistency() {
    // missing: check particular properties of 2-3-4-tree!
    root_.checkConsistency();
  }

  /** example and test */
  public static void main(String[] args) {
    A234Tree<String> tree=new A234Tree<String>();

    tree.insert("b");
    tree.checkConsistency();
    tree.insert("c");
    tree.checkConsistency();
    tree.insert("a");
    tree.checkConsistency();
    tree.insert("d");
    tree.checkConsistency();
    tree.insert("e");
    tree.checkConsistency();
    tree.insert("f");
    tree.checkConsistency();
    tree.insert("g");
    tree.checkConsistency();

    aud.util.DotViewer.displayWindow(tree,"234 tree");
    System.out.println(tree.toTikZ());

    // aud.util.DotViewer.displayWindow(tree,"234 tree");
  }
}
