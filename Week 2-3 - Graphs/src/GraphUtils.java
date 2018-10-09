

import java.lang.reflect.Array;
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
				return graphSearch.distance;
			} else {
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
		if (g == null || values == null || values.isEmpty() || 
				(values.size() - 1) != g.numNodes ||
				values.get(0) != values.get(values.size() - 1))
			return false;
		
		boolean[] checks = new boolean[values.size()];
		
		for (int i = 0; i < values.size(); i++) {
			if (g.containsElement(values.get(i))) {
				Node current = g.getNode(values.get(i));
				if (i < values.size() - 1) {
					Node next = g.getNode(values.get(i + 1));
					Set<Edge> edges = g.getNodeEdges(current);
				
					for (Edge edge : edges) {
						if (edge.getDestination() == next)
							checks[i] = true;
					}
				} else {
					checks[i] = true;
				}
			}
		}
		
		boolean result = true;
		for (int j = 0; j < checks.length; j++) {
			if (checks[j] == false)
				result = false;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		//UndirectedGraph ug = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
		//DirectedGraph dg = GraphBuilder.buildDirectedGraph("directed_graph.txt");
		
		//System.out.println(GraphUtils.minDistance(dg, "0", "8"));
		//System.out.println(GraphUtils.nodesWithinDistance(ug, "0", 1));
		
		UndirectedGraph ugh = GraphBuilder.buildUndirectedGraph("is_hamiltonian_path_test.txt");
		List<String> path = new LinkedList<>();
		path.add("0");
		path.add("1");
		path.add("2");
		path.add("3");
		path.add("4");
		path.add("5");
		path.add("0");
		
		
		System.out.println(GraphUtils.isHamiltonianPath(ugh, path));
	}
}
