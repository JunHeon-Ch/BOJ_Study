package problem_week3;

import java.util.Scanner;

public class Problem_2193 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		long sum = 0;
		
		int N = sc.nextInt();
		long[][] d = new long[N+1][2];
		d[1][0]=0;
		d[1][1]=1;
		/*nested loop�� ������� �ʰ� 1-demensional DP�ε� �ذ��� �� �ִ�
		 * d[i] = i�ڸ��� ��ģ���� �� ����
		 * d[i] = d[i-1]+d[i-2];
		 */
		for(int i=2; i<=N; i++)
		{
			for(int j=0; j<=1; j++)
			{
				if(j==0) d[i][j]=d[i-1][0]+d[i-1][1];
				else d[i][j]=d[i-1][0];
			}
		}
		sum = d[N][0]+d[N][1];
		System.out.print(sum);
	}
}