package edu.cofc.csci230;
//
//import java.util.ArrayList;
//import java.util.List;

public class Sort {

  public static void SelectionSortAscending(int arr[]) {
    int n = arr.length;

    // One by one move boundary of unsorted subarray
    for (int i = 0; i < n - 1; i++) {
      // Find the minimum element in unsorted array
      int min_idx = i;

      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[min_idx]) {
          min_idx = j;
        }
      }

      // Swap the found minimum element with the first
      // element
      int temp = arr[min_idx];
      arr[min_idx] = arr[i];
      arr[i] = temp;
      printArray(arr);
    }
  }

  public static void SelectionSortDescending(int arr[]) {
    int n = arr.length;

    for (int i = 0; i < n - 1; i++) {
      // Find the minimum element in unsorted array
      int max_idx = i;

      for (int j = i + 1; j < n; j++) {
        if (arr[j] > arr[max_idx]) {
          max_idx = j;
        }
      }

      // Swap the found minimum element with the first
      // element
      int temp = arr[max_idx];
      arr[max_idx] = arr[i];
      arr[i] = temp;
      printArray(arr);
    }
  }

  public static void InsertionSortAscending(int[] arr) {
    for (int i = 1; i < arr.length; ++i) {
      for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
        int temp = arr[j];
        arr[j] = arr[j - 1];
        arr[j - 1] = temp;
        printArray(arr);
      }
    }
  }

  public static void InsertionSortDescending(int[] arr) {
    for (int i = 1; i < arr.length; ++i) {
      for (int j = i; j > 0 && arr[j] > arr[j - 1]; j--) {
        int temp = arr[j];
        arr[j] = arr[j - 1];
        arr[j - 1] = temp;
        printArray(arr);
      }
    }
  }

  public static void BubbleSortAscending(int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 1; j < arr.length - i; j++) {
        if (arr[j - 1] > arr[j]) {
          int temp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = temp;
          count += 1;
        }
      }
      printArray(arr);
    }
    System.out.println(count);
  }

  public static void BoolBubbleSortAscending(int[] arr) {
    boolean sorted = true;
    int count = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 1; j < arr.length - i; j++) {
        if (arr[j - 1] > arr[j]) {
          sorted = false;
          int temp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = temp;
          count += 1;
        }
      }
      printArray(arr);
      if (sorted) {
        break;
      }
    }
    System.out.println(count);
  }

  public static void BubbleSortDescending(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 1; j < arr.length - i; j++) {
        if (arr[j - 1] < arr[j]) {
          int temp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = temp;
        }
      }
      printArray(arr);
    }
  }

  public static <Object extends Comparable<Object>> void BubbleSortAscending(Object[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 1; j < arr.length - i; j++) {
        if (arr[j - 1].compareTo(arr[j]) > 0) {
          Object temp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = temp;
          printArray(arr);
        }
      }
    }
  }

  public static <Object extends Comparable<Object>> void BubbleSortDescending(Object[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 1; j < arr.length - i; j++) {
        if (arr[j - 1].compareTo(arr[j]) < 0) {
          Object temp = arr[j];
          arr[j] = arr[j - 1];
          arr[j - 1] = temp;
          printArray(arr);
        }
      }
    }
  }

  public static <AnyType extends Comparable<AnyType>> List<AnyType> mergeSort(List<AnyType> list) {
    if (list == null) {
      throw new NullPointerException();
    }

    int size = list.size();
    if (size <= 1) {
      return list;
    }

    int mid = size / 2;

    List<AnyType> left = new ArrayList<>();
    List<AnyType> right = new ArrayList<>();

    for (int i = 0; i < mid; i++) {
      left.add(list.get(i));
    }
    for (int i = mid; i < size; i++) {
      right.add(list.get(i));
    }

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(left, right);
  } // end of mergeSort() method

  private static <AnyType extends Comparable<AnyType>> List<AnyType> merge(List<AnyType> left,
      List<AnyType> right) {
    List<AnyType> result = new ArrayList<>();

    // Moves elements into result from least to greatest
    while (!left.isEmpty() && !right.isEmpty()) {
      // If the element in B is <= the element in C
      if (left.get(0).compareTo(right.get(0)) <= 0) {
        result.add(left.remove(0));

      } else {
        result.add(right.remove(0));
      }

    }
    //Moves the remaining elements
    while (!left.isEmpty()) {
      result.add(left.remove(0));
    }
    while (!right.isEmpty()) {
      result.add(right.remove(0));
    }
    return result;
  }

  public static <AnyType extends Comparable<AnyType>> void quickSort(List<AnyType> list,
      int left, int right) {

    if (list == null) {
      throw new NullPointerException();
    }

    if(left < right) {
      int k = hoarePartition(list, left, right);
      quickSort(list, left, k);
      quickSort(list, k + 1, right);
    }
  }

  public static <AnyType extends Comparable<AnyType>> void swap(List<AnyType> list, int i, int j) throws IndexOutOfBoundsException {
    if ((i < 0) || (i >= list.size()) || (j < 0) || (j >= list.size())) {
      throw new IndexOutOfBoundsException();
    }

    AnyType temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }
  private static <AnyType extends Comparable<AnyType>> int hoarePartition(
      List<AnyType> list, int left, int right) {

    AnyType pivot = list.get(left);
    int i = left - 1; // Ensures first value is 0 and not 1
    int j = right + 1; // Ensures first value is size - 1 and not size

    while (true) {

      // Increments i until list[i] is greater than pivot
      do {
        i++;
//        System.out.print(" " + i);
      } while (list.get(i).compareTo(pivot) < 0);
//      System.out.println();

      // Decrements j until list[i] is less than pivot
      do {
        j--;
//        System.out.print(" " + j);
      } while (list.get(j).compareTo(pivot) > 0);

      if (i < j) {
        swap(list, i, j);
      } else {
        return j;
      }
    }
  }

  public static <AnyType extends Comparable<AnyType>> Heap<AnyType> HeapSort(java.util.List<AnyType> list) {
    Heap<AnyType> sortHeap = new Heap<AnyType>( list, true, false, list.size());

    while (sortHeap.size() > 0) {
      sortHeap.swap(0, sortHeap.size() - 1);
      sortHeap.downSize();
      maxHeapifySort( sortHeap,0);
    }

    return sortHeap;
  }

  private static <AnyType extends Comparable<AnyType>> void maxHeapifySort(Heap<AnyType> sortHeap, int index) {


    int left = sortHeap.leftChildIndex(index);
    int right = sortHeap.rightChildIndex(index);
    int max = index;

    if (left < sortHeap.size() && sortHeap.get(left).compareTo(sortHeap.get(max)) > 0) {
      max = left;
    }

    if (right < sortHeap.size() && sortHeap.get(right).compareTo(sortHeap.get(max)) > 0) {
      max = right;
    }

    if (max != index ) {
      sortHeap.swap(index, max);
      maxHeapifySort(sortHeap, max);
    }
  }


  // Prints the array
  public static void printArray(int arr[]) {
    int n = arr.length;
    for (int i = 0; i < n; ++i) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static <Object extends Comparable<Object>> void printArray(Object arr[]) {
    int n = arr.length;
    for (int i = 0; i < n; ++i) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }


  public static void main(String[] args) {
    java.util.List<Integer> arr1 = new java.util.ArrayList<>();
    arr1.add(25);
    arr1.add(-25);
    arr1.add(25);
    arr1.add(-25);
    arr1.add(0);
    arr1.add(10);
    arr1.add(100);
    arr1.add(-10);
    arr1.add(1000);
    arr1.add(100);
    arr1.add(-1000);
    arr1.add(-100);
    System.out.println(arr1);

    int arr2[] = {25, 12, 22, -25, 64, 22, 11, 0};

//    SelectionSortAscending(arr1);
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    SelectionSortDescending(arr2);
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//
//    printArray(arr1);
//    printArray(arr2);
//
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    int arr3[] = {25, 12, -25, 64, 22, 11, 0};
//    int arr4[] = {25, 12, -25, 64, 22, 11, 0};
//
//    InsertionSortAscending(arr3);
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    InsertionSortDescending(arr4);
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    int arr5[] = {25, 12, -25, 64, 22, 11, 0};
//    int arr6[] = {25, 12, -25, 64, 22, 200, 11, 100};
//
//    BubbleSortAscending(arr5);
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//    BubbleSortDescending(arr6);
//    System.out.println("==============================================");
//    System.out.println("==============================================");
//
//    BoolBubbleSortAscending(arr5);

//    arr1 = mergeSort(arr1);
//    quickSort(arr1, 0, arr1.size() - 1);

    Heap<Integer> sorted = HeapSort(arr1);
    System.out.println(sorted);
  }

}
