package edu.cofc.csci230;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * Undirected and unweighted graph data structure.
 *
 *
 * CSCI 230: Data Structures and Algorithms
 *
 * Fall 2018
 */
public class UndirectedGraph<AnyType extends Comparable<AnyType>> {

  /**
   * Adjacency list data structure. Used in conjunction with adjacent_vertices in each Vertex
   * class.
   *
   * Note: Each vertex in the adjacency list must be unique, i.e. the list cannot contain two
   * vertices that have the same value.
   */
  private List<Vertex<AnyType>> adjacency_list = null;

  /**
   * Constructor
   */
  public UndirectedGraph() {

    adjacency_list = new ArrayList<Vertex<AnyType>>();

  } // end constructor


  /**
   * See supplemental course textbook for definition of connected graph (page 31)
   *
   * This method returns TRUE if the graph is connected, or FALSE if it is not.
   *
   * Furthermore, if the adjacency list does not have any vertices, i.e. the graph is empty, the
   * method will return false.
   *
   * ----------------- Note: -----------------
   *
   * You may add additional methods or private instance variables to this class to support your
   * solution (i.e. if it is recursive). However, you MUST use the provided adjacency list and the
   * vertex class, and you MAY NOT create an additional class. If in doubt about these restrictions
   * -> you must ask!
   *
   * @author Rex Ferrer
   */
  public Boolean isConnected() {

    // ----------------------------
    // DONE:Add your code here
    // ----------------------------
    if (this.adjacency_list.isEmpty()) {
      return false;
    }

    this.depthFirstSearch(this.adjacency_list.get(0).getValue());

    for (int i = 0; i < this.adjacency_list.size(); i++) {
      Vertex<AnyType> curr = this.adjacency_list.get(i);
      //Checks if the vertex has no adjacent vertices
      if (!curr.hasBeenVisited()) {
        return false;
      }
    }
    return true; // this is only here for the code to compile, you must change!
  } // end isConnected() method

  /**
   * Create a formated string that lists all the graph cycles
   *
   * For example, if the adjacency list is:
   *
   * Vertex (1): [ 2, 3, 4 ] Vertex (2): [ 1, 4 ] Vertex (3): [ 1, 4 ] Vertex (4): [ 1, 2, 3 ]
   *
   * then this method would return the string
   *
   * cycle (1): [ 1, 2, 4 ] cycle (2): [ 1, 3, 4 ] cycle (3): [ 1, 2, 3, 4 ]
   *
   * As shown above, the format of the string MUST be:
   *
   * cycle( <cycle_number> ): [ <comma delimited sequence of vertex numbers> ]\n
   *
   *
   * ----------------- Notes: ----------------- 1) Your cycle results must be formated in ascending
   * vertex value order, e.g. below would NOT BE correct cycle (1): [ 2, 1, 4 ]
   *
   * 2) No duplicate cycles are included, e.g. this result is NOT correct cycle (1): [ 1, 2, 4 ]
   * cycle (2): [ 1, 2, 4 ]
   *
   * 3) The order of the cycles must be sorted (ascending order) by the number of vertices that form
   * the cycle. i.e., cycles formed by three vertices first, then cycles formed by four vertices
   * second, etc. For example, the result below is NOT correct. cycle (1): [ 1, 2, 4 ] cycle (2): [
   * 1, 2, 3, 4 ] cycle (3): [ 1, 3, 4 ] Lastly, cycles that have the same number of vertices are
   * ordered by the sum of the vertex values, with the minimum vertex sum appearing first. For
   * example, the result shown below is NOT correct because 1+3+4 > 1+2+4 cycle (1): [ 1, 3, 4 ]
   * cycle (2): [ 1, 2, 4 ] cycle (3): [ 1, 2, 3, 4 ]
   *
   * 4) You may add additional methods or private instance variables to this class to support your
   * solution (i.e. if it is recursive). However, you MUST use the provided adjacency list and the
   * vertex class, and you MAY NOT create an additional class.
   *
   * 5) If there are no vertices in the adjacency list OR the graph is not connected the method
   * would simply return the following string values exactly (including case): - no vertices in the
   * adjacency list (i.e. graph is empty) then return the string "empty graph". - the graph is not
   * connected then return the string "not connected".
   *
   * @author Rex Ferrer
   */
  public String findAllCycles() {

    // ----------------------------
    // DONE:Add your code here
    // ----------------------------
    if (this.adjacency_list.isEmpty()) {
      return "empty graph";
    }

    if (!this.isConnected()) {
      return "not connected";
    }

    // n > 3 for cycles to be present
    if (this.adjacency_list.size() < 3) {
      return "Graphs of less than 3 vertices can't have cycles";
    }

    TreeSet<TreeSet<Vertex<AnyType>>> cycles = new TreeSet<>(cycleOrder);
//    graphVisitReset();

    for (int i = 0; i < this.adjacency_list.size(); i++) {
      graphVisitReset();
      //Reset the cycle at each new start_vertex
      java.util.Stack<Vertex<AnyType>> cycle = new java.util.Stack<>();

      Vertex<AnyType> start_vertex = this.adjacency_list.get(i);

      // Order of visits
      int count = 0;

      findAllCycles(count, start_vertex, start_vertex, cycles, cycle);
      graphVisitReset();

      //Reset visit counts to -1

    }

    return printCycles(cycles);
  } // end findAllCycles()

