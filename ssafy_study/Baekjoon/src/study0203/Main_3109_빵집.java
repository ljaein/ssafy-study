package study0203;

import java.util.Scanner;

public class Main_3109_빵집 {

	static int R, C, res;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1 };
	static int[] dx = { 1, 1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
		}
		for(int i=0;i<R;i++){
			if(dfs(i,0))
				res++;
		}
		System.out.println(res);

	}

	public static boolean dfs(int y, int x) {
		visited[y][x]=true;
		if(x==C-1){
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= R || nx >= C || visited[ny][nx])
				continue;
			if (map[ny][nx] == '.') {
				if(dfs(ny,nx))
					return true;
			}
		}
		return false;
	}
}
