package study0301;

import java.util.Scanner;

public class Solution_D4_8275_햄스터 {

	static int T,N,X,M,max;
	static int[][] con;
	static int[] cage;
	static String res;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		for(int t=1;t<=T;t++){
			N=sc.nextInt();
			X=sc.nextInt();
			M=sc.nextInt();
			con = new int[M][3];
			cage = new int[N+1];
			max = 0;
			for(int i=0;i<M;i++){
				con[i][0]=sc.nextInt();
				con[i][1]=sc.nextInt();
				con[i][2]=sc.nextInt();
			}
			perm(1,0);
			System.out.println("#"+t+" "+ (max==0?-1:res));
		}
	}

	static void perm(int cnt, int sum){
		if(cnt==N+1){
			if(check()){
				if(max<sum){
					max = sum;
					res=makeRes();
				}
			}
			return;
		}
		for(int i=0;i<=X;i++){
			cage[cnt]=i;
			perm(cnt+1,sum+i);
		}
	}
	
	static String makeRes(){
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<N+1;i++){
			sb.append(cage[i]).append(" ");
		}
		return sb.toString();
	}
	
	static boolean check(){
		for(int i=0;i<M;i++){
			int sum = 0;
			for(int c=con[i][0];c<=con[i][1];c++){
				sum += cage[c];
			}
			if(sum!=con[i][2])
				return false;
		}
		return true;
	}
}
