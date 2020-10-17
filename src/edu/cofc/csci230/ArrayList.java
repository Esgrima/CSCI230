package edu.cofc.csci230;

import static java.lang.Math.log;

/**
 * ArrayList Data Structure
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class ArrayList<AnyType extends Comparable<AnyType>> implements List<AnyType> {

  // instance variables
  private AnyType[] array;
  private int size = 0;
  private static int MINIMUM_CAPACITY = 10;
  private int capacity = MINIMUM_CAPACITY;

  /**
   * Constructs an empty list with an initial capacity (for this assignment initial capacity is 10 -
   * see constant instance variable)
   */
  public ArrayList() {

    array = (AnyType[]) new Comparable[capacity];

  } // end constructor

  /**
   * Do not modify this method. Only use for testing purposes.
   */
  public int getCapacity() {

    return capacity;

  } // end getCapacity() method

  /**
   * Appends the specified element to the end of this list.
   *
   * @param t - element to be inserted
   */
  public boolean add(AnyType t) {


    if (size >= capacity) {
      grow();
    }

    array[size] = t;
    size++;

    return false;
  } // end add() method

  /**
   * Inserts the specified element at the specified position in this list.
   *
   * @param index - index at which the specified element is to be inserted
   * @param t - element to be inserted
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public void add(int index, AnyType t) throws IndexOutOfBoundsException, NullPointerException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    if (t == null) {
      throw new NullPointerException();
    }
    if (size >= capacity) {
      grow();
    }

    // Shifts elements to the right from the end(right) to the index(left) to open up index
    for (int i = size; i > index; i--) {
      array[i] = array[i - 1];
    }

    array[index] = t;
    size++;

  } // end add() method

  /**
   * Replaces the element at the specified position in this list with the specified element.
   *
   * @param index - index of the element to replace
   * @param t - element to be inserted
   * @throws IndexOutOfBoundsException, NullPointerException
   */
  public AnyType set(int index, AnyType t) throws IndexOutOfBoundsException, NullPointerException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    if (t == null) {
      throw new NullPointerException();
    }

    array[index] = t;
    return null;
  } // end set() method

  /**
   * Removes the element at the specified position in this list.
   *
   * @param index - index of the element to be removed
   */
  public AnyType remove(int index) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Requirement - you must use loops (i.e. may not use
     * System.arraycopy, or any other array copy operation
     * available in the Java API) to perform left or right
     * shift operations
     *
     */
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    AnyType obj = array[index];

    for (int i = index; i < size - 1; i++) {
      array[i] = array[i + 1];
    }
    size--;

    if (size <= (capacity / 2)) {
      shrink();
    }
    return obj;

  } // end remove() method

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index - index of the element to be returned
   */
  public AnyType get(int index) throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    return array[index];
  } // end get() method

  /**
   * Returns the number of elements in this list.
   */
  public int size() {

    return size;

  } // end size() method

  /**
   * Returns true if this list contains no elements.
   */
  public boolean isEmpty() {

    return (size == 0);

  } // end isEmpty() method


  /**
   * Removes all of the elements from this list and the capacity is reset to the MINIMUM_CAPACITY
   */
  public void clear() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    size = 0;
    capacity = MINIMUM_CAPACITY;
    array = (AnyType[]) new Comparable[capacity];
  } // end clear method


  /**
   * Trims the capacity of this ArrayList instance to be the list's current size. An application can
   * use this operation to minimize the storage of an ArrayList instance.
   */
  public void trimToSize() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    int newCapacity = size;

    // Ensures new capacity is not less than MINIMUM_CAPACITY
    if(newCapacity < MINIMUM_CAPACITY){
      newCapacity = MINIMUM_CAPACITY;
    }

    if (newCapacity != capacity) {
      AnyType[] temp = (AnyType[]) new Comparable[newCapacity];

      for (int i = 0; i < newCapacity; i++) {
        temp[i] = array[i];
      }
      //assert temp.equals(array);
      capacity = newCapacity;
      array = temp;
    }
  } // end trimToSize() method


  /**
   * Increases the capacity of this ArrayList instance, if necessary, to ensure that it can hold at
   * least the number of elements specified by the minimum capacity argument.
   *
   * @param minCapacity - the desired minimum capacity
   */
  public void ensureCapacity(int minCapacity) {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */

    MINIMUM_CAPACITY = minCapacity;

    if(minCapacity > capacity) {
      AnyType[] temp = (AnyType[]) new Comparable[minCapacity];
      capacity = minCapacity;

      for (int i = 0; i < size; i++) {
        temp[i] = array[i];
      }
      array = temp;
    }
  } // end ensureCapacity() method


  /**
   * The capacity of the array is resized using this function:
   *
   * new capacity = old capacity + 20 * log( log( old capacity ) )
   *
   * where log() is the natural logarithm.
   */
  private void grow() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Requirement - you must use loops (i.e. may not use
     * System.arraycopy, or any other array copy operation
     * available in the Java API)
     *
     */
    int newCapacity = (int) (capacity + (20 * log(log(capacity))));
    AnyType[] temp = (AnyType[]) new Comparable[newCapacity];
    capacity = newCapacity;

    for (int i = 0; i < size; i++) {
      temp[i] = array[i];
    }
    array = temp;
  } // end grow() method


  /**
   * The capacity of the array is resized using this function:
   *
   * new capacity = old capacity / 2
   *
   * Note: the new capacity should never be < MINIMUM_CAPACITY
   */
  private void shrink() {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     * Requirement - you must use loops (i.e. may not use
     * System.arraycopy, or any other array copy operation
     * available in the Java API)
     *
     */
    int newCapacity = MINIMUM_CAPACITY;

    if ((capacity / 2) >= MINIMUM_CAPACITY) {
      newCapacity = capacity / 2;
    }

    AnyType[] temp = (AnyType[]) new Comparable[newCapacity];
    capacity = newCapacity;

    for (int i = 0; i < size; i++) {
      temp[i] = array[i];
    }
    array = temp;
  } // end shrink() method

  /**
   * swaps to elements in a list at index
   * position i and j.
   *
   * For example,
   * 	if A = 1->2->3->4->5 and swap( 1, 3 ) is executed
   * 	then A = 1->4->3->2->5
   *
   * @param i
   * @param j
   * @throws IndexOutOfBoundsException
   */
  public void swap(int i, int j)  throws IndexOutOfBoundsException {

    /**
     * -------------------------------------------
     * DONE: You fully implement this method
     *
     */
    if ((i < 0) || (i >= size) || (j < 0) || (j >= size)) {
      throw new IndexOutOfBoundsException();
    }

    AnyType temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  } // end swap() method


  /**
   * For debugging purposes :)
   *
   * Note: this only works for integer values hence, the %d format specifier in the string format
   * method. If you want a different specifier, like string %s, you can change.
   */
  public String toString() {

    StringBuilder buffer = new StringBuilder();

    buffer.append(String.format("Size=%d, A = [ ", size));

    if (!isEmpty()) {

      for (int i = 0; i < size - 1; i++) {
        buffer.append(String.format("%d, ", array[i]));
      }

      buffer.append(String.format("%d ]", array[size - 1]));

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

    /**
     * -------------------------------------------
     * DONE: Put your test cases here
     *
     */
    ArrayList<Integer> arrayList = new ArrayList<>();
    java.util.ArrayList<Integer> test = new java.util.ArrayList<>();

    for (int i = 0; i < 5; i++) {
      arrayList.add(i);
      test.add(i);
    }
    System.out.println(arrayList);
    System.out.printf("Size=%d, A = %s\n", test.size(), test);


    //Case Swap head and tail
    try {
      arrayList.swap(0, arrayList.size() - 1);
      assert arrayList.get(0) == 4;
      assert arrayList.get(4) == 0;

      System.out.println("Case Swap head and tail: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap head and tail: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("=============================================");
    }

    //Case Swap head and body
    try {
      arrayList.swap(0, 3);
      if (arrayList.get(3) != 4) {
        throw new Exception();
      }
      if (arrayList.get(0) != 3) {
        throw new Exception();
      }

      System.out.println("Case Swap head and body: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap head and body: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("=============================================");
    }

    //Case Swap tail and body
    try {
      arrayList.swap(4, 1);
      if (arrayList.get(4) != 1) {
        throw new Exception();
      }
      if (arrayList.get(1) != 0) {
        throw new Exception();
      }

      System.out.println("Case Swap tail and body: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap tail and body: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("=============================================");
    }

    //Case Swap body and body
    try {
      arrayList.swap(3, 1);
      if (arrayList.get(3) != 0) {
        throw new Exception();
      }
      if (arrayList.get(1) != 4) {
        throw new Exception();
      }

      System.out.println("Case Swap body and body: SAT");
    } catch (Exception e) {
      System.out.println("Case Swap body and body: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("=============================================");
    }

    //Case Swap below zero
    try {
      arrayList.swap(-1, 3);

      System.out.println("Case Swap Below zero: UNSAT");
    } catch (Exception e) {
      System.out.println("Case Swap Below zero: SAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("=============================================");
    }

    //Case Swap above size
    try {
      arrayList.swap(3, 10);

      System.out.println("Case Swap Above Size: UNSAT");
    } catch (Exception e) {
      System.out.println("Case Swap Above Size: SAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("=============================================");
    }

    //Sort test cases
    arrayList.clear();
    arrayList.add(-1);
    arrayList.add(100);
    arrayList.add(20);
    arrayList.add(13);
    arrayList.add(20);

    System.out.println(arrayList);

    try{
      Utils.ascending = true; // sort in ascending order
      Utils.selectionSort(arrayList);
      System.out.println("Selection Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Selection Sort Ascending: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("==============================================");
    }

    try{
      Utils.ascending = false; // sort in descending order
      Utils.selectionSort(arrayList);
      System.out.println("Selection Sort descending: SAT");
    } catch (Exception e) {
      System.out.println("Selection Sort descending: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("==============================================");
    }

    try{
      Utils.ascending = true; // sort in ascending order
      Utils.bubbleSort(arrayList);
      System.out.println("Bubble Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Bubble Sort Ascending: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("==============================================");
    }

    try{
      Utils.ascending = false; // sort in descending order
      Utils.bubbleSort(arrayList);
      System.out.println("Bubble Sort descending: SAT");
    } catch (Exception e) {
      System.out.println("Bubble Sort descending: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("==============================================");
    }

    try{
      Utils.ascending = true; // sort in ascending order
      Utils.insertionSort(arrayList);
      System.out.println("Insertion Sort Ascending: SAT");
    } catch (Exception e) {
      System.out.println("Insertion Sort Ascending: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("==============================================");
    }

    try{
      Utils.ascending = false; // sort in descending order
      Utils.insertionSort(arrayList);
      System.out.println("Insertion Sort descending: SAT");
    } catch (Exception e) {
      System.out.println("Insertion Sort descending: UNSAT");
    } finally {
      System.out.println(arrayList);
      System.out.println("==============================================");
    }


//////////////////////////////////Original Test Cases//////////////////////////////////////////////
//    // Case Get Head
//    try {
//      assert arrayList.get(0).equals(test.get(0));
//      System.out.printf("Get head(0:%d) Test: SAT\n", arrayList.get(0));
//    } catch (IndexOutOfBoundsException e) {
//      System.out.printf("Get head(0:%d) Test: UNSAT\n", arrayList.get(0));
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get Tail
//    try {
//      assert arrayList.get(0).equals(test.get(4));
//      System.out.printf("Get tail(4:%d) Test: SAT\n", arrayList.get(4));
//    } catch (IndexOutOfBoundsException e) {
//      System.out.printf("Get tail(4:%d) Test: UNSAT\n", arrayList.get(4));
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get below 0
//    try {
//      arrayList.get(-5);
//      System.out.println("Get Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Get Below 0 Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get Above size
//    try {
//      arrayList.get(10);
//      System.out.println("Get Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Get Above Size Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Get in middle
//    try {
//      assert arrayList.get(2).equals(test.get(2));
//      System.out.printf("Get middle(2:%d) Test: SAT\n", arrayList.get(2));
//    } catch (IndexOutOfBoundsException e) {
//      System.out.printf("Get middle(2:%d) Test: UNSAT\n", arrayList.get(2));
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Clear
//    try {
//      arrayList.clear();
//      test.clear();
//      System.out.println("Clear Test: SAT");
//    } catch (Exception e) {
//      System.out.println("Clear Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Restore arrayList & test
//    for (int i = 0; i < 5; i++) {
//      arrayList.add(i);
//      test.add(i);
//    }
//    System.out.println(arrayList);
//    System.out.printf("Size=%d, A = %s\n", test.size(), test);
//    System.out.println("==============================================");
//
//    // Case Set Head
//    try {
//      arrayList.set(0, 4);
//      test.set(0, 4);
//      System.out.println("Set head Test: SAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set head Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Set Tail
//    try {
//      arrayList.set(4, 0);
//      test.set(4, 0);
//      System.out.println("Set head Test: SAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set head Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Set in Middle
//    try {
//      arrayList.set(1, 3);
//      test.set(1, 3);
//      arrayList.set(2, 2);
//      test.set(2, 2);
//      arrayList.set(3, 1);
//      test.set(3, 1);
//      System.out.println("Set Middle Test: SAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set Middle Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Set Below 0
//    try {
//      arrayList.set(-1, 1);
//      test.set(-1, 1);
//      System.out.println("Set Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set Below 0 Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Set Above size
//    try {
//      arrayList.set(10, 1);
//      test.set(10, 1);
//      System.out.println("Set Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Set Above Size Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Set null
//    try {
//      arrayList.set(2, null);
//      test.set(2, null);
//      System.out.println("Set Null Test: UNSAT");
//    } catch (NullPointerException e) {
//      System.out.println("Set Null Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Add(i, e) Below 0
//    try {
//      arrayList.add(-1, -1);
//      test.add(-1, -1);
//      System.out.println("Add Below Zero Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Add Below Zero Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Add(i, e) Above Size
//    try {
//      arrayList.add(100, 100);
//      test.add(100, 100);
//      System.out.println("Add Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Add Above Size Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Add(i, e) At head
//    try {
//      arrayList.add(0, 100);
//      test.add(0, 100);
//      System.out.println("Add at head Test: SAT");
//    } catch (Exception e) {
//      System.out.println("Add at head Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }
//
//    // Case Add(i, e) at final index(5)
//    try {
//      arrayList.add(5, 100);
//      test.add(5, 100);
//      System.out.println("Add at final index Test: SAT");
//    } catch (Exception e) {
//      System.out.println("Add at final index Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("===============================================");
//    }
//
//    // Case Add(i, e) in middle
//    try {
//      arrayList.add(2, 100);
//      test.add(2, 100);
//      System.out.println("Add in Mid Test: SAT");
//    } catch (Exception e) {
//      System.out.println("Add in Mid Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("===============================================");
//    }
//
//    // Case Add(i, e) Null
//    try {
//      arrayList.add(2, null);
//      test.add(2, null);
//      System.out.println("Add null Test: UNSAT");
//    } catch (NullPointerException e) {
//      System.out.println("Add null Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("===============================================");
//    }
//
//    // Case Remove head
//    try {
//      assert arrayList.remove(0) == 100;
//      arrayList.remove(0);
//      test.remove(0);
//      System.out.println("Remove Head Test SAT");
//
//    } catch (Exception e) {
//      System.out.println("Remove Head Test UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove in middle
//    try {
//      assert arrayList.remove(1) == 100;
//      arrayList.remove(1);
//      test.remove(1);
//      System.out.println("Remove in middle Test SAT");
//
//    } catch (Exception e) {
//      System.out.println("Remove in middle Test UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove Tail
//    try {
//      assert arrayList.remove(5) == 0;
//      arrayList.remove(5);
//      test.remove(5);
//      System.out.println("Remove Tail Test SAT");
//
//    } catch (Exception e) {
//      System.out.println("Remove Tail Test UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove Below 0
//    try {
//      arrayList.remove(-1);
//      test.remove(-1);
//      System.out.println("Remove Below 0 Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove Below 0 Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Remove Above Size
//    try {
//      arrayList.remove(10);
//      test.remove(10);
//      System.out.println("Remove Above Size Test: UNSAT");
//    } catch (IndexOutOfBoundsException e) {
//      System.out.println("Remove Above Size Test: SAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("=============================================");
//    }
//
//    // Case Grow
//    try {
//      int initial_capacity = arrayList.getCapacity();
//      int expected_capacity = (int) (initial_capacity + (20 * log(log(initial_capacity))));
//
//      for (int i = 0; i < 7; i++) {
//        arrayList.add(i);
//        test.add(i);
//      }
//      System.out.println(arrayList);
//
//      if (expected_capacity != arrayList.getCapacity()) {
//        throw new Exception();
//      }
//
//      System.out.println("Grow Size Test: SAT");
//    } catch (Exception e) {
//      System.out.println("Grow Size Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.println("=============================================");
//    }
//
//    // Case Shrink Above MINIMUM CAPACITY
//    try {
//      int initial_capacity = arrayList.getCapacity(); // 26
//      int expected_capacity = (initial_capacity / 2); //13
//      System.out
//          .printf("Current Size: %d, Expected Capacity: %d\n", arrayList.size(), expected_capacity);
//
//      for (int i = 11; i != 10; i--) {
//        arrayList.remove(i);
//        test.remove(i);
//      }
//      System.out.println(arrayList);
//
//      if (expected_capacity != arrayList.getCapacity()) {
//        System.out.println(arrayList.size());
//        System.out.println(initial_capacity);
//        System.out.printf("%d != %d\n", expected_capacity, arrayList.getCapacity());
//        throw new Exception();
//      }
//      System.out.println("Shrink Size While Above MINIMUM_CAPACITY Test: SAT");
//
//    } catch (Exception e) {
//      System.out.println("Shrink Size While Above MINIMUM_CAPACITY Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.println("=============================================");
//    }
//
//    // Case Shrink Below MINIMUM CAPACITY
//    try {
//      int initial_capacity = arrayList.getCapacity(); // 26
//      System.out
//          .printf("Current Size: %d, Expected Capacity: %d\n", arrayList.size(),
//              MINIMUM_CAPACITY);
//
//      for (int i = 10; i != 3; i--) {
//        arrayList.remove(i);
//        test.remove(i);
//      }
//      System.out.println(arrayList);
//
//      if (MINIMUM_CAPACITY != arrayList.getCapacity()) {
//        System.out.println(arrayList.size());
//        System.out.println(initial_capacity);
//        System.out.printf("%d != %d\n", MINIMUM_CAPACITY, arrayList.getCapacity());
//        throw new Exception();
//      }
//      System.out.println("Shrink Size While below MINIMUM_CAPACITY Test: SAT");
//
//    } catch (Exception e) {
//      System.out.println("Shrink Size While below MINIMUM_CAPACITY Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.println("=============================================");
//    }
//
//    //Case TrimToSize non empty arrayList
//    try {
//      System.out.println(arrayList.capacity);
//      arrayList.trimToSize();
//      test.trimToSize();
//
//      if (arrayList.capacity != arrayList.MINIMUM_CAPACITY) {
//        throw new Exception();
//      }
//      System.out.println("Trim to Size Test: SAT");
//
//    } catch (Exception e) {
//      System.out.println("Trim to Size Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.println("=============================================");
//    }
//
//    //Case TrimToSize empty arrayList
//    try {
//      arrayList.clear();
//      arrayList.trimToSize();
//      test.clear();
//      test.trimToSize();
//
//      if (arrayList.capacity != arrayList.MINIMUM_CAPACITY) {
//        throw new Exception();
//      }
//      System.out.println("Trim to Size Empty Test: SAT");
//
//    } catch (Exception e) {
//      System.out.println("Trim to Size Empty Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.println("=============================================");
//    }
//
//    //Case TrimToSize Larger arrayList
//    try {
//      for (int i = 0; i < 20; i++) {
//        arrayList.add(i);
//        test.add(i);
//      }
//      System.out.println(arrayList.capacity);
//
//      arrayList.trimToSize();
//
//
//      if (arrayList.size() != arrayList.getCapacity()) {
//        throw new Exception();
//      }
//      System.out.println("Trim to Size Larger Array Test: SAT");
//
//    } catch (Exception e) {
//      System.out.println("Trim to Size Larger Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.println("=============================================");
//    }
//
//    // arrayList and test reset
//    arrayList.clear();
//    test.clear();
//
//    for (int i = 0; i < 20; i++) {
//      arrayList.add(i);
//      test.add(i);
//    }
//    System.out.println(arrayList);
//    System.out.printf("Size=%d, A = %s\n", test.size(), test);
//    System.out.println("==============================================");
//
//
//    // Case EnsureCapacity
//    try{
//      int exp_capacity = 2 * arrayList.getCapacity();
//      arrayList.ensureCapacity(exp_capacity);
//
//      if (exp_capacity != arrayList.getCapacity()){
//        throw new Exception();
//      }
//      System.out.println("Ensure Capacity Test: SAT");
//
//    } catch (Exception e){
//      System.out.println("Ensure Capacity Test: UNSAT");
//    } finally {
//      System.out.println(arrayList);
//      System.out.printf("Size=%d, A = %s\n", test.size(), test);
//      System.out.println("==============================================");
//    }

  } // end main() method

} // end ArrayList class definition