  /**
   * @author Rex Ferrer
   */
  private void findAllCycles(int count, Vertex<AnyType> start_vertex,
      Vertex<AnyType> current_vertex,
      TreeSet<TreeSet<Vertex<AnyType>>> cycles, java.util.Stack<Vertex<AnyType>> cycle) {

    //Skips over vertices with only one adjacent vertex
    if (isLeaf(current_vertex)) {
      current_vertex.setVisited(++count);
      return;
    }

//    System.out.printf("Current: %s %d\n", current_vertex, current_vertex.getVisited());
//    System.out.printf("Cycle: %s\n", cycle);
    if (current_vertex.hasBeenVisited()) {

      // Checks for cycles beginning at the start_vertex
      if (cycle.contains(current_vertex) && current_vertex.compareTo(start_vertex) == 0) {

        // Ensures the cycle is length 3 or higher
        if (cycle.size() > 2) {

          //Current cycle gets converted to TreeSet and added to cycles
          TreeSet<Vertex<AnyType>> valid_cycle = new TreeSet<>(cycle);
          cycles.add(valid_cycle);
        }

        //Checks for cycles not beginning at the start_vertex
      } else if (cycle.contains(current_vertex) && current_vertex.compareTo(start_vertex) != 0) {

        // Ensures the cycle is length 3 or higher
        if (cycle.subList(cycle.indexOf(current_vertex), cycle.size()).size() > 2) {
          //Current cycle gets converted to TreeSet and added to cycles
          TreeSet<Vertex<AnyType>> valid_cycle = new TreeSet<>(
              cycle.subList(cycle.indexOf(current_vertex), cycle.size()));
          cycles.add(valid_cycle);
        }
      }
      //Vertex is added to cycle stack
    } else {

      cycle.push(current_vertex);
      current_vertex.setVisited(++count);

      //Iterate through the current vertex adjacent vertices
      for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {
        findAllCycles(count, start_vertex, current_vertex.getAdjacentVertex(i), cycles, cycle);
      }

      if (!cycle.isEmpty()) {
        cycle.pop();
      }
    }
  }


