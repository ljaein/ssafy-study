package study0604;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {

	static int N;
	static List<Node>[] list;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		visit = new boolean[N];
		list = new LinkedList[N];
		for(int i=0;i<N;i++){
			list[i] = new LinkedList<>();
		}
		Node[] nodes = new Node[N];
		for(int i=0;i<N;i++){
			nodes[i] = new Node(i,-1);
		}
		for(int i=0;i<N-1;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			list[a].add(nodes[b]);
			list[b].add(nodes[a]);
		}
		bfs();
		for(int i=1;i<N;i++){
			System.out.println(nodes[i].parent+1);
		}
		
	}
	
	static void bfs(){
		Queue<Node> q = new LinkedList<>();
		visit[0]=true;
		for(int i=0;i<list[0].size();i++){
			q.add(list[0].get(i));
			visit[list[0].get(i).n]=true;
			list[0].get(i).parent = 0;
		}
		while(!q.isEmpty()){
			Node cur = q.poll();
			for(int i=0;i<list[cur.n].size();i++){
				Node ver = list[cur.n].get(i);
				if(visit[ver.n])
					continue;
				q.add(ver);
				visit[ver.n]=true;
				ver.parent = cur.n;
			}
		}
	}
	
	static class Node{
		int n, parent;
		public Node(int n, int parent) {
			super();
			this.n = n;
			this.parent = parent;
		}
		
	}

}
