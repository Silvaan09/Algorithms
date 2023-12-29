package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BellmannFord {

	public static void main(String[] args) {
		ArrayList<Edge> adjacent = new ArrayList<Edge>(List.of(
				new Edge(1, 0, 8),
				new Edge(1, 3, 10),
				new Edge(0, 2, 1),
				new Edge(2, 3, -4),
				new Edge(2, 4, -1),
				new Edge(3, 4, 2),
				new Edge(4, 5, -2),
				new Edge(5, 3, 1)
				));
		// For testing the algorithm 
		Random random = new Random();
		int from = random.nextInt(6);
		int to = random.nextInt(6);
		int dist = bellmannFord(adjacent, 6, from)[to];

		if (dist == Integer.MAX_VALUE) {
			System.out.println("The node " + to + " is unreachable from " + from + ".");
		}else {
			System.out.println("The distance from node " + from + " to node " + to + " is: " + dist);
		}
	}
	
	
	
	
	
	/* Our graph:
	 * 
	 * 0 <-- 8 -- 1
	 * |          |
	 * 1          10
	 * |          |
	 * v          v
	 * 2 -- -4 -> 3
	 * |        / ^
	 * -1     2   |
	 * |    /     1
	 * v  v	      |
	 * 4 -- -2 -> 5
	 *
	 */
	
	public static class Edge{
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static int[] bellmannFord(ArrayList<Edge> E, int n, int start) {
		int[] P = new int[n]; // store predecessors (Vorgänger) of nodes
		int[] D = new int[n]; // store distance from start to nodes
		
		for (int i = 0; i < n; i++) { // Initializing every node with starting value
			D[i] = Integer.MAX_VALUE;
		}
		D[start] = 0;
		
		for (int i = 1; i < n; i++) { // we need to run the algorithm n-1 times until nothing changes anymore
			for (Edge edge : E) { // we look at every edge once
				
				if (D[edge.from] != Integer.MAX_VALUE && D[edge.to] > D[edge.from] + edge.weight) { // if staring edge == max then nothing changes when we go from there. 
																									// if edge.to isn't bigger, then we don't have to change anything, since it's already smaller
					D[edge.to] = D[edge.from] + edge.weight; // We found a cheaper way
					P[edge.to] = edge.from; // saving the predecessor
				}
			}
		}
		
		for (Edge edge: E) { // If there still is a change after n-1 iterations, there is a negative cycle
			if (D[edge.from] != Integer.MAX_VALUE && D[edge.to] > D[edge.from] + edge.weight) {
				System.out.println("Negative caycle detected.");
				return null;
			}
		}
		return D;
	}
}