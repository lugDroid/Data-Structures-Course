

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null || src == null || dest == null) 
			return -1;
		
		if (graph.containsElement(src) && graph.containsElement(dest)) {
			
			// use breadth first search to get the shortest path
			BreadthFirstSearch graphSearch = new BreadthFirstSearch(graph);
			if (graphSearch.bfs(graph.getNode(src), dest)) {
				System.out.println("Route found");
				return graphSearch.distance;
			} else {
				System.out.println("Route not found");
				return -1;
			}
		}
		
		return -1;
		
	}
	

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		if (graph == null || src == null || !graph.containsElement(src) || distance < 1)
			return null;
		
		HashSet<String> nodes = new HashSet<>();
		HashMap<Node, Integer> marked = new HashMap<>();
		Queue<Node> toExplore = new LinkedList<Node>();
		
		Node start = graph.getNode(src);
		marked.put(start, 0);
		toExplore.add(start);
		
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			if (marked.get(current) < distance) {
				for (Node neighbor : graph.getNodeNeighbors(current)) {
					if (!marked.containsKey(neighbor)) {
						marked.put(neighbor, marked.get(current) + 1);
						nodes.add(neighbor.getElement());
						toExplore.add(neighbor);
					}
				}
			}
		}
		
		return nodes;
	}


	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/* IMPLEMENT THIS METHOD! */
		
		return true; // this line is here only so this code will compile if you don't modify it
	}
	
	public static void main(String[] args) {
		
		UndirectedGraph ug = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
		DirectedGraph dg = GraphBuilder.buildDirectedGraph("directed_graph.txt");
		
		//System.out.println(GraphUtils.minDistance(dg, "0", "8"));
		System.out.println(GraphUtils.nodesWithinDistance(ug, "0", 1));
	}
}
