package edu.cofc.csci230;


/**
 * Binary search that does not allow two (or more) binary nodes in the search tree to have the same
 * element value.
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class BinarySearchTree<AnyType extends Comparable<AnyType>> {

  // --------------------------------------
  // instance variable
  private BinaryNode<AnyType> root;

  /**
   * Constructor with one parameter that sets the root node of the BST.
   */
  public BinarySearchTree(AnyType rootElement) throws NullBinaryNodeException {

    if (rootElement == null) {
      throw new NullBinaryNodeException();
    }

    this.root = new BinaryNode<>(rootElement);

  } // end constructor

  /**
   * If the BST does not have a root node, then it is empty.
   */
  public boolean isEmpty() {

    return (this.root == null);

  } // end isEmpty() method

  /**
   * Make the BST empty, i.e. a BST that has no nodes (including a root node).
   */
  public void clear() {

    this.root = null;

  } // end clear() method

  /**
   * Method that inserts a new node with the specified element value in the BST. This method has one
   * parameter: 1) The element value to be added If the BST has an existing node with the same
   * element value, throw an duplicate element exception.
   */
  public void insert(AnyType element) throws NullBinaryNodeException, DuplicateElementException {

    if (element == null) {
      throw new NullBinaryNodeException();
    }

    if (root == null) {
      this.root = new BinaryNode<>(element);
    } else {
      this.insert(root, element);
    }

  } // end insert() method

  /**
   * Private recursive method that inserts a new node (with the specified element value) in the BST.
   * This method has two parameters: 1) The node visited while recursively walking the BST 2) The
   * element value to be added If the BST has an existing node with the same element value, throw an
   * duplicate element exception.
   */
  private void insert(BinaryNode<AnyType> node, AnyType element) throws DuplicateElementException {

    /**
     * ------------------------------------
     * DONE: You complete the code.
     *
     * Note: Your solution MUST USE recursion
     *
     */
    if (node.getElement() == element) {
      throw new DuplicateElementException();
    }

    // Places child in right subtree
    if (element.compareTo(node.getElement()) > 0) {

      if (node.getRight() != null) {
        this.insert(node.getRight(), element);

      } else {
        BinaryNode<AnyType> child = new BinaryNode<AnyType>(element);
        child.setParent(node);
        node.setRight(child);
      }
    }// Places child in left subtree
    else {

      if (node.getLeft() != null) {
        this.insert(node.getLeft(), element);

      } else {
        BinaryNode<AnyType> child = new BinaryNode<AnyType>(element);
        child.setParent(node);
        node.setLeft(child);
      }
    }
  } // end insert() overloaded method

  /**
   * Method that determines if a node with the specified element value exists in the BST.
   *
   * This method has one parameter: 1) The element value that is being searched.
   *
   * If the BST is empty, throw an empty binary search tree exception.
   */
  public boolean search(AnyType element) throws NullBinaryNodeException, EmptyBSTException {

    if (element == null) {
      throw new NullBinaryNodeException();
    }
    if (this.isEmpty()) {
      throw new EmptyBSTException();
    }

    return search(this.root, element);

  } // end search() method

  /**
   * Private recursive method that determines if the element is currently stored in the BST.
   *
   * This method has two parameters: 1) The node visited while recursively walking the BST 2) The
   * element value that is being searched.
   *
   * Hint: use the compareTo() method
   */
  private boolean search(BinaryNode<AnyType> node, AnyType element) {

    /**
     * ------------------------------------
     * DONE: You complete the code.
     *
     * Note: Your solution MUST USE recursion
     *
     */

    if (node == null) {
      return false;
    }

    if (element.compareTo(node.getElement()) == 0) {
      //Test
      //System.out.printf("Search Element = %d, Node Element = %d\n", element, node.getElement());
      return true;
    }
    else if (element.compareTo(node.getElement()) < 0) {
      return this.search(node.getLeft(), element);

    }
    else {
      return this.search(node.getRight(), element);
    }

  } // end search() overloaded method

  /**
   * Find the node with the minimum element value in the BST.
   *
   * This method has no parameters.
   *
   * If the BST is empty, throw an empty binary search tree exception.
   */
  public AnyType min() throws EmptyBSTException {

    if (isEmpty()) {
      throw new EmptyBSTException();
    }

    return this.min(this.root).getElement();

  } // end findMin() method

  /**
   * Private recursive method that walks the BST searching for the node with the minimum element
   * value.
   *
   * This method has one parameter: 1) The node visited while recursively walking the BST
   */
  private BinaryNode<AnyType> min(BinaryNode<AnyType> node) {

    /**
     * ------------------------------------
     * DONE: You complete the code.
     *
     * Note: Your solution MUST USE recursion
     *
     */
    if (node.getLeft() == null) {
      return node;
    } else {
      return this.min(node.getLeft());
    }

  } // end min() method

  /**
   * Find the node with the maximum element value in the BST.
   *
   * This method has no parameters.
   *
   * If the BST is empty, throw an empty binary search tree exception.
   */
  public AnyType max() throws EmptyBSTException {

    if (isEmpty()) {
      throw new EmptyBSTException();
    }

    return this.max(root).getElement();

  } // end max() method

  /**
   * Private recursive method that walks the BST searching for the node with the maximum element
   * value.
   *
   * This method has one parameter: 1) The node visited while recursively walking the BST
   */
  private BinaryNode<AnyType> max(BinaryNode<AnyType> node) {

    /**
     * ------------------------------------
     * DONE: You complete the code.
     *
     * Note: Your solution must use recursion
     *
     */

    if (node.getRight() == null) {
      return node;
    } else {
      return this.max(node.getRight());
    }
  } // end max() method

  /**
   * Delete the node with the specified element value in the BST.
   *
   * This method has one parameter: 1) the element value to be deleted
   *
   * This method performs the following delete operations 1) delete node with no children (case 1)
   * 2) delete node with one child (case 2) 3) delete node with two children (case 3)
   *
   * If the BST is empty, throw an empty binary search tree exception.
   *
   * If the element is null, throw a null binary node exception
   */
  public AnyType delete(AnyType element) throws EmptyBSTException, NullBinaryNodeException {

    if (element == null) {
      throw new NullBinaryNodeException();
    }
    if (isEmpty()) {
      throw new EmptyBSTException();
    }

    return delete(this.root, element).getElement();

  } // end delete() method


  /**
   * Private recursive method that walk the BST looking for the specified element value to be
   * removed.
   *
   * This method has two parameters: 1) The node visited while recursively walking the BST 2) The
   * element value that is being removed.
   */
  private BinaryNode<AnyType> delete(BinaryNode<AnyType> node, AnyType element) {

    /**
     * ------------------------------------
     * DONE: You complete the code.
     *
     * Note: Your solution MUST USE recursion
     *
     */

    // Ensures the element is in the BST before attempting to delete
    if (node.getParent() == null && !search(root, element)) {
      throw new NullPointerException();
    }

    if (node == null) {
      return node;
    }

    // Searches for the element
    if (element.compareTo(node.getElement()) < 0) {
      node.setLeft(delete(node.getLeft(), element));

      // Updates the parent field of the BinaryNode object
      if (node.getLeft() != null) {
        node.getLeft().setParent(node);
      }
    } else if (element.compareTo(node.getElement()) > 0) {
      node.setRight(delete(node.getRight(), element));

      // Updates the parent field of the BinaryNode object
      if (node.getRight() != null) {
        node.getRight().setParent(node);
      }
    } // Finds the element
    else {
      // This covers leaf Case
      if (node.getLeft() == null && node.getRight() == null) {
        return null;
      } // Only has a right child Case
      else if (node.getLeft() == null && node.getRight() != null) {
        return node.getRight();
      } // Only has a left child Case
      else if (node.getRight() == null && node.getLeft() != null) {
        return node.getLeft();
      } // Has 2 children Case
      else {
        // Finds the max in the left subtree that will become the new root
        BinaryNode<AnyType> temp = max(node.getLeft());
        node.setElement(temp.getElement());
        node.setLeft(delete(node.getLeft(), temp.getElement()));

        // Updates the parent field of the BinaryNode object
        if (node.getLeft() != null) {
          node.getLeft().setParent(node);
        }
      }
    }

    return node;

  } // end delete() method


  /**
   * Recursive method that traverses the BST dynamically creating a string with the element values
   * stored in an pre-order tree traversal format.
   *
   * The return string MUST be formated as follows:
   * <element>,<element>,<element>,<element>, .... <element>
   * where <element> is the element value For example, 2,1,3 Hint: you may want to use regular
   * expressions
   *
   * Discussed in class, please review your notes
   *
   * If the BST is empty, throw an empty binary search tree exception
   */
  public String preOrder() throws EmptyBSTException {

    if (isEmpty()) {
      throw new EmptyBSTException();
    }

    return this.preOrder(root);

  } // end preOrder() method

  /**
   * Private recursive method that traverses the BST dynamically creating a string with the element
   * values stored in an pre-order tree traversal format.
   *
   * This method has one parameter: 1) The node visited while recursively walking the BST
   */
  private String preOrder(BinaryNode<AnyType> node) {

    /**
     * ------------------------------------
     * DONE: You complete the code.
     *
     * Note: Your solution MUST USE recursion
     *
     */

    String root = node.toString();
    String left = "";
    String right = "";

    if (node.getLeft() != null) {
      left = "," + preOrder(node.getLeft());
    }

    if (node.getRight() != null) {
      right = "," + preOrder(node.getRight());
    }

    return root + left + right;
  } // end preOrder() method


  /**
   * You may modify this (no restrictions) to help debug your code.
   */
  public String toString(BinaryNode<AnyType> node) {

    return node.getElement().toString();

  } // end toString() method


  /**
   * You may modify this (no restrictions) to help debug your code.
   */
  public void printBST() {
//    BSTPrinter.printNode(this.root);

  } // end printBST() method


  /**
   * Main.
   */
  public static void main(String[] args)  {

    /**
     * ------------------------------------
     * DONE: You put your test cases here.
     *
     *
     */

    // this will get you started :)

    BinarySearchTree<Integer> bst = null;
    BinarySearchTree<Integer> rightLinear = null;
    BinarySearchTree<Integer> leftLinear = null;

    try {

      bst = new BinarySearchTree<Integer>(10);
      rightLinear = new BinarySearchTree<Integer>(1);
      leftLinear = new BinarySearchTree<Integer>(1);

    } catch (NullBinaryNodeException error) {
      System.out.println(error);
    }

    //Case Build Tree
    try {
      bst.insert(-10);
      bst.insert(-5);
      bst.insert(-15);
      bst.insert(0);
      bst.insert(-7);
      bst.insert(-3);
      bst.insert(15);
      bst.insert(12);
      bst.insert(17);
      bst.insert(20);

      for (int i = 2; i < 5; i++) {
        rightLinear.insert(i);
      }

      for (int i = 0; i > -3; i--) {
        leftLinear.insert(i);
      }

      System.out.println("Insertion: SAT");
    } catch (DuplicateElementException dee) {
      System.out.println("Duplicate!");
      System.out.println("Insertion: UNSAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println("Insertion: UNSAT");
      System.out.println("Null Value");
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
//      rightLinear.printBST();
      System.out.println("==============================================");
////      leftLinear.printBST();
      System.out.println("==============================================");
    }

    //Insert Duplicate Case
    try {
      bst.insert(12);
      System.out.println("Insert Duplicate Case: UNSAT");
    } catch (DuplicateElementException dee) {
      System.out.println("Insert Duplicate Case: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println(nbne.getMessage());
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
    }
    //Case Max on Empty
    try {
      BinarySearchTree<Integer> empty = new BinarySearchTree<Integer>(24);
      empty.clear();
      empty.max();
      System.out.println("Max on Empty: UNSAT");

    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (EmptyBSTException ebe1) {
      System.out.println("Max on Empty: SAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Min on Empty
    try {
      BinarySearchTree<Integer> empty = new BinarySearchTree<Integer>(24);
      empty.clear();
      empty.min();
      System.out.println("Min on Empty: UNSAT");

    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (EmptyBSTException ebe2) {
      System.out.println("Min on Empty: SAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Max
    Integer result1 = null;

    try {
      result1 = bst.max();

      if (result1 != 20) {
        throw new Exception();
      }
      System.out.printf("Max: 20=%d: SAT\n", result1);

    } catch (Exception e) {
      System.out.printf("Max: 20!=%d: UNSAT\n", result1);
    } finally {
      System.out.println("==============================================");
    }

    //Case Max left Linear BST
    try {
      result1 = leftLinear.max();

      if (result1 != 1) {
        throw new Exception();
      }
      System.out.printf("Max Left Linear: 1=%d: SAT\n", result1);

    } catch (Exception e) {
      System.out.printf("Max Left Linear: 1!=%d: UNSAT\n", result1);
    } finally {
      System.out.println("==============================================");
    }

    //Case Max Right Linear BST
    try {
      result1 = rightLinear.max();

      if (result1 != 4) {
        throw new Exception();
      }
      System.out.printf("Max Right Linear: 4=%d: SAT\n", result1);

    } catch (Exception e) {
      System.out.printf("Max Right Linear: 4!=%d: UNSAT\n", result1);
    } finally {
      System.out.println("==============================================");
    }

    //Case Min
    try {
      result1 = bst.min();

      if (result1 != -15) {
        throw new Exception();
      }
      System.out.printf("Min: -15=%d: SAT\n", result1);

    } catch (Exception e) {
      System.out.printf("Min: -15!=%d: UNSAT\n", result1);
    } finally {
      System.out.println("==============================================");
    }

    //Case Min Left Linear BST
    try {
      result1 = leftLinear.min();

      if (result1 != -2) {
        throw new Exception();
      }
      System.out.printf("Min Left Linear: -2=%d: SAT\n", result1);

    } catch (Exception e) {
      System.out.printf("Min Left Linear: -2!=%d: UNSAT\n", result1);
    } finally {
      System.out.println("==============================================");
    }

    //Case Min Right Linear BST
    try {
      result1 = rightLinear.min();

      if (result1 != 1) {
        throw new Exception();
      }
      System.out.printf("Min Right Linear: 1=%d: SAT\n", result1);

    } catch (Exception e) {
      System.out.printf("Min Right Linear: 1!=%d: UNSAT\n", result1);
    } finally {
      System.out.println("==============================================");
    }

    //Case Min and Max on Single Node
    try {
      BinarySearchTree<Integer> single = new BinarySearchTree<Integer>(24);
      if (single.max() != single.min()) {
        throw new Exception();
      }
      System.out.println("Max and Min on Single Node Tree: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (Exception e) {
      System.out.println("Max and Min on Single Node Tree: UNSAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Search on Empty
    try {
      BinarySearchTree<Integer> empty = new BinarySearchTree<Integer>(24);
      empty.clear();
      empty.search(24);
      System.out.println("Search on Empty: UNSAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (EmptyBSTException e) {
      System.out.println("Search on Empty: SAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Search on Single True
    try {
      BinarySearchTree<Integer> single = new BinarySearchTree<Integer>(24);
      if (!single.search(24)) {
        throw new Exception();
      }
      System.out.println("Search on Single True: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (EmptyBSTException ebe) {
      System.out.println("EmptyBSTException");
    } catch (Exception e) {
      System.out.println("Search on Single True: SAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Search on Single False
    try {
      BinarySearchTree<Integer> single = new BinarySearchTree<Integer>(24);
      if (single.search(0)) {
        throw new Exception();
      }
      System.out.println("Search on Single False: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (EmptyBSTException ebe) {
      System.out.println("EmptyBSTException");
    } catch (Exception e) {
      System.out.println("Search on Single False: SAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Search True Right Subtree
    boolean status = false;
    try {
      status = bst.search(12);
      if (!status) {
        throw new Exception();
      }
      System.out.printf("Search Right Subtree true=%b: SAT\n", status);
    } catch (Exception e) {
      System.out.printf("Search Right Subtree true!=%b: UNSAT\n", status);
    } finally {
      System.out.println("==============================================");
    }

    //Case Search True Left Subtree
    status = false;
    try {
      status = bst.search(-7);
      if (!status) {
        throw new Exception();
      }
      System.out.printf("Search Left Subtree true=%b: SAT\n", status);
    } catch (Exception e) {
      System.out.printf("Search Left Subtree true!=%b: UNSAT\n", status);
    } finally {
      System.out.println("==============================================");
    }

    //Case Search False Right Subtree
    status = true;
    try {
      status = bst.search(5000);
      if (status) {
        throw new Exception();
      }
      System.out.printf("Search Right Subtree false=%b: SAT\n", status);
    } catch (Exception e) {
      System.out.printf("Search Right Subtree false!=%b: UNSAT\n", status);
    } finally {
      System.out.println("==============================================");
    }

    //Case Search False Left Subtree
    status = true;
    try {
      status = bst.search(-5000);
      if (status) {
        throw new Exception();
      }
      System.out.printf("Search Left Subtree false=%b: SAT\n", status);
    } catch (Exception e) {
      System.out.printf("Search Left Subtree false!=%b: UNSAT\n", status);
    } finally {
      System.out.println("==============================================");
    }

    //Case bst preOrder
    try {
      String preorder = bst.preOrder();
      System.out.println(preorder);
      if (!preorder.equals("10,-10,-15,-5,-7,0,-3,15,12,17,20")) {
        throw new Exception();
      }

      System.out.println("Preorder: SAT");
    } catch (EmptyBSTException ebe) {
      System.out.println("EmptyBSTException");
    } catch (Exception e) {
      System.out.println("Preorder: UNSAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Left Linear preOrder
    try {
      String preorder = leftLinear.preOrder();
      System.out.println(preorder);
      if (!preorder.equals("1,0,-1,-2")) {
        throw new Exception();
      }

      System.out.println("Preorder Left Linear: SAT");
    } catch (EmptyBSTException ebe) {
      System.out.println("EmptyBSTException");
    } catch (Exception e) {
      System.out.println("Preorder Left Linear: UNSAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Right Linear preOrder
    try {
      String preorder = rightLinear.preOrder();
      System.out.println(preorder);
      if (!preorder.equals("1,2,3,4")) {
        throw new Exception();
      }

      System.out.println("Preorder Right Linear: SAT");
    } catch (EmptyBSTException ebe) {
      System.out.println("EmptyBSTException");
    } catch (Exception e) {
      System.out.println("Preorder Right Linear: UNSAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case bst Empty preOrder
    try {
      BinarySearchTree<Integer> empty = new BinarySearchTree<Integer>(24);
      empty.clear();
      String preorder = empty.preOrder();
      System.out.println(preorder);

      System.out.println("Empty Preorder: UNSAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println("NullBinaryNodeException");
    } catch (EmptyBSTException ebe) {
      System.out.println("Empty Preorder: SAT");
    } finally {
      System.out.println("==============================================");
    }

    //Case Delete element not in BST
    try {
      bst.delete(-1000);
      System.out.println("Delete Element Not Found Case: UNSAT");
    } catch (NullPointerException npe) {
      System.out.println("Delete Element Not Found Case: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println(nbne.getMessage());
    } catch (EmptyBSTException ebe) {
      System.out.println(ebe.getMessage());
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
    }

    //Case Root/Node with 2 children
    try {
      bst.delete(10);

      if (bst.root.getElement() != 0) {
        throw new Exception();
      }
      System.out.println(bst.preOrder());
      System.out.println("Delete Root/Node with 2 Kids Case: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println(nbne.getMessage());
    } catch (EmptyBSTException ebe) {
      System.out.println(ebe.getMessage());
    } catch (Exception e) {
      System.out.println("Delete Root/Node with 2 Kids Case: UNSAT");
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
    }

    //Case Delete leaf
    try {
      bst.delete(20);

      if (bst.search(20)) {
        throw new Exception();
      }
      System.out.println(bst.preOrder());
      System.out.println("Delete Leaf Case: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println(nbne.getMessage());
    } catch (EmptyBSTException ebe) {
      System.out.println(ebe.getMessage());
    } catch (Exception e) {
      System.out.println("Delete Leaf Case: UNSAT");
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
    }

    //Case Delete Node with 1 Child on Right
    try {
      bst.delete(12);
      bst.delete(15);

      if (bst.search(15)) {
        throw new Exception();
      }
      System.out.println(bst.preOrder());
      System.out.println("Delete Node with 1 Child on Right Case: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println(nbne.getMessage());
    } catch (EmptyBSTException ebe) {
      System.out.println(ebe.getMessage());
    } catch (Exception e) {
      System.out.println("Delete Node with 1 Child on Right Case: UNSAT");
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
    }

    //Case Delete Node with 1 Child on Left
    try {
      bst.delete(17);
      bst.delete(-10);

      if (bst.search(-10)) {
        throw new Exception();
      }
      System.out.println(bst.preOrder());
      System.out.println("Delete Node with 1 Child on Left Case: SAT");
    } catch (NullBinaryNodeException nbne) {
      System.out.println(nbne.getMessage());
    } catch (EmptyBSTException ebe) {
      System.out.println(ebe.getMessage());
    } catch (Exception e) {
      System.out.println("Delete Node with 1 Child on Left Case: UNSAT");
    } finally {
//      bst.printBST();
      System.out.println("==============================================");
    }

  } // end main method
} // end BinarySearchTree class
