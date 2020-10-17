package edu.cofc.csci230;

public class EmptyHeapException extends Exception {

  /**
   * Exception used in Heap Class.
   *
   * This exception is thrown when an operation is applied to an heap that is empty.
   *
   * @author Rex Ferrer
   */

  public EmptyHeapException() {

    super("Empty Heap Exception");

  }
}
