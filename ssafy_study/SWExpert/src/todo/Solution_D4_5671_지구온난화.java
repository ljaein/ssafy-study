package todo;

import java.util.Scanner;

public class Solution_D4_5671_지구온난화 {

	static int T, N, M, max;
	static String[] word;
	static boolean[] alph, sel;
	static int[] use;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			max = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			word = new String[N];
			alph = new boolean[26];
			for (int i = 0; i < N; i++) {
				word[i] = sc.next();
				char[] c = word[i].toCharArray();
				for (int j = 0; j < c.length; j++) {
					alph[c[j] - 'a'] = true;
				}
			}
			int len = 0;
			for (int i = 0; i < 26; i++) {
				if (alph[i])
					len++;
			}
			use = new int[len];
			int idx = 0;
			for (int i = 0; i < 26; i++) {
				if (alph[i])
					use[idx++] = i;
			}
			sel = new boolean[26];
			combi(0, 0);
			System.out.println("#" + t + " " + max);
		}
	}

	static void combi(int start, int cnt) {
		if (cnt == M) {
			max = Math.max(max, check());
			return;
		}
		for (int i = start; i < use.length; i++) {
			sel[use[i]] = true;
			combi(i + 1, cnt + 1);
			sel[use[i]] = false;
		}
	}

	static int check() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			boolean ok = true;
			char[] c = word[i].toCharArray();
			for (int j = 0; j < c.length; j++) {
				if (!sel[c[j] - 'a']) {
					ok = false;
					break;
				}
			}
			if (ok)
				cnt++;
		}
		return cnt;
	}

}