  /**
   * Add an edge between two vertices
   */
  public Boolean addEdge(AnyType vertex_value_A, AnyType vertex_value_B) throws VertexException {

    // ------------------------------------
    // Basic steps:
    // ------------------------------------
    // 1) For Vertex A and B, check to see if the vertex exits in the
    //    adjacency_list. If not, then add new vertex (with given value)
    //    to the adjacency_list.
    // 2) In the Vertex class, if Vertex B is in Vertex A's
    //    adjacent_vertices and vice versa (i.e. an edge exists). If an
    //    edge already exists, then return false, otherwise goto step 3.
    // 3) Add Vertex B to Vertex A's adjacent_vertices and vice versa.
    //    Lastly, return true indicating the edge was successfully added.

    Vertex<AnyType> vertexA = new Vertex<AnyType>(vertex_value_A);
    Vertex<AnyType> vertexB = new Vertex<AnyType>(vertex_value_B);

    int vertexA_exists = -1;
    int vertexB_exists = -1;

    // Step 1
    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(vertexA) == 0) {
        vertexA_exists = i;
      }

      if (adjacency_list.get(i).compareTo(vertexB) == 0) {
        vertexB_exists = i;
      }

    }

    if (vertexA_exists == -1) {
      adjacency_list.add(vertexA);
    } else {
      vertexA = adjacency_list.get(vertexA_exists);
    }

    if (vertexB_exists == -1) {
      adjacency_list.add(vertexB);
    } else {
      vertexB = adjacency_list.get(vertexB_exists);
    }

    // Step 2

    if (vertexA.hasAdjacentVertex(vertexB) && vertexB.hasAdjacentVertex(vertexA)) {
      return false;
    } else {

      vertexA.addAdjacentVertex(vertexB);
      vertexB.addAdjacentVertex(vertexA);

    }

    return true;

  } // end addEdge() method


  /**
   * Remove the edge that connects two vertices
   */
  public Boolean removeEdge(AnyType vertex_value_A, AnyType vertex_value_B) throws VertexException {

    // ------------------------------------
    // Basic steps:
    // ------------------------------------
    // 1) For each vertex, see if the vertex exists in
    //    the adjacency_list. If not, return false that indicates the
    //    edge does not exist. Otherwise goto step 2.
    // 2) In Vertex class, see if Vertex B is in Vertex A's
    //    adjacent_vertices and vice versa (i.e. an edge exists).
    //    If the edge does not exist return false, otherwise goto
    //    step 3.
    // 3) In the Vertex class, remove Vertex B from Vertex A's
    //    adjacent_vertices and vice versa, and then goto step 4.
    //    Does not exist and return false, otherwise proceed to step 4.
    // 4) If number of adjacent vertices for Vertex A is zero, then
    //    remove from the adjacency_list. Likewise, if the number of
    //    adjacent vertices for Vertex B is zero, then remove from
    //    adjacency_list. Lastly, return true indicating the edge was
    //    successfully removed.

    Vertex<AnyType> vertexA = new Vertex<AnyType>(vertex_value_A);
    Vertex<AnyType> vertexB = new Vertex<AnyType>(vertex_value_B);

    int vertexA_exists = -1;
    int vertexB_exists = -1;

    // -----------------------------------------------------------------
    // Step 1
    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(vertexA) == 0) {
        vertexA_exists = i;
      }

      if (adjacency_list.get(i).compareTo(vertexB) == 0) {
        vertexB_exists = i;
      }

    }

    // -----------------------------------------------------------------
    // Step 2
    if (vertexA_exists == 1 || vertexB_exists == -1) {
      return false;
    }

    vertexA = adjacency_list.get(vertexA_exists);
    vertexB = adjacency_list.get(vertexB_exists);

    // -----------------------------------------------------------------
    // Step 3
    if (!vertexA.hasAdjacentVertex(vertexB) || !vertexB.hasAdjacentVertex(vertexA)) {
      return false;
    } else {

      vertexA.removeAdjacentVertex(vertexB);
      vertexB.removeAdjacentVertex(vertexA);

    }

    // -----------------------------------------------------------------
    // Step 4
    if (vertexA.numberOfAdjacentVertices() == 0) {
      adjacency_list.remove(vertexA_exists);
    }

    if (vertexB.numberOfAdjacentVertices() == 0) {
      adjacency_list.remove(vertexB_exists);
    }

    return true;

  } // end removeEdge() method

  /**
   * Depth first search (DFS) using stack data structure. Specifically, the ConstantTimeStack
   * class.
   *
   * Return a String that shows when each vertex was visited during the DFS. String format: <Vertex
   * Value>:<Visited Count>\n
   *
   * Notes: (1) Mark each vertex as not visited before the the search begins. (2) If the
   * start_vertex value does not exist in the undirected graph throw a new VertexException. (3)
   * Vertices are pushed onto the Stack in the same order they were added to the adjacent_vertices
   */
  public String depthFirstSearch(AnyType start_vertex) throws VertexException {

    StringBuffer buffer = new StringBuffer();

    Vertex<AnyType> startVertex = new Vertex<AnyType>(start_vertex);

    int vertex_exists = -1;

    // Determines if the start vertex exists
    for (int i = 0; i < this.adjacency_list.size(); i++) {

      if (this.adjacency_list.get(i).compareTo(startVertex) == 0) {
        vertex_exists = i;
      }

    }

    if (vertex_exists == -1) {
      throw new VertexException(String.format("Vertex %s does not exist!", startVertex));
    }

    this.graphVisitReset();

    int count = 0;

    startVertex = this.adjacency_list.get(vertex_exists);

    Stack<Vertex<AnyType>> stack = new ConstantTimeStack<Vertex<AnyType>>();

    stack.push(startVertex);

    try {

      while (true) {

        Vertex<AnyType> current_vertex = stack.peek();

        // If the curr vertex has not been visited
        if (!current_vertex.hasBeenVisited()) {

          current_vertex.setVisited(++count);

          //Push all adjacent vertices to the stack
          for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {

            stack.push(current_vertex.getAdjacentVertex(i));

          }
          // Pop the vertex if it's been visited
        } else {
          stack.pop();
        }

      }

      // DFS is done
    } catch (EmptyStackException emptyStack) {

      //
      for (int i = 0; i < this.adjacency_list.size(); i++) {
        buffer.append(String.format("%s:%d\n", this.adjacency_list.get(i),
            this.adjacency_list.get(i).getVisited()));
      }
    }
    return buffer.toString();
  } // end depthFirstSearch() method

  /**
   * @author Rex Ferrer
   */
  public void recursiveDFS() {
    this.graphVisitReset();

    int count = 0;

    for (int i = 0; i < this.adjacency_list.size(); i++) {
      if (!this.adjacency_list.get(i).hasBeenVisited()) {
        rDFS(this.adjacency_list.get(i), count);
      }
    }

    this.printSearchPath();

    this.graphVisitReset();
  }

  /**
   * @author Rex Ferrer
   */
  private void rDFS(Vertex<AnyType> vertex, int count) {
    vertex.setVisited(++count);
//    System.out.printf("%s : %d \n", vertex.toString(), vertex.getVisited());

    for (int i = 0; i < vertex.numberOfAdjacentVertices(); i++) {
      if (!vertex.getAdjacentVertex(i).hasBeenVisited()) {
        rDFS(vertex.getAdjacentVertex(i), count);
      }
    }
  }


  /**
   * Breadth first search (BFS) using queue data structure. Specifically, the ConstantTimeQueue
   * class
   *
   * Return a String that shows when each vertex was visited during the BFS. String format: <Vertex
   * Value>:<Visited Count>\n
   *
   * Notes: (1) Mark each vertex as not visited before the search begins. (2) If the start_vertex
   * value does not exist throw a new VertexException. (4) Vertices are added to the Queue in the
   * same order they were added to the adjacent_vertices
   */
  public String breadthFirstSearch(AnyType start_vertex) throws VertexException {

    StringBuffer buffer = new StringBuffer();

    Vertex<AnyType> startVertex = new Vertex<AnyType>(start_vertex);

    int vertex_exists = -1;

    for (int i = 0; i < adjacency_list.size(); i++) {

      if (adjacency_list.get(i).compareTo(startVertex) == 0) {
        vertex_exists = i;
      }

    }

    if (vertex_exists == -1) {
      throw new VertexException(String.format("Vertex %s does not exist!", startVertex));
    }

    this.graphVisitReset();

    int count = 0;

    startVertex = this.adjacency_list.get(vertex_exists);

    Queue<Vertex<AnyType>> queue = new ConstantTimeQueue<Vertex<AnyType>>();

    queue.add(startVertex);

    try {

      while (true) {

        Vertex<AnyType> current_vertex = queue.remove();

        if (!current_vertex.hasBeenVisited()) {

          current_vertex.setVisited(++count);

          for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {

            queue.add(current_vertex.getAdjacentVertex(i));

          }

        }

      }

    } catch (NoSuchElementException emptyQueue) {

      for (int i = 0; i < adjacency_list.size(); i++) {

        buffer.append(String.format("%s:%d\n", adjacency_list.get(i),
            adjacency_list.get(i).getVisited()));

      }

    }

    return buffer.toString();

  } // end breadthFirstSearch() method

  /**
   * @author Rex Ferrer
   */
  public void iterativeBFS() {
    this.graphVisitReset();
    int count = 0;
    LinkedList<Vertex<AnyType>> queue = new LinkedList<>();

    Vertex<AnyType> current = this.adjacency_list.get(0);
    current.setVisited(++count);
    queue.add(current);

    while (!queue.isEmpty()) {
      current = queue.pollFirst();

      for (int i = 0; current != null && i < current.numberOfAdjacentVertices(); i++) {
        Vertex<AnyType> adj_v = current.getAdjacentVertex(i);

        if (!adj_v.hasBeenVisited()) {
          queue.add(adj_v);
          adj_v.setVisited(++count);
        }

      }
    }
    this.printSearchPath();

    this.graphVisitReset();
  }

  /**
   * Recursive breadth first search that starts at the adjacency list's first vertex
   * @author Rex Ferrer
   */
  public void recursiveBFS() {
    this.graphVisitReset();
    LinkedList<Vertex<AnyType>> queue = new LinkedList<>();
    int count = 0;

    for (int i = 0; i < this.adjacency_list.size(); i++) {

      if (!this.adjacency_list.get(i).hasBeenVisited()) {
        Vertex<AnyType> current = this.adjacency_list.get(i);
        current.setVisited(++count);
        queue.add(current);
        rBFS(queue, count);
      }
    }

    this.printSearchPath();
    this.graphVisitReset();
  }

  /**
   * Helper function for recursiveBFS
   *
   * @param queue Enables level traversal
   * @param count Tracks order of traversal
   * @author Rex Ferrer
   */
  private void rBFS(LinkedList<Vertex<AnyType>> queue, int count) {
    if (queue.isEmpty()) {
      return;
    }

    Vertex<AnyType> vertex = queue.pollFirst();

    for (int i = 0; vertex != null && i < vertex.numberOfAdjacentVertices(); i++) {
      Vertex<AnyType> adj_v = vertex.getAdjacentVertex(i);
      if (!adj_v.hasBeenVisited()) {
        adj_v.setVisited(++count);
        queue.add(adj_v);
      }
    }

    rBFS(queue, count);
  }

  /**
   *
   */
  public void clear() {

    adjacency_list.clear();

  } // end clear()

  /**
   * Resets the vertice's VISITED field
   * @author Rex Ferrer
   */
  private void graphVisitReset() {
    //Marks each vertex as not visited
    for (int i = 0; i < this.adjacency_list.size(); i++) {
      this.adjacency_list.get(i).setVisited(Vertex.NOT_VISITED);
    }
  } // end graphVisitReset()


  /**
   * convert adjacency list to string
   */
  public String toString() {

    StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < this.adjacency_list.size(); i++) {

      buffer.append(this.adjacency_list.get(i).printVertex());

    }

    return buffer.toString();

  } // end toString() method

  /**
   * Comparator that IntelliJ suggested Comparator that will enable cycles TreeSet object to sort
   * it's cycle TreeSet objects by length and/or sum of the vertices
   *
   * @author Rex Ferrer
   */
  private Comparator<TreeSet<Vertex<AnyType>>> cycleOrder = (cycle1, cycle2) -> {
    if (cycle1.size() > cycle2.size()) {
      return 1;
    } else if (cycle1.size() < cycle2.size()) {
      return -1;
    } else {
      Integer sum1 = 0;
      Integer sum2 = 0;

      for (Vertex<AnyType> vertex : cycle1) {
        sum1 += (Integer) vertex.getValue();
      }

      for (Vertex<AnyType> vertex : cycle2) {
        sum2 += (Integer) vertex.getValue();
      }

      return Integer.compare(sum1, sum2);
    }
  };

