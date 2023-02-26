// --== CS400 Project One File Header ==--
// Name: Adam Lewandowski
// CSL Username: alewandowski
// Email: aclewandowski@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

/**
 * Class that uses the methods found in HashtableMap.java and tests that they properly function as
 * intended
 * 
 * @author Adam Lewandowski
 *
 */
public class HashtableMapTests {

  /**
   * Tests that the get() method returns the proper value and throws the correct exception when the
   * value is not located in the table
   * 
   * @return - true if both tests pass, otherwise false
   */
  public static boolean test1() {
    int numPassed = 0;

    HashtableMap testTable = new HashtableMap<>();

    // put key value pair 3, 3 into the table
    // and check to see if get accurately gets
    // the correct key
    testTable.put(3, 3);
    if (testTable.get(3).equals(3)) {
      numPassed++;
    }

    // try getting a key that is not in the table
    // and test for correct exception
    try {
      testTable.get(4);
    } catch (NoSuchElementException a) {
      numPassed++;
    }

    if (numPassed == 2) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * Tests the remove method and makes sure it returns the correct key while also making sure the
   * correct exception is thrown when trying to remove an invalid key
   * 
   * @return - true if all tests pass, otherwise false
   */
  public static boolean test2() {
    HashtableMap testTable = new HashtableMap<>();

    // test to see if remove method returns
    // the correct value
    testTable.put(3, 3);
    if (!testTable.remove(3).equals(3)) {
      return false;
    }

    if (testTable.remove(4) != null) {
      return false;
    }

    return true;
  }

  /**
   * Test method for the size() method in HashtableMap
   * 
   * @return - true if test passes, otherwise false
   */
  public static boolean test3() {
    HashtableMap testTable = new HashtableMap<>();
    testTable.put(4, 5);
    testTable.put(3, 2);
    testTable.put(8, 9);
    testTable.put(7, 10);
    testTable.put(12, 11);

    if (testTable.size() == 5) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Tests whether the clear() method actually removes all the items currently in the list
   * 
   * @return - true if it makes size 0, otherwise false
   */
  public static boolean test4() {
    HashtableMap testTable = new HashtableMap<>();
    testTable.put(1, 2);
    testTable.put(2, 3);
    testTable.put(4, 5);

    testTable.clear();

    if (testTable.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Tests the changing of the hashtable when the array must be resized and rehashing must occur
   * 
   * @return - true if tests pass, otherwise false
   */
  public static boolean test5() {
    HashtableMap testTable = new HashtableMap<>(8);
    testTable.put(4, 5);
    testTable.put(3, 2);
    testTable.put(8, 9);
    testTable.put(7, 10);
    testTable.put(12, 11);
    testTable.put(14, 15);
    testTable.put(16, 17);
    testTable.put(17, 18);

    if (testTable.tableLength() != 16) {
      return false;
    }
    if (testTable.size() != 8) {
      return false;
    }

    return true;
  }

  /**
   * Runs all of the above tests
   * 
   * @return - true only if every test passes
   */
  public static boolean runAllTests() {
    if (test1()) {
      System.out.println("test1 passed");
      if (test2()) {
        System.out.println("test2 passed");
        if (test3()) {
          System.out.println("test3 passed");
          if (test4()) {
            System.out.println("test4 passed");
            if (test5()) {
              System.out.println("test5 passed");
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  /**
   * Prints out the messages found in runAllTests to help see if they passed their tests
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());

  }

}
