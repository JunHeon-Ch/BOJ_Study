package problem_week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_20055 {
    
	public static int N, K;
    public static int[] A;
    public static boolean[] check;
    public static int count=0;

    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        A = new int[N*2];
        check = new boolean[N];

        input = br.readLine().split(" ");
        for (int i=0; i<N*2; i++) A[i] = Integer.parseInt(input[i]);

        while (possible()) {
        	
        	int first = A[N*2-1];
        	
        	for (int i=N*2-1; i>0; i--) A[i]=A[i-1];
        	A[0]=first;
        	
        	for (int i=N-1; i>0; i--) check[i]=check[i-1];
        	check[0]=false;
  
        	check[N-1]=false;
        
        	for (int i=N-1; i>0; i--) {
        		
        		//�̵��Ϸ��� ĭ�� �κ��� ������, �� ĭ�� �������� 1�̻� ���� �־�� �Ѵ�
        		if (check[i-1] && check[i]==false && A[i]>=1) {
        			//���� �ִ� ĭ�� �ٽ� �κ��� ����
        			check[i-1]=false;
        			//�̵��� ĭ�� �κ��� �ִ�.
        			check[i]=true;
        			//������ 1 ����
        			A[i]--;
        		}
        	}
        	
        	if(A[0]>0) {
        		check[0]=true;
        		A[0]--;
        	}
        	count++;
        }
        
        System.out.print(count);
    }

    public static boolean possible() {
        
    	int c = 0;

        for (int i=0; i<N*2; i++) {
           
        	if (A[i]==0) c++;
            if (c>=K) return false;
        }
        
        return true;
    }
}