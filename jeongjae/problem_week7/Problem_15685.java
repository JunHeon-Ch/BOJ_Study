package problem_week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Problem_15685 {
	
	public static int N, x, y, d, g;
	public static boolean[][] check = new boolean[101][101];
	public static int count = 0;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
    	// TODO Auto-generated method stub
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        N = Integer.parseInt(br.readLine());
        
        for (int i=N; i>0; i--) {
        	
        	String[] input = br.readLine().split(" ");
        	x = Integer.parseInt(input[0]);
        	y = Integer.parseInt(input[1]);
        	d = Integer.parseInt(input[2]);
        	g = Integer.parseInt(input[3]);
        	
        	ArrayList<Integer> dir = new ArrayList<>();
        	dir.add(d);
            //storing direction information of each dragon curve with d and g 
        	//d, g�� ������ �����ϴ� �巡�� Ŀ�� ������ ���� ���� �����ϱ�
        	while (g-->0) {
        		
        		for (int j=dir.size()-1; j>=0; j--) {
        			int temp = (dir.get(j)+1)%4;
        			dir.add(temp);
        		}
        	
        	//drawing each dragon curve with direction information of it
        	//������ �巡�� Ŀ���� ���������� ������ �巡�� Ŀ�� �׸���
        	dragon_curve(x, y, dir);
        	}
        }
        
        //counting the number of square(1X1) whose edges are all part of dragon curve
        //ũ�Ⱑ 1X1�� ���簢���� �� �������� ��� �巡�� Ŀ���� �Ϻ��� ���� ����
        for (int i=0; i<100; i++) {
        	for (int j=0; j<100; j++) {
        		if(check[i][j] && check[i][j+1] && check[i+1][j] && check[i+1][j+1]) count++;
        	}
        }
        System.out.print(count);
    }
    
    //�׷��� ���� check[x][y] = true
    public static void dragon_curve(int x, int y, ArrayList<Integer> dir) {
        
    	check[x][y] = true;
    	
        for (int d : dir) {
        	switch (d) {
        		//0: x��ǥ�� �����ϴ� ����
        		case 0:
                    check[++x][y]=true;
                    break;
                //1: y��ǥ�� �����ϴ� ����
                case 1:
                    check[x][--y]=true;
                    break;
                //2: x��ǥ�� �����ϴ� ����    
                case 2:
                    check[--x][y]=true;
                    break;
                //3: y��ǥ�� �����ϴ� ����    
                case 3:
                    check[x][++y]=true;
                    break;
                default:
                	break;
            }
        }
    }
}
