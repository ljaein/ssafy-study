package study0201;

import java.util.Scanner;

public class Solution_D3_2806_NQueen {

	static int[] board;
	static int N;
	static int res;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {

			res = 0;
			N = sc.nextInt();
			board = new int[N];
			setY(0);
			System.out.println("#" + t + " " + res);

		}

	}

	public static void setY(int y) {// 각 층
		for (int i = 0; i < N; i++) {
			board[y] = i;

			boolean check = true;
			for (int j = 0; j < y; j++) {
				if (board[j] == board[y] || y - j == board[y] - board[j] || y - j == board[j] - board[y]) {
					check = false;
				}
			}
			// 만약 놓을 수 있으면
			if (check) {
				if (y == N - 1)
					res++;
				else
					setY(y + 1);
			}
		}
	}

}
