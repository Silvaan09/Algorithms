package Graphs;

import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {

	public static void main(String[] args) {
		List<List<Integer>> adjacent = List.of(
				List.of(2),
                List.of(2, 4),
                List.of(0, 1, 3),
                List.of(2),
                List.of(1, 5),
                List.of(4, 6, 8),
                List.of(5, 9),
                List.of(8),
                List.of(5, 7, 9),
                List.of(6, 8)
            );

		bfs(adjacent, 1, 10);
	}
	
	// Our Graph:
	/*       0
	 *       |
	 *       |
	 * 1 --- 2 --- 3
	 * |          
	 * |           
	 * 4 --- 5 --- 6
	 *       |     |
	 *       |     |
	 * 7 --- 8 --- 9
	 *
	 * 
	 */
	
	public static void bfs(List<List<Integer>> G, int s, int n) { //(adjacency list, start, #nodes)
		boolean[] visited = new boolean[n];
		LinkedList<Integer> Q = new LinkedList<>(); //works as our Queue
		
		Q.addLast(s);
		
		while (!Q.isEmpty()) {
			int u = Q.pollFirst();
			
			if (!visited[u]) {
				visited[u] = true;
				System.out.println(u + " visited"); // not necessary, but you see when which node gets visited
				
				for (Integer v : G.get(u)) { // going through every adjacent node of u
					if (!visited[v]) {
						Q.addLast(v);
					}
				}
			}
		}
	}
}
