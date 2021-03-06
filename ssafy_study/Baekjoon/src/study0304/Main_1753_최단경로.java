package study0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {

	static int V, E, K;
	static List<Ver>[] list;
	static int[] dist;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine()) - 1;
		list = new LinkedList[V];
		dist = new int[V];
		for (int i = 0; i < V; i++) {
			list[i] = new LinkedList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			list[s].add(new Ver(e, w));
		}
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Ver> pq = new PriorityQueue<>();

		dist[K] = 0;
		pq.add(new Ver(K, 0));
		while (!pq.isEmpty()) {
			Ver cur = pq.poll();
			for (Ver ver : list[cur.v]) {
				if (dist[ver.v] > dist[cur.v] + ver.w) {
					dist[ver.v] = dist[cur.v] + ver.w;
					pq.add(new Ver(ver.v, dist[ver.v]));
				}
			}
		}
		for (int i = 0; i < V; i++)
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		System.out.print(sb.toString());
	}

	static class Ver implements Comparable<Ver> {
		int v, w;

		public Ver(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Ver arg0) {
			return this.w - arg0.w;
		}

	}
}
