//package edu.cofc.csci230;
//
///**
// * Binary Search Tree Print Class
// * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
// *
// * Original code has been modified for generic compatability.
// * @author Michal Kreuzman
// * @since 2011-02-11
// */
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class BSTPrinter {
//    public static <AnyType extends Comparable<?>> void printNode(BinaryNode<AnyType> root) {
//      int maxLevel = BSTPrinter.maxLevel(root);
//
//      printNodeInternal(Collections.singletonList(root), 1, maxLevel);
//    }
//
//    private static <AnyType extends Comparable<?>> void printNodeInternal(List<BinaryNode<AnyType>> nodes, int level, int maxLevel) {
//      if (nodes.isEmpty() || BSTPrinter.isAllElementsNull(nodes))
//        return;
//
//      int floor = maxLevel - level;
//      int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
//      int firstSpaces = (int) Math.pow(2, (floor)) - 1;
//      int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
//
//      BSTPrinter.printWhitespaces(firstSpaces);
//
//      List<BinaryNode<AnyType>> newNodes = new ArrayList<BinaryNode<AnyType>>();
//      for (BinaryNode<AnyType> node : nodes) {
//        if (node != null) {
//          System.out.print(node.getElement());
//          newNodes.add(node.getLeft());
//          newNodes.add(node.getRight());
//        } else {
//          newNodes.add(null);
//          newNodes.add(null);
//          System.out.print(" ");
//        }
//
//        BSTPrinter.printWhitespaces(betweenSpaces);
//      }
//      System.out.println("");
//
//      for (int i = 1; i <= endgeLines; i++) {
//        for (int j = 0; j < nodes.size(); j++) {
//          BSTPrinter.printWhitespaces(firstSpaces - i);
//          if (nodes.get(j) == null) {
//            BSTPrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
//            continue;
//          }
//
//          if (nodes.get(j).getLeft() != null)
//            System.out.print("/");
//          else
//            BSTPrinter.printWhitespaces(1);
//
//          BSTPrinter.printWhitespaces(i + i - 1);
//
//          if (nodes.get(j).getRight() != null)
//            System.out.print("\\");
//          else
//            BSTPrinter.printWhitespaces(1);
//
//          BSTPrinter.printWhitespaces(endgeLines + endgeLines - i);
//        }
//
//        System.out.println("");
//      }
//
//      printNodeInternal(newNodes, level + 1, maxLevel);
//    }
//
//    private static void printWhitespaces(int count) {
//      for (int i = 0; i < count; i++)
//        System.out.print(" ");
//    }
//
//    private static <AnyType extends Comparable<?>> int maxLevel(BinaryNode<AnyType> node) {
//      if (node == null)
//        return 0;
//
//      return Math.max(BSTPrinter.maxLevel(node.getLeft()), BSTPrinter.maxLevel(node.getRight())) + 1;
//    }
//
//    private static <AnyType> boolean isAllElementsNull(List<AnyType> list) {
//      for (Object object : list) {
//        if (object != null)
//          return false;
//      }
//      return true;
//    }
//}
