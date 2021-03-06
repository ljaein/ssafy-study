package kakao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution4 {

	public static void main(String[] args) {
		int[][] board = {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}};
		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
		int answer = Integer.MAX_VALUE;
		int R = board.length;
		int C = board[0].length;
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		int[][] visit = new int[R][C];
		for (int i = 0; i < R; i++)
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		Queue<Point> q = new LinkedList<>();
		visit[0][0] = 0;
		q.add(new Point(0, 0, -1, 0));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.y == R-1 && cur.x == C-1){
				answer = Math.min(answer, cur.cnt);
				continue;
			}
			for (int d = 0; d < 4; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];
				int ncnt = cur.cnt;
				if (ny < 0 || nx < 0 || ny >= R || nx >= C || board[ny][nx] == 1)
					continue;
				if (cur.d != d && cur.d != -1) {
					ncnt += 500;
				}
				if (visit[ny][nx] < ncnt + 100)
					continue;
				visit[ny][nx] = ncnt + 100;
				q.add(new Point(ny, nx, d, ncnt + 100));

			}
		}
		return answer;
	}

	static class Point {
		int y, x, d, cnt;

		public Point(int y, int x, int d, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
			this.cnt = cnt;
		}
	}
}