//  /** Original Comparator
//   * Comparator that will enable cycles TreeSet object to sort it's cycle TreeSet objects by length
//   * and/or sum of the vertices
//   */
//  Comparator<TreeSet<Vertex<AnyType>>> cycleOrder = new Comparator<TreeSet<Vertex<AnyType>>>() {
//    @Override
//    public int compare(TreeSet<Vertex<AnyType>> cycle1, TreeSet<Vertex<AnyType>> cycle2) {
//      if (cycle1.size() > cycle2.size()) {
//        return 1;
//      } else if (cycle1.size() < cycle2.size()) {
//        return -1;
//      } else {
//        Integer sum1 = 0;
//        Integer sum2 = 0;
//
//        for (Vertex<AnyType> vertex : cycle1) {
//          sum1 += (Integer) vertex.getValue();
//        }
//
//        for (Vertex<AnyType> vertex : cycle2) {
//          sum2 += (Integer) vertex.getValue();
//        }
//
//        return Integer.compare(sum1, sum2);
//      }
//    }
//  };

  /**
   * @return [ <comma delimited sequence of vertex numbers> ]\n
   * @author Rex Ferrer
   */
  private String printCycle(TreeSet<Vertex<AnyType>> cycle) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("[ ");

    int index = 0;
    for (Vertex<AnyType> vertex : cycle) {
      if (index == cycle.size() - 1) {
        buffer.append(String.format("%s", vertex));
      } else {
        buffer.append(String.format("%s, ", vertex));
      }
      index++;
    }
    buffer.append(" ]\n");

