package edu.cofc.csci230;

/**
 * Heap data structure.
 *
 *
 * @author Rex Ferrer
 */
public class Heap<AnyType extends Comparable<AnyType>> implements Comparable {

  private java.util.ArrayList<AnyType> heap;
  private static int CAPACITY = 10;
  private int size;
  private boolean maxHeap = false;
  private boolean minHeap = false;

  /**
   * Builds a max or min heap from any collection implementing list. Default is max.
   * @param list collection that will be heapified
   * @param max true if maxheap, false otherwise
   * @param min true if minheap, false otherwise
   * @param maxCapacity max size of heap
   */
  public Heap(java.util.List<AnyType> list, boolean max, boolean min, int maxCapacity) {
    this.heap = new java.util.ArrayList<>(list);
    this.size = list.size();
    CAPACITY = maxCapacity;

    if (min && !max) {
      this.minHeap = true;
      minHeapify();
    }
    else {
      this.maxHeap = true;
      maxHeapify();
    }
  }

  // Heap constructor for HeapSort
  public Heap(java.util.List<AnyType> list, int maxCapacity) {
    this.heap = new java.util.ArrayList<>(list);
    this.size = list.size();
    CAPACITY = maxCapacity;
    this.maxHeap = true;
  }

  public void maxHeapify() {
    for (int i = (int) Math.floor(this.size / 2.0); i >= 0; i--) {
      this.siftDownMax(i);
    }
  }

  public void siftDownMax(int index) {
    if (index < 0 || index > this.size) {
      return;
    }

    while (!isLeaf(index)) {
      int childIdx = leftChildIndex(index);

      AnyType child = this.get(childIdx);
      AnyType sibling = this.get(childIdx + 1);

      if ((childIdx < this.size - 1) && child.compareTo(sibling) < 0) {
        childIdx++;
      }

      if (this.get(index).compareTo(this.get(childIdx)) >= 0) {
        this.swap(index, childIdx);
      }

      index = childIdx;
    }
  }

  public void siftDownMin(int index) {
    if (index < 0 || index > this.size) {
      return;
    }

    while (!isLeaf(index)) {
      int childIdx = leftChildIndex(index);

      AnyType child = this.get(childIdx);
      AnyType sibling = this.get(childIdx + 1);

      if ((childIdx < this.size - 1) && child.compareTo(sibling) > 0) {
        childIdx++;
      }

      if (this.get(index).compareTo(this.get(childIdx)) <= 0) {
        this.swap(index, childIdx);
      }

      index = childIdx;
    }
  }

  public void minHeapify() {
    for (int i = (int) Math.floor(this.size / 2.0); i >= 0; i--) {
      this.siftDownMin(i);
    }
  }

  public void swap(int i, int j) {
    AnyType temp = this.get(i);
    this.set(i, this.get(j));
    this.set(j, temp);
  }

  public boolean isEmpty() {
    return this.size <= 0;
  }

  public AnyType get(int index) {
    return this.heap.get(index);
  }

  public void set(int index, AnyType element) {
    this.heap.set(index, element);
  }

  public int size() {
    return this.size;
  }

  public void downSize() {
    this.size--;
  }

  public static int getCAPACITY() {
    return CAPACITY;
  }

  /**
   * height = ⌈ logn + 1 ⌉
   * @return height of the tree
   */
  public int getHeight() {
    return (int) Math.ceil((Math.log(this.size) + 1.0));
  }

  public boolean isMaxHeap() {
    return this.maxHeap;
  }

  public boolean isMinHeap() {
    return this.minHeap;
  }

  public boolean isLeaf(int index) {
    return (index >= this.size / 2) && (index < this.size);
  }

  public int leftChildIndex(int index) {
//    if (index >= this.size / 2) {
//      return -1;
//    }

    return 2 * index + 1;
  }

  public int rightChildIndex(int index) {
//    if (index >= (this.size - 1) / 2) {
//      return -1;
//    }

    return 2 * index + 2;
  }

  public int parentIndex(int index) {
    if (index <= 0) {
      return -1;
    }

    return (int) Math.floor((index - 1.0) / 2.0);
  }

  public int leftSiblingIndex(int index) {
    //TODO
    return 0;
  }

  public int rightSiblingIndex(int index) {
    //TODO
    return 0;
  }

  /**
   * Inserts element into and heapifies if necessary
   */
  public void insert(AnyType element) {
    //TODO
  }

  public AnyType getMax() {
    if (this.maxHeap) {
      // O(1)
      return this.heap.get(0);
    } else {
      AnyType max = this.heap.get((int)Math.floor(this.size / 2.0));

      // O(n)
      // Finds the max value in a minHeap's leaf section
      // Future Idea: Copy the sublist and MaxHeapify it
      for (AnyType key : this.heap.subList((int) Math.floor(this.size / 2.0), this.size - 1)) {
        if (key.compareTo(max) > 0) {
          max = key;
        }
      }
      return max;
    }

  }

  public AnyType getMin() {
    if(this.minHeap) {
      // O(1)
      return this.heap.get(0);
    } else {
      AnyType min = this.heap.get((int)Math.floor(size / 2.0));

      // O(n)
      // Finds the min value in a maxHeap's leaf section
      // Future Idea: Copy the sublist and minHeapify it
      for (AnyType key : this.heap.subList((int) Math.floor(this.size / 2.0), this.size - 1)) {
        if (key.compareTo(min) < 0) {
          min = key;
        }
      }
      return min;
    }
  }




  @Override
  public int compareTo(Object o) {
    return 0;
  }

  public String toString() {
    return this.heap.toString();
  }

  public static void main(String[] args) {

  }
}
