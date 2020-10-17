//public String findAllCycles() {
//
//    // ----------------------------
//    // Add your code here
//    // ----------------------------
//    if (this.adjacency_list.isEmpty()) {
//    return "empty graph";
//    }
//
//    if (!this.isConnected()) {
//    return "not connected";
//    }
//
//    String cycles_str;
//    Vertex<AnyType> start_vertex = this.adjacency_list.get(0);
//    TreeSet<TreeSet<Vertex<AnyType>>> cycles = new TreeSet<TreeSet<Vertex<AnyType>>>(cycleOrder);
//
//    int count = 0;
//
//    Stack<Vertex<AnyType>> stack = new ConstantTimeStack<Vertex<AnyType>>();
//
//    stack.push(start_vertex);
//    TreeSet<Vertex<AnyType>> cycle = new TreeSet<>();
//    try {
//    int cycle_count = 1;
//
//    //Keeps track of the start vertex of a cycle
//    Vertex<AnyType> cycle_start = null;
//    while (true) {
//
//    Vertex<AnyType> current_vertex = stack.peek();
//
//    // If the curr vertex has not been visited
//    if (!current_vertex.hasBeenVisited()) {
//
//    current_vertex.setVisited(++count);
//
//    //Sets the cycle_start
//    cycle_start = current_vertex;
//    for (int i = 0; i < current_vertex.numberOfAdjacentVertices(); i++) {
//
//    stack.push(current_vertex.getAdjacentVertex(i));
//
//    }
//    // Pop the vertex if it's been visited
//    } else {
//
//    Vertex<AnyType> last_pop = stack.pop();
//    System.out.printf("%d Adding this: %s\n", cycle_count, last_pop.toString());
//    if (!cycle.add(last_pop) || last_pop.compareTo(cycle_start) == 0) {
//    //Adds the cycle to cycles TreeSet
//    cycles.add(cycle);
//    cycle = new TreeSet<>();
//    cycle_count++;
//    }
//    }
//
//    }
//
//    // DFS is done
//    } catch (EmptyStackException emptyStack) {
//    cycles_str = printCycles(cycles);
//    }
//
//    return cycles_str; // this is only here for the code to compile, you must change!


//dostuff regarding cycles

//Iterates through the adjacent vertices and performs findAllCycles on not visited vertices
//if the not visited vertex is a back edge then the cycle is added to cycles



//    for (int i = 0; i < start_vertex.numberOfAdjacentVertices(); i++) {
//    if (!start_vertex.getAdjacentVertex(i).hasBeenVisited()) {
//    findAllCycles(count, start_vertex.getAdjacentVertex(i), cycles, cycle);
//    } else {
//    cycle.add(start_vertex.getAdjacentVertex(i));
//    if (cycle.size() > 2) {
//    cycles.add(cycle);
//    }
//    }
//    }




