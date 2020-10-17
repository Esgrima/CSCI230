package edu.cofc.csci230;



/**
 * Doubly Linked List Data Structure
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class DoublyLinkedList<AnyType extends Comparable<AnyType>> implements List<AnyType> {

  // instance variables
  private Node<AnyType> headNode = null;
  private Node<AnyType> tailNode = null;

  private int size = 0;

  /**
   * Appends the specified element to the end of this list.
   */
  public boolean add(AnyType t) throws NullPointerException {

    if (t == null) {
      throw new NullPointerException();
    }

    this.addNode(new Node<>(t));

    return false;
  } // end add() method

  /**
   * Implementation for public add(AnyType t) method
   *
   * I did this one for you :)
   */
  private void addNode(Node<AnyType> t) {

    if (isEmpty()) {

      this.headNode = t;
      this.tailNode = this.headNode;

    } else {

      Node<AnyType> node = this.getNode(size - 1);
      node.setNextNode(t);
      t.setPreviousNode(node);

      this.tailNode = t;

    }

    this.size++;

  } // end addNode() method


  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public void add(int index, AnyType t) throws IndexOutOfBoundsException, NullPointerException {

    if (t == null) {
      throw new NullPointerException();
    }

    this.addNode(index, new Node<>(t));

  } // end add() method

  /**
   *
   * Implementation for public add(int index, AnyType t) method
   *
   */
  private void addNode(int index, Node<AnyType> t) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    Node<AnyType> curr;

    if (index == this.size) {
      curr = this.tailNode;
    } else {
      curr = this.getNode(index);
    }

    // Insert before head
    if (index == 0) {
      t.setNextNode(this.headNode);
      this.headNode.setPreviousNode(t);
      this.headNode = t;

    } // Insert after tail(Append)
    else if (index == this.size) {
      this.addNode(t);

    } // Insert in between head and tail
    else {
      Node<AnyType> prev = curr.getPreviousNode();

      // Reconfigures Node pointers prev <-> new Node <-> curr
      t.setPreviousNode(prev);
      prev.setNextNode(t);
      t.setNextNode(curr);
      curr.setPreviousNode(t);
    }
    this.size++;
  } // end addNode() method

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public AnyType set(int index, AnyType t) throws IndexOutOfBoundsException, NullPointerException {

    if (t == null) {
      throw new NullPointerException();
    }

    this.setNode(index, new Node<>(t));

    return null;
  } // end set() method

  /**
   *
   * @param index
   * @param t
   * @throws IndexOutOfBoundsException
   */
  private void setNode(int index, Node<AnyType> t) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    // Stores current, previous, and next nodes
    Node<AnyType> curr = this.getNode(index);
    curr.setData(t.getData());

  } // end setNode() method


  /**
   * Removes the element at the specified position in this list.
   */
  public AnyType remove(int index) throws IndexOutOfBoundsException {

    return this.removeNode(index).getData();

  } // end remove() method

  /**
   *
   * @param index
   * @return
   * @throws IndexOutOfBoundsException
   */
  private Node<AnyType> removeNode(int index) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    Node<AnyType> node = getNode(index);

    // Remove head
    if (index == 0) {
      this.headNode = node.getNextNode();
      if (this.headNode != null) {
        this.headNode.setPreviousNode(null);
      }
    } // Remove tail
    else if (index == this.size - 1) {
      Node<AnyType> prev = node.getPreviousNode();
      prev.setNextNode(null);
      this.tailNode = prev;

    } // Remove body node
    else {
      Node<AnyType> prev = node.getPreviousNode();
      Node<AnyType> next = node.getNextNode();

      // Reconfigure next and previous pointers
      next.setPreviousNode(prev);
      prev.setNextNode(next);
    }

    node.setNextNode(null);
    node.setPreviousNode(null);

    size--;

    if (this.isEmpty()) {
      this.clear();
    }

    return node;

  } // end removeNode() method

  /**
   * Returns the element at the specified position in this list.
   */
  public AnyType get(int index) throws IndexOutOfBoundsException {

    return getNode(index).getData();

  } // end get() method

  /**
   * Implementation of get(int index) method
   */
  private Node<AnyType> getNode(int index) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Your implementation MUST do the following link traversals:
     *
     * 1) If the index location is <= floor( size/2 ), start traversal from head node
     * 2) if the index location is > floor( size/2), start traversal from tail node
     *
     * Your code will be reviewed by instructor to ensure the two conditions
     * are fully meet by your solution.
     *
     */
    // Checks for an invalid index
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException();
    }

    Node<AnyType> curr = null;

    if (this.isEmpty()) {
      return curr;
    }

    // Checks if desired node is head or tail
    if (index == 0) {
      return this.headNode;
    } else if (index == this.size - 1) {
      return this.tailNode;
    }

    double midpoint = Math.floor(this.size / 2.0);

    // Determines where to start traversal
    if (index <= midpoint) {
      curr = this.headNode;

      for (int i = 0; i != index; i++) {
        curr = curr.getNextNode();
      }
      return curr;

    } else if (index > midpoint) {
      curr = this.tailNode;

      for (int i = this.size - 1; i != index; i--) {
        curr = curr.getPreviousNode();
      }
      return curr;
    }

    return curr;
  } // end get() method

  /**
   * Returns the number of elements in this list.
   */
  public int size() {

    return this.size;

  } // end size() method

  /**
   * Returns true if this list contains no elements.
   */
  public boolean isEmpty() {

    return this.size == 0;

  } // end isEmpty() method


  /**
   * Removes all of the elements from this list.
   */
  public void clear() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    this.headNode = null;
    this.tailNode = null;

    this.size = 0;

  } // end clear method

  /**
   * swaps to elements in a list at index position i and j.
   *
   * For example, if A = 1->2->3->4->5 and swap( 1, 3 ) is executed then A = 1->4->3->2->5
   */
  public void swap(int i, int j) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    AnyType temp = this.get(i);
    this.getNode(i).setData(this.get(j));
    this.getNode(j).setData(temp);
  } // end swap() method

  /**
   * Do not modify
   *
   * To help you debug your code
   */
  public void printList() {

    Node<AnyType> n = headNode;

    while (n != null) {

      System.out.println(n);

      n = n.getNextNode();

    }

  } // end printlist()

  /**
   * For debugging and testing purpose
   *
   * !!! Do not remove or modify !!!
   */
  public String toString() {

    StringBuilder buffer = new StringBuilder();

    buffer.append(String.format("Size=%d, A = [ ", size));

    if (!isEmpty()) {

      for (int i = 0; i < size - 1; i++) {
        buffer.append(String.format("%d, ", get(i)));
      }

      buffer.append(String.format("%d ]", get(size - 1)));

    } else {

      buffer.append("] ");
    }

    return buffer.toString();

  } // end toString()


  /**
   *
   * @param args
   */
  public static void main(String[] args) {

    // -------------------------------------
    // Put your test cases here
    // -------------------------------------

    List<Integer> list = new DoublyLinkedList<>();
//    ArrayList<Integer> test = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      list.add(i);
    }
    System.out.println(list);

    //Case Swap head and tail
    try {
      list.swap(0, list.size() - 1);
      assert list.get(0) == 4;
      assert list.get(4) == 0;

      System.out.println("Case Swap head and tail: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap head and tail: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("==============================================");
    }

    //Case Swap head and body
    try {
      list.swap(0, 3);
      if (list.get(3) != 4) {
        throw new Exception();
      }
      if (list.get(0) != 3) {
        throw new Exception();
      }

      System.out.println("Case Swap head and body: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap head and body: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("==============================================");
    }

    //Case Swap tail and body
    try {
      list.swap(4, 1);
      if (list.get(4) != 1) {
        throw new Exception();
      }
      if (list.get(1) != 0) {
        throw new Exception();
      }

      System.out.println("Case Swap tail and body: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap tail and body: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("==============================================");
    }

    //Case Swap body and body
    try {
      list.swap(3, 1);
      if (list.get(3) != 0) {
        throw new Exception();
      }
      if (list.get(1) != 4) {
        throw new Exception();
      }

      System.out.println("Case Swap body and body: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap body and body: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("==============================================");
    }

    //Case Swap below zero
    try {
      list.swap(-1, 3);

      System.out.println("Case Swap Below zero: UNSAT");
    } catch (Exception e) {
      System.out.println("Case Swap Below zero: SAT");
    } finally {
      System.out.println(list);
      System.out.println("==============================================");
    }

    //Case Swap above size
    try {
      list.swap(3, 10);

      System.out.println("Case Swap Above Size: UNSAT");
    } catch (Exception e) {
      System.out.println("Case Swap Above Size: SAT");
    } finally {
      System.out.println(list);
      System.out.println("==============================================");
    }

    //Sort test cases
    list.clear();
    list.add(-1);
    list.add(100);
    list.add(20);
    list.add(13);
    list.add(20);

    System.out.println(list);

    try {
      Utils.ascending = true; // sort in ascending order
      Utils.selectionSort(list);
      System.out.println("Selection Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Selection Sort Ascending: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("=============================================");
    }

    try {
      Utils.ascending = false; // sort in descending order
      Utils.selectionSort(list);
      System.out.println("Selection Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Selection Sort Ascending: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("=============================================");
    }

    try {
      Utils.ascending = true; // sort in ascending order
      Utils.bubbleSort(list);
      System.out.println("Bubble Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Bubble Sort Ascending: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("=============================================");
    }

    try {
      Utils.ascending = false; // sort in descending order
      Utils.bubbleSort(list);
      System.out.println("Bubble Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Bubble Sort Ascending: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("=============================================");
    }

    try {
      Utils.ascending = true; // sort in ascending order
      Utils.insertionSort(list);
      System.out.println("Insertion Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Insertion Sort Ascending: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("=============================================");
    }

    try {
      Utils.ascending = false; // sort in descending order
      Utils.insertionSort(list);
      System.out.println("Insertion Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Insertion Sort Ascending: UNSAT");
    } finally {
      System.out.println(list);
      System.out.println("=============================================");
    }

// ////////////////////////Original test cases////////////////////////////
//    // Case Add at head through tail
//    try {
//      // Add with index only test
//      for (int i = 0; i < 7; i++) {
//        if (i < 4) {
//          list.add(i);
//          test.add(i);
//        } else {
//          list.add(i, i);
//          test.add(i, i);
//        }
//      }
//      System.out.println("Add test SAT");
//
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Add test UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Add Below 0
//    try {
//      list.add(-1, -1);
//      test.add(-1, -1);
//      System.out.println("Add Below Zero Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Add Below Zero Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Add Above Size
//    try {
//      list.add(100, 100);
//      test.add(100, 100);
//      System.out.println("Add Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Add Above Size Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove head
//    try {
//      assert list.remove(0) == 0;
//      list.remove(0);
//      test.remove(0);
//      System.out.println("Remove Head Test SAT");
//
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove Head Test UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Remove in middle
//    try {
//      assert list.remove(3) == 4;
//      list.remove(3);
//      test.remove(3);
//      System.out.println("Remove in middle Test SAT");
//
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove in middle Test UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove Tail
//    try {
//      assert list.remove(4) == 6;
//      list.remove(4);
//      test.remove(4);
//      System.out.println("Remove Tail Test SAT");
//
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove Tail Test UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove Below 0
//    try {
//      list.remove(-1);
//      System.out.println("Remove Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove Below 0 Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove Above Size
//    try {
//      list.remove(10);
//      System.out.println("Remove Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove Above Size Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get Head
//    try {
//      list.get(0);
//      test.get(0);
//      assert list.get(0) == test.get(0);
//      System.out.printf("Get head(0:%d) Test: SAT\n", list.get(0));
//    } catch (IndexOutOfBoundsException e) {
//      System.out.printf("Get head(0:%d) Test: UNSAT\n", list.get(0));
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get Tail
//    try {
//      list.get(3);
//      test.get(3);
//      System.out.printf("Get tail(3:%d) Test: SAT\n", list.get(3));
//    } catch (IndexOutOfBoundsException e) {
//      System.out.printf("Get tail(3:%d) Test: UNSAT\n", list.get(3));
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get below 0
//    try {
//      list.get(-5);
//      test.get(-5);
//      System.out.println("Get Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Get Below 0 Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get Above 0
//    try {
//      list.get(10);
//      test.get(10);
//      System.out.println("Get Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Get Below 0 Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get in middle
//    try {
//      list.get(2);
//      test.get(2);
//      System.out.printf("Get middle(2:%d) Test: SAT\n", list.get(2));
//    } catch (IndexOutOfBoundsException e) {
//      System.out.printf("Get middle(2:%d) Test: UNSAT\n", list.get(2));
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Set Head
//    try {
//      list.set(0, 0);
//      test.set(0, 0);
//      System.out.println("Set head Test: SAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set head Test: UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Set Tail
//    try {
//      list.set(3, 3);
//      test.set(3, 3);
//      System.out.println("Set head Test: SAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set head Test: UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Set in Middle
//    try {
//      list.set(1, 1);
//      test.set(1, 1);
//      list.set(2, 2);
//      test.set(2, 2);
//      System.out.println("Set Middle Test: SAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set Middle Test: UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Set Below 0
//    try {
//      list.set(-1, 1);
//      test.set(-1, 1);
//      System.out.println("Set Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set Below 0 Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Set Above size
//    try {
//      list.set(10, 1);
//      test.set(10, 1);
//      System.out.println("Set Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set Above Size Test: SAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Clear
//    try {
//      list.clear();
//      test.clear();
//      System.out.println("Clear Test: SAT");
//    } catch (Exception e) {
//      System.out.println("Clear Test: UNSAT");
//    } finally {
//      System.out.println(list);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }

  } // end main() method

} // end DoublyLinkedList class definition