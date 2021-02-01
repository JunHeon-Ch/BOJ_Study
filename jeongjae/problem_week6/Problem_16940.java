package problem_week6;

import java.io.*;
import java.util.*;

//Reference : https://bcp0109.tistory.com/entry/%EB%B0%B1%EC%A4%80-16940%EB%B2%88-BFS-%EC%8A%A4%ED%8E%98%EC%85%9C-%EC%A0%80%EC%A7%80-Java-Kotlin 
public class Problem_16940 {
    
	static private class Node {
        int parent;
        int childSize;

        public Node (int parent, int childSize)
        {
            this.parent = parent;
            this.childSize = childSize;
        }
    }


    public static void main(String[] args) throws Exception {
    	// TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Integer>> arr = new ArrayList<>();
        Node[] node = new Node[n+1];

        for (int i=0; i<n+1; i++)
        {
            arr.add(new ArrayList<>());
            node[i] = new Node(0, 0);
        }

        for (int i=0; i<n-1; i++)
        {
            String[] input = br.readLine().split(" ");
            int v1 = Integer.parseInt(input[0]);
            int v2 = Integer.parseInt(input[1]);

            arr.get(v1).add(v2);
            arr.get(v2).add(v1);
        }

        String[] order = br.readLine().split(" ");
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];

        queue.add(1);
        visited[1] = true;

        // BFS ���鼭 �θ� ���, �ڽ� ��� ���� ���ϱ�
        while (!queue.isEmpty()) {
            int now = queue.poll();

            arr.get(now).forEach(it -> {
                if (!visited[it]) {
                    visited[it] = true;
                    node[it].parent = now;
                    node[now].childSize++;
                    queue.add(it);
                }
            });
        }

        // ��尡 A -> B ������� ���Դٸ� �ڿ��� A �ڽĳ�� -> B �ڽĳ�� ������� ���;��Ѵ�
        // parentQueue ���� order ������� �����鼭 �ڽĳ�尡 ���� ���ö����� ��ٷȴٰ� �θ��带 ��ü���ش�
        Queue<Integer> parentQueue = new LinkedList<>();
        int parent = 1;
        for(int i=1; i<n; i++) {
            int now = Integer.parseInt(order[i]);
            if (parent != node[now].parent) {
                System.out.println(0);
                return;
            }

            if (node[now].childSize > 0) parentQueue.add(now);
            node[parent].childSize--;
            if (node[parent].childSize == 0 && i < n-1) parent = parentQueue.poll();
        }

        System.out.println(1);
    }
}