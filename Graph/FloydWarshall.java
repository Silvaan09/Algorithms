package Graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FloydWarshall {

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
		int[][] DP = floydWarshall(adjacent, 6, from);

		if (DP[from][to] == Integer.MAX_VALUE) {
			System.out.println("The node " + to + " is unreachable from " + from + ".");
		}else {
			System.out.println("The distance from node " + from + " to node " + to + " is: " + DP[from][to]);
		}
	}
	
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
	
	public static int[][] floydWarshall(ArrayList<Edge> E, int n, int m){
		int[][] DP = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				DP[i][j] = Integer.MAX_VALUE;
				if (i == j) { //from node i to i is always 0
					DP[i][j] = 0;
				}
			}
		}
		
		for (Edge edge : E) {
			DP[edge.from][edge.to] = edge.weight;
			DP[edge.to][edge.from] = edge.weight; //If it is an undirected Graph we need this line. But not for a directed Graph
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if(DP[j][i] != Integer.MAX_VALUE && //if j to i is Max, then it is for sure bigger then j to k
							DP[i][k] != Integer.MAX_VALUE && //if i to k is Max, then it is for sure bigger then j to k
							DP[j][k] > DP[j][i] + DP[i][k]) { //if it is already smaller or equal, then we don't have to change it
						
						DP[j][k] = DP[j][i] + DP[i][k];
					}
				}
			}
		}
		return DP;
	}
}

	