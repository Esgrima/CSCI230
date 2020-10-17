package edu.cofc.csci230;

import java.util.ArrayList;

/**
 * Utilities class that will sort in ascending and descending order the elements of a list.
 *
 * The sorting algorithms supported in this class are: 1. selection sort 2. bubble sort 3. insertion
 * sort
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class Utils {

  public static boolean ascending = true;

  /**
   *
   */
  private Utils() {

  } // end private constructor

  /**
   *
   * @param list
   */
  public static <AnyType extends Comparable> void selectionSort(List<AnyType> list)
      throws IndexOutOfBoundsException {

    /*
     * DONE:
     *
     * Implement selection sort algorithm as
     * described in class. The pseudo-code for
     * this algorithm can also be found in the
     * content section on OAKS and at the end
     * of the homework assignment.
     *
     * Must sort in:
     * -----------------------------------------
     * 1. ascending order (first element
     * in list is smallest value, last element in
     * list is largest value).
     *
     * 2. descending order (first element
     * in list is largest value, last element in
     * list is smallest value).
     *
     */
    int min_max;

    for (int start = 0; start < list.size() - 1; start++) {
      min_max = start;

      for (int end = start + 1; end < list.size(); end++) {
        if (ascending) {
          if (list.get(end).compareTo(list.get(min_max)) < 0) {
            min_max = end;
          }
        } else {
          if (list.get(end).compareTo(list.get(min_max)) > 0) {
            min_max = end;
          }
        }
      }
      list.swap(start, min_max);
    }
    System.out.printf("Ascending order [%b]\n", ascending);
  } // end selectionSort() method

  /**
   *
   * @param list
   */
  public static <AnyType extends Comparable> void bubbleSort(List<AnyType> list)
      throws IndexOutOfBoundsException {

    /*
     * DONE:
     *
     * Implement bubble sort algorithm as
     * described in class. The pseudo-code for
     * this algorithm can also be found in the
     * content section on OAKS and at the end
     * of the homework assignment.
     *
     *
     * Must sort in:
     * -----------------------------------------
     * 1. ascending order (first element
     * in list is smallest value, last element in
     * list is largest value).
     *
     * 2. descending order (first element
     * in list is largest value, last element in
     * list is smallest value).
     *
     */

    for (int start = 0; start < list.size() - 1; start++) {
      for (int end = 0; end < list.size() - 1 - start; end++) {
        if (ascending) {
          if (list.get(end + 1).compareTo(list.get(end)) < 0) {
            list.swap(end, end + 1);
          }
        } else {
          if (list.get(end + 1).compareTo(list.get(end)) > 0) {
            list.swap(end, end + 1);
          }
        }
      }
    }
    System.out.printf("Ascending order [%b]\n", ascending);
  } // end bubbleSort() method

  /**
   *
   * @param list
   */
  public static <AnyType extends Comparable> void insertionSort(List<AnyType> list)
      throws IndexOutOfBoundsException {

    /*
     * DONE:
     *
     * Implement insertion sort algorithm as
     * described in class. The pseudo-code for
     * this algorithm can also be found in the
     * content section on OAKS and at the end
     * of the homework assignment.
     *
     *
     * Must sort in:
     * -----------------------------------------
     * 1. ascending order (first element
     * in list is smallest value, last element in
     * list is largest value).
     *
     * 2. descending order (first element
     * in list is largest value, last element in
     * list is smallest value).
     *
     */

    for (int start = 1; start < list.size(); start++) {
      //Compares element at start to each element prior to start
      for (int end = start; end > 0; end--) {
        if (ascending) {
          if (list.get(end).compareTo(list.get(end - 1)) < 0) {
            list.swap(end, end - 1);
          }
        } else {
          if (list.get(end).compareTo(list.get(end - 1)) > 0) {
            list.swap(end, end - 1);
          }
        }
      }
    }
    System.out.printf("Ascending order [%b]\n", ascending);
  } // end insertionSort() method


  private static <AnyType extends Comparable<AnyType>> java.util.List<AnyType> mergeSort(
      java.util.List<AnyType> list) {
    if (list == null) {
      throw new NullPointerException();
    }

    int size = list.size();
    if (size <= 1) {
      return list;
    }

    int mid = size / 2;

    java.util.List<AnyType> left = new java.util.ArrayList<>();
    java.util.List<AnyType> right = new java.util.ArrayList<>();

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

  private static <AnyType extends Comparable<AnyType>> java.util.List<AnyType> merge(
      java.util.List<AnyType> left, java.util.List<AnyType> right) {
    java.util.List<AnyType> result = new ArrayList<>();

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
} // end Utils class definition
