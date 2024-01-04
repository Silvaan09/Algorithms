package Graphs;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {

	public static void main(String[] args) {

		Edge[] edges = {
				new Edge(0, 2, 1),
				new Edge(1, 4, 8),
				new Edge(2, 3, 1),
				new Edge(2, 1, 3),
				new Edge(3, 6, 2),
				new Edge(4, 5, 4),
				new Edge(5, 6, 2), 
				new Edge(5, 8, 9),
				new Edge(6, 9, 1),
				new Edge(8, 7, 1),
				new Edge(9, 8, 5)
		};
		
		int vertices = 10;
		int edge = edges.length;
		// For testing the algorithm 
		ArrayList<Edge> MST = kruskal(edges, vertices, edge);
		int weight = 0;
		System.out.println("From  to");
		for (int i = 0; i < MST.size(); i++) {
			System.out.println(MST.get(i).from + "     " + MST.get(i).to);
			weight += MST.get(i).weight;
		}
		System.out.println("Weight: " + weight);
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
	public static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge other) {
			return Integer.compare(this.weight, other.weight);
		}
	
	}
	
	public static ArrayList<Edge> kruskal(Edge[] E, int n, int m) { //If we don't get the Edges as an Array, we have to create the array ourself
		
		Arrays.sort(E); //If the import isn't given, use MergeSort

		UnionFind UF = new UnionFind(n);
		ArrayList<Edge> MST = new ArrayList<>(n - 1);
		
		for (Edge edge : E) {
			if (!UF.connected(edge.from, edge.to)) {
				MST.add(edge);
				UF.union(edge.from, edge.to);
			}
		}

		return MST;
	}
}
