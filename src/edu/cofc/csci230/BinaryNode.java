package edu.cofc.csci230;


/**
 * Binary node used in binary tree / binary search tree coding assignment.
 *
 * @author CSCI 230: Data Structures and Algorithms Fall 2018
 */
public class BinaryNode<AnyType> {

  // instance variables
  private AnyType element;
  private BinaryNode<AnyType> left = null;
  private BinaryNode<AnyType> right = null;
  private BinaryNode<AnyType> parent = null;

  /**
   * Constructor with one argument. The argument sets the element value stored in the binary node
   */
  public BinaryNode(AnyType element) {

    this.element = element;

  } // end constructor

  /**
   * Method used to set the parent node.
   */
  public void setParent(BinaryNode<AnyType> parent) {

    this.parent = parent;

  } // end setParent() method

  /**
   * Method used to get the parent node.
   */
  public BinaryNode<AnyType> getParent() {

    return parent;

  } // end getParent() method

  /**
   * Method used to set the right child node.
   */
  public void setRight(BinaryNode<AnyType> right) {

    this.right = right;

  } // end setRight() method

  /**
   * Method used to get the right child node.
   */
  public BinaryNode<AnyType> getRight() {

    return right;

  } // end getRight() method

  /**
   * Method used to set the left child node.
   */
  public void setLeft(BinaryNode<AnyType> left) {

    this.left = left;

  } // end setLeft() method

  /**
   * Method used to get the left child node.
   */
  public BinaryNode<AnyType> getLeft() {

    return left;

  } // end getLeft() method


  /**
   * Get the element value stored in the binary node.
   */
  public AnyType getElement() {

    return element;

  } // end getElement() method

  /**
   * Set the element value stored in the binary node.
   */
  public void setElement(AnyType element) {

    this.element = element;

  } // end setElement() method

  /**
   *Converts the element to string format.
   */
  public String toString() {

    return getElement().toString();

  } // end toString();

} // end BinaryNode class definition
