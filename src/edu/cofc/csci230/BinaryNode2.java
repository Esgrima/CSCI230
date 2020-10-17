//package edu.cofc.csci230;
//
//public class BinaryNode<Integer extends Comparable<Integer>> {
//
//  protected Integer data;
//  protected BinaryNode<Integer> left;
//  protected BinaryNode<Integer> right;
//  protected BinaryNode<Integer> parent;
//
//  /**
//   *
//   * @param data
//   */
//  public BinaryNode(Integer data, BinaryNode<Integer> left, BinaryNode<Integer> right) {
//    this.data = data;
//    this.left = left;
//    this.right = right;
//  }
//
//  public BinaryNode(Integer data) {
//    this.data = data;
//    this.left = null;
//    this.right = null;
//  }
//
//  public BinaryNode() {
//    this.data = null;
//    this.left = null;
//    this.right = null;
//  }
//
//  /**
//   *
//   * @param left
//   */
//  public void setLeftChild(BinaryNode<Integer> left){
//    this.left = left;
//  }
//
//  /**
//   *
//   * @param right
//   */
//  public void setRightChild(BinaryNode<Integer> right){
//    this.right = right;
//  }
//
//  /**
//   *
//   * @param parent
//   */
//  public void setParent(BinaryNode<Integer> parent){
//    this.parent = parent;
//  }
//
//  /**
//   *
//   * @return left child
//   */
//  public BinaryNode<Integer> getLeft(){
//    return this.left;
//  }
//
//  /**
//   *
//   * @return right child
//   */
//  public BinaryNode<Integer> getRight(){
//    return this.right;
//  }
//
//  /**
//   *
//   * @return parent
//   */
//  public BinaryNode<Integer> getParent(){
//    return this.parent;
//  }
//
//  public boolean isLeaf() {
//    return (this.left == null) && (this.right == null);
//  }
//
//  public boolean isEmpty() {
//
//    return (this.data == null);
//  }
//
//  public String toString()
//  {
//    return this.getData().toString();
//  }
//
//  public Integer getData() {
//    return this.data;
//  }
//
//  public void setData(Integer data) {
//    this.data = data;
//  }
//
//
//}
