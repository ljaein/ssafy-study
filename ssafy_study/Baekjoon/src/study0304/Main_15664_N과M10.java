package study0304;

import java.util.Arrays;
import java.util.Scanner;

public class Main_15664_N과M10 {

	static int N,M;
	static int[] sel, num;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		M=sc.nextInt();
		sel=new int[M];
		num=new int[N];
		for(int i=0;i<N;i++)
			num[i]=sc.nextInt();
		Arrays.sort(num);
		combi(0,0);
		System.out.println(sb.toString());
	}
	static void combi(int start, int cnt){
		if(cnt==M){
			for(int i=0;i<M;i++)
				sb.append(sel[i]).append(" ");
			sb.append("\n");
			return;
		}
		boolean[] visit = new boolean[10001];
		for(int i=start;i<N;i++){
			if(!visit[num[i]]){
				sel[cnt]=num[i];
				visit[num[i]]=true;
				combi(i+1,cnt+1);
			}
		}
	}

}
