//package edu.cofc.csci230;
//
//public class BST {
//  private BinaryNode<Integer> root;
////
////  public BST(Integer data){
////    root = new BinaryNode<>(data);
////  }
////
////  public BST(Integer data, BinaryNode<Integer> left, BinaryNode<Integer> right){
////    root = new BinaryNode<>(data, left, right);
////  }
//
//  public static BinaryNode<Integer> insert(BinaryNode<Integer> root, Integer data){
//    if (root == null) {
//      return new BinaryNode<>(data);
//    } else {
//
//      if (data <= root.getData()) {
//        root.setLeftChild(insert(root.getLeft(), data));
//      } else {
//        root.setRightChild(insert(root.getRight(), data));
//      }
//      return root;
//    }
//  }
//
//  public static Integer getMax(BinaryNode<Integer> root){
//    if (root.getRight() != null) {
//        return getMax(root.getRight());
//    }
//    return root.getData();
//  }
//
//  public static Integer getMin(BinaryNode<Integer> root){
//    if (root.getLeft() != null) {
//      return getMin(root.getLeft());
//    }
//    return root.getData();
//  }
//
//  public static void preOrder(BinaryNode<Integer> root){
//    if (root == null){
//      return;
//    }
//    System.out.printf("%d --", root.getData());
//    preOrder(root.getLeft());
//    preOrder(root.getRight());
//  }
//  public static void inOrder(BinaryNode<Integer> root){
//    if (root == null){
//      return;
//    }
//    inOrder(root.getLeft());
//    System.out.printf("%d --", root.getData());
//    inOrder(root.getRight());
//  }
//
//
//  public static void postOrder(BinaryNode<Integer> root){
//    if (root == null){
//      return;
//    }
//    postOrder(root.getLeft());
//    postOrder(root.getRight());
//    System.out.printf("%d --", root.getData());
//  }
//
//  public static boolean searchTF(BinaryNode<Integer> root, Integer value){
//    if (root == null){
//      return false;
//    } else {
//      if (root.getData().equals(value)){
//        return true;
//      } else if (value < root.getData()){
//        return searchTF(root.getLeft(), value);
//      } else if (value > root.getData()){
//        return searchTF(root.getRight(), value);
//      }
//    }
//    return false;
//  }
//
//  public static BinaryNode<Integer> search(BinaryNode<Integer> root, Integer value){
//    if(root == null || (root.getData() == value)){
//      return root;
//    }
//    if (root.getData() <= value){
//      return search(root.getLeft(), value);
//    }
//    return search(root.getRight(), value);
//  }
//
//  public static void main(String[] args){
//
//    BinaryNode<Integer> root = new BinaryNode<Integer>(8);
//    insert(root,10);
//    insert(root,20);
//    insert(root,21);
//    insert(root,8);
//    insert(root,6);
//    insert(root,16);
//    insert(root,23);
//    insert(root,2);
//    insert(root, -200);
//
//    inOrder(root);
//    System.out.println();
//    preOrder(root);
//    System.out.println();
//    postOrder(root);
//    System.out.println();
//
//    System.out.printf("Max: %d\n", getMax(root));
//    System.out.printf("Min: %d\n", getMin(root));
//
//    System.out.printf("Expected: False, Got: %b\n", searchTF(root,1000));
//    System.out.printf("Expected: True, Got: %b\n", searchTF(root, 16));
//    System.out.printf("Expected: 10, Got: %d\n", search(root, 10));
//    System.out.printf("Expected: 20, Got: %d\n", search(root, 20));
//    System.out.printf("Expected: 21, Got: %d\n", search(root, 21));
////    System.out.printf("Expected: True, Got: %b\n", search(root, 8));
////    System.out.printf("Expected: True, Got: %b\n", search(root, 6));
////    System.out.printf("Expected: True, Got: %b\n", search(root, 23));
////    System.out.printf("Expected: True, Got: %b\n", search(root, -200));
////    System.out.printf("Expected: True, Got: %b\n", search(root, 2));
//
//  }
//
//}
