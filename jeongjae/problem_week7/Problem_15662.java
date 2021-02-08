package problem_week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.Point;

public class Problem_15662 {
    
	public static int T, K;
    public static int cogwheel[][];
    public static int temp[][];
    public static boolean[] check;
    public static Queue<Point> q = new LinkedList<>();
    public static ArrayList<Point> al = new ArrayList<>();
    public static int count = 0;
    
    public static void main(String[] args) throws IOException{
    	// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        cogwheel = new int[T+1][9];
        temp = new int[T+1][9];
        
        for(int i=1; i<=T; i++) {
            String line = br.readLine();
            for(int j=1; j<9; j++)  cogwheel[i][j] = line.charAt(j-1)-'0';
        }
  
        K = Integer.parseInt(br.readLine());
        
        for(int i=0; i<K; i++) {
            String[] line = br.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            int dir = Integer.parseInt(line[1]);
            q.add(new Point(num, dir));
        }
        
        //processing all operations
        //��� ���� ó���ϱ�(��Ϲ��� ȸ����Ű��)
        while(!q.isEmpty()) {
        	
        	al.clear();
        	check= new boolean[T+1];
            Point p= q.remove();
            int num = p.x;
            int dir = p.y;
            check[num]=true;
            
            //finding out every cogwheel that can be rotated by others
            //�� ��Ϲ����� ȸ������ ���� ȸ���� ������ ��� ��Ϲ����� ã�Ƴ���
            find_cogwheel(num,dir);
            
            //rotating every cogwheel that can be rotated
            //ȸ���� ������ ��� ��Ϲ����� ȸ����Ų��
            for(int i=0; i<al.size(); i++) rotate_cogwheel(al.get(i).x,al.get(i).y);
        }
        
        //�� K�� ȸ����Ų ���Ŀ� 12�ù����� S���� ��Ϲ����� ���� ã��
        for(int i=1; i<=T; i++) {
            if(cogwheel[i][1]==1) count++;
        }
        System.out.print(count);
    }
    
    //finding all cogwheels that rotate when one cogwheel(#num) rotates
    //��ȣ�� num�� ��Ϲ����� ȸ�������� �׷� ���� ȸ���ϴ� ��� ��Ϲ��� ã��
    public static void find_cogwheel(int num, int dir) {
    	
        al.add(new Point(num, dir));
        
        if(num-1>=1 && cogwheel[num-1][3]!=cogwheel[num][7] && check[num-1]==false ) {
            check[num-1] = true;
            find_cogwheel(num-1, dir*-1);
        }
        if(num+1<=T && cogwheel[num+1][7] != cogwheel[num][3] && check[num+1]==false) {
            check[num+1] = true;
            find_cogwheel(num+1, dir*-1);
        }
    }
    
    //rotating one cogwheel
    //�Ѱ��� ��Ϲ��� ȸ����Ű��
    public static void rotate_cogwheel(int num, int dir) {
    	
    	//copying the status of every cogwheel to 2D array temp
    	//2D array temp�� ���� ��� ��Ϲ����� ���� �����ϱ�
        for(int i=1; i<=T; i++) {
            for(int j=1; j<9; j++) {
                temp[i][j]=cogwheel[i][j];
            }
        }
        //clock_wise_rotation
        //�ð����ȸ��
        if(dir==1) {
            for(int i=1; i<9; i++) {
                if(i==1) {
                    cogwheel[num][1] = temp[num][8];
                }
                else {
                    cogwheel[num][i] = temp[num][i-1];
                }
            }
        }
        //counter_clock_wise_rotation
        //�ݽð����ȸ��
        else {
            for(int i=8; i>=1; i--) {
                if(i==8) {
                    cogwheel[num][8]=temp[num][1];
                }
                else {
                    cogwheel[num][i] = temp[num][i+1];
                }
            }
        }
    }
}