////    Test
//    System.out.println(buffer);

    return buffer.toString();
  }


  /**
   * @return cycle(< cycle_number >): [ <comma delimited sequence of vertex numbers> ]\n
   * @author Rex Ferrer
   */
  public String printCycles(TreeSet<TreeSet<Vertex<AnyType>>> cycles) {
    StringBuilder cycle_builder = new StringBuilder();
    int cycle_count = 1;
    for (TreeSet<Vertex<AnyType>> cycle : cycles) {
      cycle_builder.append(String.format("cycle (%d): %s\n", cycle_count, printCycle(cycle)));

      cycle_count++;
    }

    return cycle_builder.toString();
  }

  /**
   * Prints path order
   * @author Rex Ferrer
   */
  private void printSearchPath() {
    for (int i = 0; i < this.adjacency_list.size(); i++) {
      Vertex<AnyType> curr = this.adjacency_list.get(i);
      System.out.printf("%s : %d \n", curr.toString(), curr.getVisited());
    }
  }

  /**
   *
   * @author Rex Ferrer
   */
  private boolean isLeaf(Vertex<AnyType> vertex) {
    return vertex.numberOfAdjacentVertices() <= 1;
  }


  /**
   *
   * @param args
   * @author Rex Ferrer
   */
  public static void main(String[] args) {

//    System.out.println("Empty Graph Case");
//    UndirectedGraph<Integer> graph1 = new UndirectedGraph<Integer>();
//
//    System.out.println(graph1);
//    System.out.println(graph1.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("Not Connected Graph Case");
//    graph1 = new UndirectedGraph<>();
//
//    graph1.addEdge(1, 2);
//    graph1.addEdge(3, 4);
//    graph1.addEdge(5, 6);
//
//    System.out.println(graph1);
//    System.out.println(graph1.findAllCycles());
//
//    graph1.clear();
//    System.out.println("=============================================");
//
//    System.out.println("Sample Case");
//    graph1 = new UndirectedGraph<>();
//
//    graph1.addEdge(1, 2);
//    graph1.addEdge(1, 3);
//    graph1.addEdge(1, 4);
//    graph1.addEdge(2, 4);
//    graph1.addEdge(3, 4);
//
//    System.out.println(graph1);
//    System.out.println(graph1.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("Sample Case + Leaf");
//
//    graph1.addEdge(4, 5);
//
//    System.out.println(graph1);
//    System.out.println(graph1.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("Line Case of 2 Vertices");
//    UndirectedGraph<Integer> line = new UndirectedGraph<Integer>();
//    line.addEdge(1, 2);
//
//    System.out.println(line);
//    System.out.println(line.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("C_4 Case");
//    UndirectedGraph<Integer> c_4 = new UndirectedGraph<Integer>();
//    c_4.addEdge(1, 2);
//    c_4.addEdge(2, 3);
//    c_4.addEdge(3, 4);
//    c_4.addEdge(4, 1);
//
//    System.out.println(c_4);
//    System.out.println(c_4.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("K_4 Case");
//    UndirectedGraph<Integer> k_4 = new UndirectedGraph<Integer>();
//    k_4.addEdge(1, 2);
//    k_4.addEdge(1, 3);
//    k_4.addEdge(1, 4);
//    k_4.addEdge(2, 3);
//    k_4.addEdge(2, 4);
//    k_4.addEdge(3, 4);
//
//    System.out.println(k_4);
//    System.out.println(k_4.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("W_4 Case");
//    UndirectedGraph<Integer> w_4 = new UndirectedGraph<Integer>();
//    w_4.addEdge(1, 2);
//    w_4.addEdge(1, 3);
//    w_4.addEdge(1, 4);
//    w_4.addEdge(2, 3);
//    w_4.addEdge(2, 5);
//    w_4.addEdge(3, 4);
//    w_4.addEdge(3, 5);
//    w_4.addEdge(4, 5);
//
//    System.out.println(w_4);
//    System.out.println(w_4.findAllCycles());
//    System.out.println("=============================================");
//
//    System.out.println("K3_3 Case");
//    UndirectedGraph<Integer> k3_3 = new UndirectedGraph<Integer>();
//    k3_3.addEdge(1, 4);
//    k3_3.addEdge(1, 5);
//    k3_3.addEdge(1, 6);
//    k3_3.addEdge(2, 4);
//    k3_3.addEdge(2, 5);
//    k3_3.addEdge(2, 6);
//    k3_3.addEdge(3, 4);
//    k3_3.addEdge(3, 5);
//    k3_3.addEdge(3, 6);
//
//    System.out.println(k3_3);
//    System.out.println(k3_3.findAllCycles());
    System.out.println("Sample Case");
    UndirectedGraph<Integer> graph1 = new UndirectedGraph<>();

    graph1.addEdge(1, 2);
    graph1.addEdge(1, 3);
    graph1.addEdge(1, 4);
    graph1.addEdge(2, 4);
    graph1.addEdge(3, 4);

    System.out.printf("DFS\n%s\n", graph1.depthFirstSearch(1));
    graph1.recursiveDFS();
    System.out.printf("BFS\n%s\n", graph1.breadthFirstSearch(1));
    graph1.iterativeBFS();
    graph1.recursiveBFS();

  } // end main() method

} // end UndirectedGraph class definition