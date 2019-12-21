package com.app.adt.tree;

import com.app.adt.IComparable;
import com.app.adt.IIterator;
import com.app.adt.queue.ArrayQueue;
import com.app.adt.queue.IQueue;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends IComparable<T>> implements ITree<T> {

  private BinaryNode root;
  public BinarySearchTree() {
    root = null;
  }

  public BinarySearchTree(T rootData) {
    root = new BinaryNode(rootData);
  }

  public boolean contains(T entry) {
    return getEntry(entry) != null;
  }
  
  
  public T getEntry(T entry) {
    return findEntry(root, entry);
  }
  
  private T findEntry(BinaryNode rootNode, T entry) {
    T result = null;

    if (rootNode != null) {
      T rootEntry = rootNode.data;

      if (entry.toString().equals(rootEntry.toString())) {
        result = rootEntry;
      } else if (entry.compareTo(rootEntry) < 0) {
        result = findEntry(rootNode.left, entry);
      } else {
        result = findEntry(rootNode.right, entry);
      }
    }
    return result;
  }

  public T add(T newEntry) {
    T result = null;

    if (isEmpty()) {
      root = new BinaryNode(newEntry);
    } else {
      result = addEntry(root, newEntry);
    }

    return result;
  }

  /**
   * Task: Adds newEntry to the nonempty subtree rooted at rootNode.
   */
  private T addEntry(BinaryNode rootNode, T newEntry) {
    T result = null;
    int comparison = newEntry.compareTo(rootNode.data);

    if (comparison == 0) {						// newEntry matches entry in root
      result = rootNode.data;
      rootNode.data = newEntry;
    } else if (comparison < 0) {				// newEntry < entry in root
      if (rootNode.left != null) {
        result = addEntry(rootNode.left, newEntry);
      } else {
        rootNode.left = new BinaryNode(newEntry);
      }
    } else {														// newEntry > entry in root
      if (rootNode.right != null) {
        result = addEntry(rootNode.right, newEntry);
      } else {
        rootNode.right = new BinaryNode(newEntry);
      }
    }
    return result;
  }

  public T remove(T entry) {
    ReturnObject oldEntry = new ReturnObject(null);

    BinaryNode newRoot = removeEntry(root, entry, oldEntry);

    root = newRoot;

    return oldEntry.get();
  }

  /**
   * Task: Removes an entry from the tree rooted at a given node.
   *
   * @param rootNode a reference to the root of a tree
   * @param entry the object to be removed
   * @param oldEntry an object whose data field is null
   * @return the root node of the resulting tree; if entry matches an entry in
   * the tree, oldEntry's data field is the entry that was removed from the
   * tree; otherwise it is null
   */
  private BinaryNode removeEntry(BinaryNode rootNode, T entry, ReturnObject oldEntry) {
    if (rootNode != null) {
      T rootData = rootNode.data;
      int comparison = entry.compareTo(rootData);

      if (comparison == 0) {      // entry == root entry
        oldEntry.set(rootData);
        rootNode = removeFromRoot(rootNode);
        
      } else if (comparison < 0) {  // entry < root entry
        BinaryNode leftChild = rootNode.left;
        BinaryNode subtreeRoot = removeEntry(leftChild, entry, oldEntry);
        rootNode.left = subtreeRoot;
      } else {                      // entry > root entry
        BinaryNode rightChild = rootNode.right;
        rootNode.right = removeEntry(rightChild, entry, oldEntry);

      }
    }

    return rootNode;
  }

  /**
   * Task: Removes the entry in a given root node of a subtree.
   *
   * @param rootNode the root node of the subtree
   * @return the root node of the revised subtree
   */
  private BinaryNode removeFromRoot(BinaryNode rootNode) {
    // Case 1: rootNode has two children
    if (rootNode.left != null && rootNode.right != null) {
      // find node with largest entry in left subtree
      BinaryNode leftSubtreeRoot = rootNode.left;
      BinaryNode largestNode = findLargest(leftSubtreeRoot);

      // replace entry in root
      rootNode.data = largestNode.data;

      // remove node with largest entry in left subtree
      rootNode.left = removeLargest(leftSubtreeRoot);
    } // end if
    // Case 2: rootNode has at most one child
    else if (rootNode.right != null) {
      rootNode = rootNode.right;
    } else {
      rootNode = rootNode.left;
    }

	  // Assertion: if rootNode was a leaf, it is now null
    return rootNode;
  }

  /**
   * Task: Finds the node containing the largest entry in a given tree.
   *
   * @param rootNode the root node of the tree
   * @return the node containing the largest entry in the tree
   */
  private BinaryNode findLargest(BinaryNode rootNode) {
    if (rootNode.right != null) {
      rootNode = findLargest(rootNode.right);
    }

    return rootNode;
  }
  

  /**
   * Task: Removes the node containing the largest entry in a given tree.
   *
   * @param rootNode the root node of the tree
   * @return the root node of the revised tree
   */
  private BinaryNode removeLargest(BinaryNode rootNode) {
    if (rootNode.right != null) {
      BinaryNode rightChild = rootNode.right;
      BinaryNode root = removeLargest(rightChild);
      rootNode.right = root;
    } else {
      rootNode = rootNode.left;
    }

    return rootNode;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public void clear() {
    root = null;
  }

  public IIterator<T> getInorderIterator() {
    return new InorderIterator();
  }

  public IIterator<T> getPreorderIterator() {
    return new PreorderIterator();
  }

  public IIterator<T> getPostorderIterator() {
    return new PostorderIterator();
  }

  // ReturnObject is the type for method removeEntry's 3rd parameter, oldEntry,
  // which is used for returning the removed entry
  private class ReturnObject {

    private T item;

    private ReturnObject(T entry) {
      item = entry;
    }

    public T get() {
      return item;
    }

    public void set(T entry) {
      item = entry;
    }
  }

  public class BinaryNode {

    public T data;
    public BinaryNode left;
    public BinaryNode right;

    private BinaryNode() {
      this(null);
    }

    private BinaryNode(T dataPortion) {
      this(dataPortion, null, null);
    }

    private BinaryNode(T data, BinaryNode left, BinaryNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    private boolean isLeaf() {
      return (left == null) && (right == null);
    }
    


  }
  //Inorder
  private class InorderIterator implements IIterator<T> {

    private IQueue<T> queue = new ArrayQueue<T>();

    public InorderIterator() {
      inorder(root);
    }

    private void inorder(BinaryNode treeNode) {
      if (treeNode != null) {
        inorder(treeNode.left);
        queue.add(treeNode.data);
        inorder(treeNode.right);
      }
    }

    public boolean hasNext() {
      return !queue.isEmpty();
    }

    public T next() {
      if (!queue.isEmpty()) {
        return queue.remove();
      } else {
        throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
    

  }
  
  //Preorder
  private class PreorderIterator implements IIterator<T> {

    private IQueue<T> queue = new ArrayQueue<T>();

    public PreorderIterator() {
      preorder(root);
    }

    private void preorder(BinaryNode treeNode) {
      if (treeNode != null) {
        queue.add(treeNode.data);
        preorder(treeNode.left);
        preorder(treeNode.right);
      }
    }

    public boolean hasNext() {
      return !queue.isEmpty();
    }

    public T next() {
      if (!queue.isEmpty()) {
        return queue.remove();
      } else {
        throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
    

  }
  
  //Postorder
  private class PostorderIterator implements IIterator<T> {

    private IQueue<T> queue = new ArrayQueue<T>();

    public PostorderIterator() {
      postorder(root);
    }

    private void postorder(BinaryNode treeNode) {
      if (treeNode != null) {
        postorder(treeNode.left);
        postorder(treeNode.right);
        queue.add(treeNode.data);
      }
    }

    public boolean hasNext() {
      return !queue.isEmpty();
    }

    public T next() {
      if (!queue.isEmpty()) {
        return queue.remove();
      } else {
        throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
    

  }

}
