package problem_week6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Reference : https://wooooooak.github.io/algorithm/2018/12/05/%EB%B0%B1%EC%A4%802250%EB%AC%B8%EC%A0%9C/
public class Problem_2250 {
    
    // point : ���� x��ǥ (��带 �湮�� �� ���� +1 ����)
    public static int point = 1; 
    public static List<Node> tree = new ArrayList<>();
    public static int[] min;
    public static int[] max;
    public static int maxLevel = 0; // Ʈ���� �ִ� ����(����)
    
    static class Node
    {
        int parent;
        int n;
        int left;
        int right;

        Node(int n, int left, int right)
        {
            this.parent = -1;
            this.n = n;
            this.left = left;
            this.right = right;
        }
    }
    
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
    	Scanner sc = new Scanner(System.in);
    	
        int N = sc.nextInt();
        int rootIndex = 0;
        min = new int[N+1];
        max = new int[N+1];
    
        for(int i=0; i<=N; i++)
        {
            tree.add(new Node(i, -1, -1));
            min[i] = N;
            max[i] = 0;
        }
        
        for(int i=0; i<N; i++)
        {
            int n = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();
            
            tree.get(n).left = left;
            tree.get(n).right = right;
            if(left!=-1)  tree.get(left).parent = n;
            if(right!=-1) tree.get(right).parent = n;
        }
        
        for(int i=1; i<=N; i++)
        {
            if(tree.get(i).parent==-1)
            {
                rootIndex = i;
                break;
            }
        }

        inorder(rootIndex, 1);

        // �ϼ��� max�� min�� ������ �� �̾Ƴ���
        int answerLevel = 1;
        int answerWidth = max[1] - min[1] + 1;
        for(int i=2; i<=maxLevel; i++)
        {
            int width = max[i] - min[i] + 1;
            if(answerWidth<width)
            {
                answerLevel = i;
                answerWidth = width;
            }
        }
        System.out.println(answerLevel + " " + answerWidth);
    }

    public static void inorder(int rootIndex, int level)
    {
        Node root = tree.get(rootIndex);
        
        if(maxLevel<level) maxLevel = level;
        if(root.left!=-1) inorder(root.left, level+1);
        // ���� ��尡 ���� ���� ����� ����
        min[level] = Math.min(min[level], point);
        // ���� ���� ������庸�� �׻� x��ǥ�� �� ���� ������ ����
        max[level] = point;
        point++;
        if(root.right!=-1) inorder(root.right, level+1);
    }
}