package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Dijkstra {

	public static void main(String[] args) {
		Random random = new Random();
		
		// Each ArrayList stands for the edges from the according node
		ArrayList<ArrayList<Edge>> adjacent = new ArrayList<>(List.of(
				new ArrayList<>(List.of(new Edge(0, 2, 1))), // Edges from node 0
				new ArrayList<>(List.of(new Edge(1, 4, 8))),
				new ArrayList<>(List.of(new Edge(2, 3, 1), new Edge(2, 1, 3))), // Edges from node 2
				new ArrayList<>(List.of(new Edge(3, 6, 2))),
				new ArrayList<>(List.of(new Edge(4, 5, 4))), 
				new ArrayList<>(List.of(new Edge(5, 6, 2), new Edge(5, 8, 9))),
				new ArrayList<>(List.of(new Edge(6, 9, 1))),
				new ArrayList<>(List.of(new Edge(7, 7, 0))), // This node doesn't have an edge going away
				new ArrayList<>(List.of(new Edge(8, 7, 1))),
				new ArrayList<>(List.of(new Edge(9, 8, 5)))
				));
		
		// For testing the algorithm 
		int from = random.nextInt(adjacent.size());
		int to = random.nextInt(adjacent.size());
		int dist = dijkstra(adjacent, 10, from)[to];
		
		if (dist == Integer.MAX_VALUE) {
			System.out.println("The node " + to + " is unreachable from " + from + ".");
		}else {
			System.out.println("The distance from node " + from + " to node " + to + " is: " + dist);
		}
	}
	
	// Our Graph:
		/*        0
		 *        |
		 *        1
		 *        |
		 *        v
		 * 1 <-3- 2 -1-> 3
		 * |			 |
		 * 8    		 2      
		 * |             |
		 * v			 v
		 * 4 -4-> 5 -2-> 6
		 *        |      |
		 *        9      1
		 *        |		 |
		 *        v      v
		 * 7 <-1- 8 <-5- 9
		 *
		 * 
		 */
	
	public static class Edge {
		int from;
		int to;
		int weight;
		
		Edge (int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static class Node implements Comparable<Node>{
		int key;
		int distance;
		
		Node (int key, int distance){
			this.key = key;
			this.distance = distance;
		}
		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.distance, node.distance);
		}
		
	}
	
	public static int[] dijkstra(ArrayList<ArrayList<Edge>> G, int n, int start) { 
		int[] P = new int[n]; // store predecessors (Vorgänger) of nodes
		int[] D = new int[n]; // store distance from start to nodes
		
		for (int i = 0; i < n; i++) { // Initializing every node with starting value
			D[i] = Integer.MAX_VALUE;
		}
		D[start] = 0;
		
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		
		PQ.add(new Node(start, 0)); // Adding our staring Node
		
		while (!PQ.isEmpty()) {
			Node u = PQ.poll();
			
			if (D[u.key] < u.distance) { // We already found a shorter distance
				continue;
			}
			
			for (Edge edge : G.get(u.key)) { // Going through all adjacent edges
				
				int v = edge.to;
				int d = D[u.key] + edge.weight;  // Distance to u + edge weight to the next node v
				if (d < D[v]) { // If this distance is smaller then the initial then we change it
					D[v] = d;
					P[v] = u.key;
					PQ.add(new Node(v, D[v]));
				}
			}
		}
		return D;
	}
}
