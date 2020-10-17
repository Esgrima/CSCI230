//package edu.cofc.csci230;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTimeout;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import edu.cofc.csci230.ArrayList;
//
//class ArrayListTest {
//
//  edu.cofc.csci230.ArrayList<String> testList;
//  ArrayList<String> sampleList;
//  static final String UNEXPECTED_EXCEPTION = "Unexpected exception. (-5 pts)";
//
//  @org.junit.jupiter.api.BeforeEach
//  void setUp() {
//
//    // sampleList is an instance of ArrayList.
//    // We use it as a baseline to test the edu.cofc.csci230.ArrayList class.
//    sampleList = new ArrayList<String>();
//    sampleList.add("To Kill a Mockingbird");
//    sampleList.add("Chicago");
//    sampleList.add("Rent");
//    sampleList.add("Cats");
//    sampleList.add("Kinky Boots");
//    sampleList.add("Wicked");
//    sampleList.add("The Phantom of the Opera");
//
//    // testList is an instance of the assignment's edu.cofc.csci230.ArrayList.
//    // We use it in some of the test cases below.
//    // Some test cases may require more than one list, so they create their own instances.
//    try {
//      testList = getList(sampleList);
//      testList.ensureCapacity(10);
//    } catch (NullPointerException e) {
//    }
//  }
//
//
//  @org.junit.jupiter.api.AfterEach
//  void tearDown() {
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Grow (add without index)")
//  void grow() {
//    // Should grow with the specified function
//    // new capacity = old capacity + 20 * log( log( old capacity ) )
//    try {
//      int oldCapacity = testList.getCapacity();
//      while (testList.size() < 55) {
//        testList.add("Something");
//      }
//
//      boolean listGrows = oldCapacity < testList.getCapacity();
//      assertTrue(listGrows, "Should grow the list in capacity as new items are added (-5 pts)");
//
//      // List should grow according to the function provided. This should work for those who rounded up as well as those who dropped the decimal part.
//      if (listGrows) {
//        assertTrue(testList.getCapacity() == 76 || testList.getCapacity() == 78,
//            "The list should grow according to the function provided (-2.5 pts)");
//      }
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @Test
//  @DisplayName("Grow (add with index)")
//  public void growAddWithIndex() {
//    // Should grow with the specified function
//    // new capacity = old capacity + 20 * log( log( old capacity ) )
//    try {
//      int oldCapacity = testList.getCapacity();
//      while (testList.size() < 55) {
//        testList.add(1, "Something");
//      }
//
//      boolean listGrows = oldCapacity < testList.getCapacity();
//      Assertions.assertTrue(listGrows,
//          "Should grow the list in capacity as new items are added (-5 pts)");
//
//      // List should grow according to the function provided. This should work for those who rounded up as well as those who dropped the decimal part.
//      if (listGrows) {
//        Assertions.assertTrue(testList.getCapacity() == 76 || testList.getCapacity() == 78,
//            "The list should grow according to the function provided (-2.5 pts)");
//      }
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @Test
//  @DisplayName("Shrink")
//  public void shrink() {
//    try {
//      while (testList.size() < 55) {
//        testList.add("Something");
//      }
//      int oldCapacity = testList.getCapacity(); // Should be 76 or 78
//      while (testList.size() > 35) {
//        testList.remove(0);
//      }
//      // Capacity should now be 38 or 39
//      boolean listShrinks = oldCapacity > testList.getCapacity();
//      assertTrue(listShrinks, "Should shrink the list in capacity as items are removed (-5 pts)");
//
//      if (listShrinks) {
//        assertTrue(testList.getCapacity() == 38 || testList.getCapacity() == 39,
//            "Should shrink by half as items are removed (-2.5 pts)");
//      }
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @org.junit.jupiter.api.Test
//  void add() {
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Add (with index)")
//  void add1() {
//    String play = "The Book of Mormon";
//    // Add to middle
//    try {
//      ArrayList<String> expectedMiddleList = new ArrayList<>(sampleList);
//      expectedMiddleList.add(4, play);
//      edu.cofc.csci230.ArrayList<String> actualMiddleList = getList(sampleList);
//      try {
//        actualMiddleList.add(4, play);
//      } catch (Exception e) {
//      }
//
//      // Add to head
//      ArrayList<String> expectedHeadList = new ArrayList<>(sampleList);
//      expectedHeadList.add(0, play);
//      edu.cofc.csci230.ArrayList<String> actualHeadList = getList(sampleList);
//      try {
//        actualHeadList.add(0, play);
//      } catch (Exception e) {
//      }
//
//      // Add to tail
//      ArrayList<String> expectedTailList = new ArrayList<>(sampleList);
//      expectedTailList.add(expectedTailList.size(), play);
//      edu.cofc.csci230.ArrayList<String> actualTailList = getList(sampleList);
//      try {
//        actualTailList.add(7, play);
//      } catch (Exception e) {
//      }
////      try {
////        String[] actualMiddleListArr = toArray(actualMiddleList, String.class);
////        String[] actualHeadListArr = toArray(actualHeadList, String.class);
////        String[] actualTailListArr = toArray(actualTailList, String.class);
////        assertAll("Adds",
////            () -> assertArrayEquals("Failed adding item at index 4 (-1.66 pts)",
////                expectedMiddleList.toArray(new String[expectedMiddleList.size()]),
////                actualMiddleListArr),
////            () -> assertArrayEquals("Failed adding item at head (index 0) (-1.67 pts)",
////                expectedHeadList.toArray(new String[expectedHeadList.size()]), actualHeadListArr),
////            () -> assertArrayEquals("Failed adding item at tail (index 7) (-1.67 pts)",
////                expectedTailList.toArray(new String[expectedTailList.size()]), actualTailListArr));
////      } catch (NullPointerException e) {
////        fail("Error comparing lists. Does your list have the right number of nodes? (size is "
////            + String.valueOf(actualMiddleList.size() + " but found less nodes) (-5 pts)"));
////      }
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//
//  }
//
//  @org.junit.jupiter.api.Test
//  void set() {
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Remove")
//  void remove() {
//    try {
//      // Remove from middle
//      ArrayList<String> expectedMiddleList = new ArrayList<>(sampleList);
//      String expectedRemovedMiddle = expectedMiddleList.remove(4);
//      edu.cofc.csci230.ArrayList<String> actualMiddleList = getList(sampleList);
//      String actualRemovedMiddle = actualMiddleList.remove(4);
//
//      // Remove from head
//      ArrayList<String> expectedHeadList = new ArrayList<>(sampleList);
//      String expectedRemovedHead = expectedHeadList.remove(0);
//      edu.cofc.csci230.ArrayList<String> actualHeadList = getList(sampleList);
//      String actualRemovedHead = actualHeadList.remove(0);
//
//      // Remove from tail
//      ArrayList<String> expectedTailList = new ArrayList<>(sampleList);
//      String expectedRemovedTail = expectedTailList.remove(6);
//      edu.cofc.csci230.ArrayList<String> actualTailList = getList(sampleList);
//      String actualRemovedTail = actualTailList.remove(6);
//
////      assertAll("Removes",
////          () -> assertArrayEquals("Failed removing item at index 4 (-1.66 pts)",
////              expectedMiddleList.toArray(new String[expectedMiddleList.size()]),
////              toArray(actualMiddleList, String.class)),
////          () -> assertArrayEquals("Failed removing item at head (index 0) (-1.67 pts)",
////              expectedHeadList.toArray(new String[expectedHeadList.size()]),
////              toArray(actualHeadList, String.class)),
////          () -> Assertions.assertArrayEquals("Failed removing item at tail (index 6) (-1.67 pts)",
////              expectedTailList.toArray(new String[expectedTailList.size()]),
////              toArray(actualTailList, String.class)));
//
//      assertAll("Remove returns the right node",
//          () -> Assertions.assertEquals(expectedRemovedMiddle, actualRemovedMiddle,
//              "Method did not return the right item (-0.84 pts)"),
//          () -> assertEquals(expectedRemovedHead, actualRemovedHead,
//              "Method did not return the right item (-0.83 pts)"),
//          () -> assertEquals(expectedRemovedTail, actualRemovedTail,
//              "Method did not return the right item (-0.83 pts)"));
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Get")
//  void get() {
//    // Should retrieve the right node for every index
//    try {
//      boolean getHead = sampleList.get(0) == testList.get(0);
//      boolean getMiddle = sampleList.get(4) == testList.get(4);
//      boolean getTail = sampleList.get(6) == testList.get(6);
//      assertTrue(getHead && getMiddle && getTail, "Does not retrieve the right node (-5 pts)");
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Clear list")
//  void clear() {
//    try {
//      testList.clear();
//      Executable outOfBounds = new Executable() {
//        @Override
//        public void execute() {
//          testList.get(0);
//        }
//      };
//      assertAll("Cleared list",
//          () -> assertThrows(IndexOutOfBoundsException.class, outOfBounds,
//              "List should be empty (-2.5 pts)"),
//          () -> assertEquals(0, testList.size(), "Size should be zero (-2.5 pts)"));
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Trim to size")
//  void trimToSize() {
//    try {
//      // Trims
//      edu.cofc.csci230.ArrayList<Integer> list = getIntegerList(22);
//      list.trimToSize();
//      boolean actualMatchesExpected = 22 == list.getCapacity();
//      assertTrue(actualMatchesExpected, "Does not trim the array properly (-5 pts)");
//
//      if (actualMatchesExpected) {
//        // Does not trim below minimum capacity
//        edu.cofc.csci230.ArrayList<Integer> smallList = getIntegerList(7);
//        smallList.trimToSize();
//        assertEquals(10, smallList.getCapacity(),
//            "Should not trim beyond the default minimum capacity (-2.5 pts)");
//      }
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  @org.junit.jupiter.api.Test
//  @DisplayName("Ensure capacity")
//  void ensureCapacity() {
//    try {
//      // Has capacity equal to minCapacity param
//      int minCapacity = 200;
//      testList.ensureCapacity(minCapacity);
//      assertEquals(minCapacity, testList.getCapacity(), "Did not ensure capacity (-5 pts)");
//    } catch (Exception e) {
//      fail("(" + e.getClass().getName() + ") " + UNEXPECTED_EXCEPTION);
//    }
//  }
//
//  private <AnyType extends Comparable<AnyType>> AnyType[] toArray(edu.cofc.csci230.ArrayList<AnyType> list, Class<AnyType> c) throws NullPointerException {
//    ArrayList<AnyType> arr = new ArrayList<AnyType>();
//    for(int index = 0; index < list.size(); index++) {
//      arr.add(list.get(index));
//    }
//
//    AnyType[] a = (AnyType[]) Array.newInstance(c, list.size());
//    return arr.toArray(a);
//  }
//
//  private edu.cofc.csci230.ArrayList<Integer> getIntegerList(int length) {
//    edu.cofc.csci230.ArrayList<Integer> list = new edu.cofc.csci230.ArrayList<>();
//    for(int i = 0; i < length; i++)
//      list.add(i);
//    return list;
//  }
//
//  private <AnyType extends Comparable<AnyType>> edu.cofc.csci230.ArrayList<AnyType> getList(ArrayList<AnyType> list) {
//    edu.cofc.csci230.ArrayList<AnyType> clone = new edu.cofc.csci230.ArrayList<AnyType>();
//    for(AnyType item : list) {
//      clone.add(item);
//    }
//
//    return clone;
//  }
//
//  protected abstract class Assertion {
//
//    abstract void checkAssertion();
//  }
//}