package study0204;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2583_영역구하기 {

	static int M, N, K, cnt,s;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy ={-1,0,1,0};
	static int[] dx = {0,1,0,-1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[M][N];
		visited=new boolean[M][N];
		for (int i = 0; i < K; i++) {
			int sx = sc.nextInt();
			int sy = sc.nextInt();
			int ex = sc.nextInt();
			int ey = sc.nextInt();
			for(int j=sy;j<ey;j++){
				for(int k=sx;k<ex;k++){
					map[j][k]=1;
				}
			}
		}
		int[] size = new int[10000];
		int idx=0;
		for(int i=0;i<M;i++){
			for(int j=0;j<N;j++){
				if(map[i][j]==0 && !visited[i][j]){
					s=1;
					visited[i][j]=true;
					dfs(i,j);
					size[idx++]=s;
					cnt++;
				}
					
			}
		}
		Arrays.sort(size);
		System.out.println(cnt);
		for(int i=0;i<10000;i++){
			if(size[i]!=0)
				System.out.print(size[i]+" ");
		}
		
	}
	public static void dfs(int y, int x){
		for(int d=0;d<4;d++){
			int ny = y+dy[d];
			int nx = x+dx[d];
			if(ny<0||nx<0||ny>=M||nx>=N||visited[ny][nx])
				continue;
			if(map[ny][nx]==0){
				visited[ny][nx]=true;
				s++;
				dfs(ny,nx);
			}
		}
	}
	

}
