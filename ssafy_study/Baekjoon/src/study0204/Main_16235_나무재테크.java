package study0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_16235_나무재테크 {

	static int N, M, K;
	static int[][] A;
	static int[][] nut;
	static LinkedList<Tree> tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];// 추가할 양분
		nut = new int[N][N]; // 현재 양분
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				nut[i][j]=5;
			}
		}
		tree = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			tree.add(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}
		for (int k = 0; k < K; k++) {
			year(k);
		}
		System.out.println(tree.size());

	}

	public static void year(int k) {
		int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };
		int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
		// 봄
		for (Tree t : tree) {
			if (t.a <= nut[t.y][t.x]) {
				nut[t.y][t.x] -= t.a;
				t.a += 1;
			} else {
				t.l=0;
			}
		}
		// 여름
		for(Iterator<Tree> it = tree.iterator();it.hasNext();) {
			Tree t= it.next();
			if(t.l==0){
				nut[t.y][t.x] += t.a / 2;
				it.remove();
			}
		}		
		// 가을
		LinkedList<Tree> newtree = new LinkedList<>();
		for (Tree t : tree) {
			if (t.a % 5 == 0) {
				for (int d = 0; d < 8; d++) {
					int ny = t.y + dy[d];
					int nx = t.x + dx[d];
					if (ny < 0 || nx < 0 || ny >= N || nx >= N)
						continue;
					newtree.add(new Tree(ny, nx, 1));
				}
			}
		}
		tree.addAll(0,newtree);
		if(k==K-1)
			return;
		// 겨울
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				nut[i][j] += A[i][j];
			}
		}

	}

	static class Tree {
		int y, x, a,l;

		public Tree(int y, int x, int a) {
			super();
			this.y = y;
			this.x = x;
			this.a = a;
			l=1;
		}
		
	}

}
