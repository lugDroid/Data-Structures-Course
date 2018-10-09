

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected HashMap<Node, Integer> marked;
	protected Graph graph;
	protected int distance;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashMap<Node, Integer>();
		graph = graphToSearch;
		distance = 0;
	}
	
	public boolean bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return false;
		}
		if (start.getElement().equals(elementToFind)) {
			return true;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.put(start, 0);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.containsKey(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						distance = marked.get(current) + 1;
						return true;
					}
					marked.put(neighbor, marked.get(current) + 1);
					toExplore.add(neighbor);
				}
			}
		}
		return false;
	}
	

}
