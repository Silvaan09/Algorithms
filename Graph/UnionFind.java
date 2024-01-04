package Graphs;

public class UnionFind {
	private int[] parent;
	private int[] rank;
	
	
	public UnionFind(int n){
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}
	
	public int find(int x) {
		while (parent[x] != x) {
			parent[x] = parent[parent[x]];
			x = parent[x];
		}
		return parent[x];
	}
	
	public void union (int from, int to) { //We compared from where to where the edges go
		int xr = find(from);
		int yr = find(to);
		if (xr == yr) {
			return;
		}
		
		if (rank[yr] < rank[xr]) {
			parent[yr] = xr;
		}else if (rank[yr] > rank[xr]) {
			parent[xr] = yr;
		}else {
			parent[yr] = xr;
			rank[xr]++;
		}
	}
	
	public boolean connected(int from, int to) { // we check if the edge Vertices from and to of the edge are already connected
		return find(from) == find(to);
	}
}
